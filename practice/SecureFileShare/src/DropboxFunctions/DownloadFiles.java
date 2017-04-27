package DropboxFunctions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;

public class DownloadFiles {

	public static void downloadFromDropbox(DbxClientV2 client)//TODO need to include path of the folder

    { OutputStream downloadFile = null;
		try{
        //output file for download --> storage location on local system to download file
         downloadFile = new FileOutputStream("temp/test.txt");
       
       
        FileMetadata metadata = client.files().downloadBuilder("/test.txt")
                .download(downloadFile);
        } catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally
        {
            try {
				downloadFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
	
}
