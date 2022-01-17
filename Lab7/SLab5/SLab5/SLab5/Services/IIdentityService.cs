using SLab5.DTO;
using SLab5.Entity;

namespace SLab5.Services
{
    public interface IIdentityService
    {
        public UserDecrypted SignIn(SignInDTO signInDto);

        public UserDecrypted SignUp(SignUpDTO signUpDto);
    }
}