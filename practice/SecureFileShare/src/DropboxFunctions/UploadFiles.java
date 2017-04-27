package DropboxFunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.UploadErrorException;

public class UploadFiles {

	
	public static boolean uploadToDropbox(DbxClientV2 client, String encfile, String filepath) //TODO need to include path of the folder
	{
	
		boolean uploaded = false;
    try (InputStream in = new FileInputStream(encfile)) {
        FileMetadata metadata = client.files().uploadBuilder("/" +filepath)
            .uploadAndFinish(in);
        
        uploaded = true;
        JOptionPane.showMessageDialog(null, "File uploaded to dropbox");
        
       return uploaded;
        
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
	return uploaded;
	
}
}
