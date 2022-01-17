using System;

namespace SLab5.Entity
{
    public class User
    {
        public Guid ID { get; set; }
        public byte[] FirstName { get; set; }
        public byte[] LastName { get; set; }
        public byte[] Login { get; set; }
        public byte[] PasswordHash { get; set; }
        public DateTime CreateTime { get; set; }
    }
}