package DropboxConnection;
import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;
import com.dropbox.core.v2.sharing.ListFilesResult;
import com.dropbox.core.v2.sharing.ListFoldersResult;
import com.dropbox.core.v2.sharing.ListReceivedFilesBuilder;
import com.dropbox.core.v2.sharing.SharedFileMetadata;
import com.dropbox.core.v2.sharing.SharedFolderMetadata;
import com.dropbox.core.v2.users.FullAccount;

import DropboxFunctions.DownloadFiles;
import DropboxFunctions.UploadFiles;

import java.util.ArrayList;
import java.util.List;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class DropboxConnection {
    private static final String ACCESS_TOKEN = "raj4TOJ9dYAAAAAAAAAAcq_ALXxKfKha1w_FQpLGFCUEZTDyK406fiDmeMPLBzoC"; 
    public static DbxClientV2 client;
    @SuppressWarnings("unchecked")
	public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        @SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
         client = new DbxClientV2(config, ACCESS_TOKEN);
        
     //account information
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        

     // Get files and folder metadata from Dropbox root directory
        
        ListFolderResult result = client.files().listFolder(""); 
        
        while (true) {
            for (Metadata metadata : result.getEntries()) {
            	
            	  	System.out.println(metadata.getPathDisplay());          	  
            
            }
            if (!result.getHasMore()) {
            	
                break;
            }
            
            result = client.files().listFolderContinue(result.getCursor());
        }

        
     //DownloadFiles.downloadFromDropbox(client);

      // UploadFiles.uploadToDropbox(client) ;
        
    //Get the list of shared folders
        ListFoldersResult sharedFolders = client.sharing().listFolders();   
        List<SharedFolderMetadata> sharedList = sharedFolders.getEntries();
        for(SharedFolderMetadata sharedData : sharedList){
        System.out.println("Shared Folder : "+sharedData.getName());
        
        }
    
      //Get the list of shared files
        
        
        
        try{
        ListFilesResult shFiles = client.sharing().listReceivedFiles();
               
        for(SharedFileMetadata sharedData :  shFiles.getEntries()){
        System.out.println(sharedData.getName());
        
        }
        }
        catch(Exception e){
        	e.printStackTrace();
        }
      

        
             
        
    }
}
