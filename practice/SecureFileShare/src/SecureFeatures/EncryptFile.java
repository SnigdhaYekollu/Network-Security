package SecureFeatures;

import java.io.File;

import javax.crypto.Cipher;

import DBfunctions.DBconn;
import DBfunctions.DButils;




public class EncryptFile {

	static final int mode=Cipher.ENCRYPT_MODE;
	String password;
	int numberOfBits;
	String algorithm;
	String transformation;
	String filename;
	boolean pwdexist;
	
	public EncryptFile(int numberOfBits, String algorithm,String transformation,String filename,String password, boolean pwdexist) {
		super();
		this.password = password;
		this.numberOfBits = numberOfBits;
		this.algorithm = algorithm;
		this.transformation=transformation;
		this.filename=filename;
		this.pwdexist = pwdexist;
	}

	
	/** used to encrypt the file.
	 * @param inputFile
	 * @param outputFile
	 * @throws Exception
	 */
	public void Encrypt(File inputFile,File outputFile) throws Exception{
		
		DButils newObj = new DButils();
		//Generate the encryption key using pseudo random
		byte fileEncKey[] = newObj.generateKey(16);
		//Generate the hash key using pseudo random.
		byte hashKey[]=newObj.generateKey(16);
		//generate the hash value of the input file. AES_SHA 128 bits.
		String hashValue = newObj.generateHash(inputFile, hashKey); 
		// generate the hash of password. This is to check if the password is correct or not.
		
		// connecting to the database.
		DBconn dbConn= new DBconn();
		
		
		// adding values to the database
		 byte[] encFilePassword= newObj.generateMasterKey_EncryptAndDecrypt(password, 0, fileEncKey);
		 byte[] encHashPassword= newObj.generateMasterKey_EncryptAndDecrypt(password, 0, hashKey);
		 String hashPassword= newObj.stringHash(password);
		// instead of password hash in the last column file hash is stored.
		// This has to be changed for hashing password. Function is not yet implemented. 
		 dbConn.insert(outputFile.getName(),pwdexist, password,encFilePassword, encHashPassword, hashValue);
		// do the file encryption
		newObj.encryptFile(transformation,mode, inputFile, outputFile, fileEncKey);
	
	System.out.println("Encryption done successfully");
	}
	
	
	
	
	
}
