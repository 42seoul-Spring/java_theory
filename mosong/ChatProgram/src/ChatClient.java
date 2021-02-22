import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.io.PrintWriter;

public class ChatClient {
    private static final String SERVER_IP = "192.168.219.107";
    private static final int SERVER_PORT = 5004;

    public static void main(String[] args) {
        String name = null;
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Insert username.");
            System.out.print(">>> ");
            name = sc.nextLine();

            if (name.isEmpty() == false ) {
                break;
            }

            System.out.println("please insert username\n");
        }
        sc.close();

        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
            System.out.println("Hello!");
            new ChatWindow(name, socket).show();

            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
            String request = "Join : " + name + "\n";
            pw.println(request);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
