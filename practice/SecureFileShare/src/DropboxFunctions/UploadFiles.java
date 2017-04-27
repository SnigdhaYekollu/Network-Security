package DropboxFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class UploadFiles {

	
	public static void uploadToDropbox(DbxClientV2 client) //TODO need to include path of the folder
	{
	// Upload "test.txt" to Dropbox
    
    try (InputStream in = new FileInputStream("C:/Users/snigdha/Desktop/practice/SecureFileShare/srij.txt")) {
        FileMetadata metadata = client.files().uploadBuilder("/srij.txt")
            .uploadAndFinish(in);
        
       
    } catch (UploadErrorException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DbxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
