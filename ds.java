package DigitalSignature;
import java.util.*;
import java.security.*;

public class ds 
{
    public static void main(String[] args) 
    {
      try
      {
        KeyPairGenerator keygen=KeyPairGenerator.getInstance("DSA"); 
        keygen.initialize(2048);
        KeyPair keypair=keygen.generateKeyPair();
        PrivateKey pkey=keypair.getPrivate();
        PublicKey pukey=keypair.getPublic(); 

      try(Scanner sc = new Scanner(System.in))
      {
        System.out.println("Enter the message : ");
        String message=sc.nextLine();

        Signature siganture=Signature.getInstance("SHA256withDSA");
        siganture.initSign(pkey);
        siganture.update(message.getBytes());
        byte[] digitalSignature=siganture.sign();

        String signatureBase64=Base64.getEncoder().encodeToString(digitalSignature);
        System.out.println("Digital signature : "+signatureBase64);

        siganture.initVerify(pukey);
        siganture.update(message.getBytes());
        boolean varified=siganture.verify(digitalSignature);
        System.out.println("Result : "+varified);
      }
     }
     catch(Exception e)
     {
        e.printStackTrace();
     }
    }
    
}
