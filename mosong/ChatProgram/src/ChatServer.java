import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class ChatServer {
    public static final int PORT = 5004;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        List<PrintWriter> listWriters = new ArrayList<PrintWriter>();

        try {
            String localHostAddress = InetAddress.getLocalHost().getHostAddress();
            serverSocket.bind(new InetSocketAddress(localHostAddress, PORT));
            System.out.println("[server]\n address : "+localHostAddress + ", host : " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                new ChatServerProcessThread(socket, listWriters).start();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (serverSocket != null && !serverSocket.isClosed())
                    serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
