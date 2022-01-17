namespace SLab5.Services
{
    public interface IEncryptorDecryptor
    {
        byte[] Encrypt(string plainText, byte[] key, byte[] iv);
        string Decrypt(byte[] cipherText, byte[] key, byte[] iv);
    }
}