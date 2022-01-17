I used the code of lab6 and generate some tls certificates for the backend of project;
I use this to create certificate 
openssl req -x509 -newkey rsa:4096 -keyout key.pem -out cert.pem -sha256 -days 365
As we can see, we create a certificate with rsa:4096 and key encrypted with sha256 for a one year
