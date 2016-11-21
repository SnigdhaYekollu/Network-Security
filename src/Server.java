import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static final int LISTENING_PORT = 7000;
	boolean connected=false;
	 
    public static void main(String[] args)
    {
        // Open server socket for listening
        ServerSocket serverSocket = null;
        try {
           serverSocket = new ServerSocket(LISTENING_PORT);
           System.out.println("Chat Server started on port " + LISTENING_PORT);
        } catch (IOException se) {
           System.err.println("Can not start listening on port " + LISTENING_PORT);
           se.printStackTrace();
           System.exit(-1);
        }
        
        
 
        
 
        // Accept and handle client connections
        while (true) {
           try {
               Socket socket = serverSocket.accept();
               String clientMsg;
               System.out.println("entered try of server");
               String message="Server says you are registered";
               //check in database and then say whther registerd or not if not, it will add to database
               PrintWriter writer = new PrintWriter(socket.getOutputStream());
               writer.println(message);
               BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               clientMsg = reader.readLine();
               System.out.println(clientMsg);
               
               
            // Start ServerDispatcher thread ----- start once we have key
               SendClientMsg serverDispatcher = new SendClientMsg();
               serverDispatcher.start();
               
               NodeInfo clientInfo = new NodeInfo();
               clientInfo.mSocket = socket;
               Client clientListener =
                   new Client(clientInfo, serverDispatcher);
               ClientSender clientSender =
                   new ClientSender(clientInfo, serverDispatcher);
               clientInfo.mClientListener = clientListener;
               clientInfo.mClientSender = clientSender;
               clientListener.start();
               clientSender.start();
               serverDispatcher.addClient(clientInfo);
           } catch (IOException ioe) {
               ioe.printStackTrace();
           }
        }
    }
	
	
}
