import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerProcessThread extends Thread {
    private String nickname = null;
    private Socket socket = null;
    List<PrintWriter> listWriters = null;

    public ChatServerProcessThread(Socket socket, List<PrintWriter> listWriters) {
        this.socket = socket;
        this.listWriters = listWriters;
    }

    @Override
    public void run() {
        try {
            BufferedReader buffereedReader =
                    new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            while(true) {
                String request = buffereedReader.readLine();

                if( request == null) {
                    System.out.println("disconnected from client");
                    doQuit(printWriter);
                    break;
                }

                String[] tokens = request.split(":");
                if("join".equals(tokens[0])) {
                    doJoin(tokens[1], printWriter);
                }
                else if("message".equals(tokens[0])) {
                    doMessage(tokens[1]);
                }
                else if("quit".equals(tokens[0])) {
                    doQuit(printWriter);
                }
            }
        }
        catch(IOException e) {
            System.out.println(this.nickname + "left the chat room");
        }
    }
    private void doQuit(PrintWriter writer) {
        removeWriter(writer);

        String data = this.nickname + "left the chat room";
        broadcast(data);
    }

    private void removeWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.remove(writer);
        }
    }

    private void doMessage(String data) {
        broadcast(this.nickname + ":" + data);
    }

    private void doJoin(String nickname, PrintWriter writer) {
        this.nickname = nickname;

        String data = nickname + "has entered.";
        broadcast(data);

        addWriter(writer);
    }

    private void addWriter(PrintWriter writer) {
        synchronized (listWriters) {
            listWriters.add(writer);
        }
    }

    private void broadcast(String data) {
        synchronized (listWriters) {
            for (PrintWriter writer : listWriters) {
                writer.println(data);
                writer.flush();
            }
        }
    }
}
