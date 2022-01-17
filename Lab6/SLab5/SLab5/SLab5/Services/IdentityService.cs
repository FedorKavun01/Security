using System;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using Konscious.Security.Cryptography;
using Microsoft.AspNetCore.Http;
using SLab5.DTO;
using SLab5.Entity;
using SLab5.Infrastructure;

namespace SLab5.Services
{
    public class IdentityService : IIdentityService
    {

        private readonly SLabDBContext _context;
        private readonly IEncryptorDecryptor _encryptorDecryptor;
        
        public IdentityService(SLabDBContext context, IEncryptorDecryptor encryptorDecryptor)
        {
            _context = context;
            _encryptorDecryptor = encryptorDecryptor;
        }

        public UserDecrypted SignIn(SignInDTO signInDto)
        {
            var user = _context.User.ToList().Find(user => Enumerable.SequenceEqual(user.Login, _encryptorDecryptor.Encrypt(signInDto.Login, Encoding.UTF8.GetBytes(user.CreateTime.ToString()), Encoding.UTF8.GetBytes(user.CreateTime.ToString()))) && Enumerable.SequenceEqual(user.PasswordHash, HashPassword(signInDto.Password, user.CreateTime.ToString())));
            
            if (user == null)
            {
                throw new BadHttpRequestException("Incorrect credentials");
            }
            
            var aesKey = Encoding.UTF8.GetBytes(user.CreateTime.ToString());

            var userDecrypted = new UserDecrypted()
            {
                ID = user.ID,
                FirstName = _encryptorDecryptor.Decrypt(user.FirstName, aesKey, aesKey),
                LastName = _encryptorDecryptor.Decrypt(user.LastName, aesKey, aesKey),
                Login = _encryptorDecryptor.Decrypt(user.Login, aesKey, aesKey)
            };

            return userDecrypted;
        }

        public UserDecrypted SignUp(SignUpDTO signUpDto)
        {
            var creationDate = DateTime.Now;
            var aesKey = Encoding.UTF8.GetBytes(creationDate.ToString());

            var ID = Guid.NewGuid();
            var FirstName = _encryptorDecryptor.Encrypt(signUpDto.FirstName, aesKey, aesKey);
            var LastName = _encryptorDecryptor.Encrypt(signUpDto.LastName, aesKey, aesKey);
            var Login = _encryptorDecryptor.Encrypt(signUpDto.Login, aesKey, aesKey);
            var PasswordHash = HashPassword(signUpDto.Password, creationDate.ToString());
            var CreateTime = creationDate;
            
            var user = new User()
            {
                ID = ID,
                FirstName = FirstName,
                LastName = LastName,
                Login = Login,
                PasswordHash = PasswordHash,
                CreateTime = creationDate
            };

            _context.User.Add(user);
            _context.SaveChanges();

            var userDecrypted = new UserDecrypted()
            {
                ID = user.ID,
                FirstName = signUpDto.FirstName,
                LastName = signUpDto.LastName,
                Login = signUpDto.Login
            };

            return userDecrypted;
        }

        private byte[] HashPassword(string password, string salt)
        {
            var argon2 = new Argon2id(Encoding.UTF8.GetBytes(password));
            
            argon2.Salt = Encoding.ASCII.GetBytes(salt);
            argon2.DegreeOfParallelism = 8;
            argon2.Iterations = 4;
            argon2.MemorySize = 512 * 512; 
            
            return argon2.GetBytes(16);
        }
    }
}