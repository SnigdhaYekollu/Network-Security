import java.io.*;
import java.net.*;
 
public class ChatClient
{
    public static final String SERVER_HOSTNAME = "localhost";
    public static final int SERVER_PORT = 7000;
   
    public static void main(String[] args)
    {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
           
          Socket socket= new Socket(SERVER_HOSTNAME, SERVER_PORT);
           in = new BufferedReader(
               new InputStreamReader(socket.getInputStream()));
           out = new PrintWriter(
               new OutputStreamWriter(socket.getOutputStream()));
           System.out.println("Connected to server " +
              SERVER_HOSTNAME + ":" + SERVER_PORT);
           
           String message;
           String sendServer="Thank you , I am registered";
          while ((message=in.readLine()) != null) {
              System.out.println(message);
          }
           
           
           
        } catch (IOException ioe) {
           System.err.println("Can not establish connection to " +
               SERVER_HOSTNAME + ":" + SERVER_PORT);
           ioe.printStackTrace();
           System.exit(-1);
        }
 
        // Create and start Sender thread
        Sender sender = new Sender(out);
        sender.setDaemon(true);
        sender.start();
 
        try {
           // Read messages from the server and print them
        	System.out.println("Entering try of chat client");
            String message;
            String sendServer="Thank you , I am registered";
           while ((message=in.readLine()) != null) {
               System.out.println(message);
           }
          //// PrintWriter writer = new PrintWriter(socket.getOutputStream());
			//writer.println(sendServer);
	
           
           
           
        } catch (IOException ioe) {
           System.err.println("Connection to server broken.");
           ioe.printStackTrace();
        }
 
    }
}
 
class Sender extends Thread
{
private PrintWriter mOut;
 
public Sender(PrintWriter aOut)
{
        mOut = aOut;
}
 
    /**
     * Until interrupted reads messages from the standard input (keyboard)
     * and sends them to the chat server through the socket.
     */
public void run()
{
try {
BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
while (!isInterrupted()) {
String message = in.readLine();
mOut.println(message);
mOut.flush();
}
} catch (IOException ioe) {
            // Communication is broken
}
}
}