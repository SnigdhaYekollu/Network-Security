
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.users.FullAccount;

import java.util.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class DropboxConnection {
    private static final String ACCESS_TOKEN = "raj4TOJ9dYAAAAAAAAAAcq_ALXxKfKha1w_FQpLGFCUEZTDyK406fiDmeMPLBzoC"; 

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        @SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        
     //account information
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        

     // Get files and folder metadata from Dropbox root directory
        
        ListFolderResult result = client.files().listFolder(""); 
                
        while (true) {
            for (Metadata metadata : result.getEntries()) {
            	
                System.out.println(metadata.getPathLower());
            }

            if (!result.getHasMore()) {
                break;
            }

            result = client.files().listFolderContinue(result.getCursor());
        }

        // Upload "test.txt" to Dropbox
                        
        try (InputStream in = new FileInputStream("C:/Users/snigdha/Desktop/practice/SecureFileShare/test.txt")) {
            FileMetadata metadata = client.files().uploadBuilder("/test.txt")
                .uploadAndFinish(in);
            
           
        }
        
        
    }
}
