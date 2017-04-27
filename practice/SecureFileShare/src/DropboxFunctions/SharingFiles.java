package DropboxFunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.FileMemberActionResult;
import com.dropbox.core.v2.sharing.MemberSelector;
import com.dropbox.core.v2.users.FullAccount;

public class SharingFiles {
	
	private static final String ACCESS_TOKEN = "raj4TOJ9dYAAAAAAAAAAcq_ALXxKfKha1w_FQpLGFCUEZTDyK406fiDmeMPLBzoC"; 

    public static void main(String args[]) throws DbxException, IOException {
        // Create Dropbox client
        @SuppressWarnings("deprecation")
		DbxRequestConfig config = new DbxRequestConfig("dropbox/java-tutorial", "en_US");
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);
        
     //account information
        FullAccount account = client.users().getCurrentAccount();
        System.out.println(account.getName().getDisplayName());
        
        
        
     //share files to specified email id
        List<MemberSelector> newMembers = new ArrayList<MemberSelector>();
        MemberSelector newMember = MemberSelector.email("srijate@gmail.com");
        
        newMembers.add(newMember);

        List<FileMemberActionResult> fileMemberActionResults = client.sharing().addFileMember("/srij.txt", newMembers);
        System.out.print(fileMemberActionResults);

     //need to set access control for files
        
        
        
    }

}
