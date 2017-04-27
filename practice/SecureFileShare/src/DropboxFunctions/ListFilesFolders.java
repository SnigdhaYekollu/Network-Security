package DropboxFunctions;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.ListFolderErrorException;
import com.dropbox.core.v2.files.ListFolderResult;
import com.dropbox.core.v2.files.Metadata;

public class ListFilesFolders {
	
	
	public static void ListAllFromDropbox(DbxClientV2 client) throws ListFolderErrorException, DbxException{
	

    ListFolderResult resultSubfolder;
    ListFolderResult result = client.files().listFolder(""); 
    
        for (Metadata metadata : result.getEntries()) {
        	
        	
            System.out.println("File/Folder:  "+metadata.getPathDisplay());
           
         
            
            if(!metadata.getPathLower().contains("."))
            {
            resultSubfolder = client.files().listFolder(metadata.getPathDisplay()); 
              
         
            for(Metadata meta : resultSubfolder.getEntries()){
            	System.out.println("Subfolder Contents: "+meta.getPathDisplay());
            }
            }
            result = client.files().listFolderContinue(result.getCursor()); 
          
        }
        
}

}
