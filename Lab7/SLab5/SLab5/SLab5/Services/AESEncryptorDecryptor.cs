using System;
using System.IO;
using System.Security.Cryptography;

namespace SLab5.Services
{
    public class AESEncryptorDecryptor: IEncryptorDecryptor
    {
        public byte[] Encrypt(string plainText, byte[] key, byte[] iv)
        {
            byte[] encrypted;

            var keyNormal = NormalizeAESKey(key);
            var ivNormal = NormalizeAESIV(iv);
            
            using (Aes aes = Aes.Create())
            {
                ICryptoTransform encryptor = aes.CreateEncryptor(keyNormal, ivNormal);
                
                using (MemoryStream msEncrypt = new MemoryStream())
                {
                    using (CryptoStream csEncrypt = new CryptoStream(msEncrypt, encryptor, CryptoStreamMode.Write))
                    {
                        using (StreamWriter swEncrypt = new StreamWriter(csEncrypt))
                        {
                            swEncrypt.Write(plainText);
                        }
                        encrypted = msEncrypt.ToArray();
                    }
                }
            }
            
            return encrypted;
        }
        
        public string Decrypt(byte[] cipherText, byte[] key, byte[] iv)
        {
            string plaintext;
            
            var keyNormal = NormalizeAESKey(key);
            var ivNormal = NormalizeAESIV(iv);

            using (Aes aesAlg = Aes.Create())
            {
                ICryptoTransform decryptor = aesAlg.CreateDecryptor(keyNormal, ivNormal);
                
                using (MemoryStream msDecrypt = new MemoryStream(cipherText))
                {
                    using (CryptoStream csDecrypt = new CryptoStream(msDecrypt, decryptor, CryptoStreamMode.Read))
                    {
                        using (StreamReader srDecrypt = new StreamReader(csDecrypt))
                        {
                            plaintext = srDecrypt.ReadToEnd();
                        }
                    }
                }
            }

            return plaintext;
        }

        private byte[] NormalizeAESKey(byte[] key)
        {
            var keyNormal = new byte[32];
            
            for (int i = 0; i < Math.Min(keyNormal.Length, key.Length); i++)
            {
                keyNormal[i] = key[i];
            }

            return keyNormal;
        }

        private byte[] NormalizeAESIV(byte[] iv)
        {
            var ivNormal = new byte[16];

            for (int i = 0; i < Math.Min(ivNormal.Length, iv.Length); i++)
            {
                ivNormal[i] = iv[i];
            }

            return ivNormal;
        }
    }
}