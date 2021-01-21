import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Parameter;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

class TestClient {


    private ByteBuffer buffer = ByteBuffer.allocateDirect(100);

    private SelectionKey key;

    private SocketChannel channel;

    public TestClient(SocketChannel channel, SelectionKey key) {

        this.channel = channel;

        this.key = key;
    }


    public void onRead(Selector selector) throws IOException {

        channel.read(buffer);

        int pos = buffer.position();

        if (pos < 10)
            return;

        buffer.flip();

        key.interestOps(SelectionKey.OP_WRITE);

        selector.wakeup();

    }

    public void onWrite(Selector selector) throws IOException {

        byte[] data = new byte[10];

        channel.write(buffer.get(data));

        if (buffer.position() < 10)
            return;

        buffer.compact();

        key.interestOps(SelectionKey.OP_READ);

        selector.wakeup();
    }

}
public class NIOTest {

    private final static int port = 25555;

    public static void main(String[] args) {

        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();

            serverSocket.configureBlocking(false);

            serverSocket.bind(new InetSocketAddress(port));

            Selector selector = Selector.open();

            serverSocket.register(selector, SelectionKey.OP_ACCEPT, null);

            while (true) {

                selector.select();

                Set<SelectionKey> keys = selector.selectedKeys();

                for (SelectionKey key : keys) {

                    if (key.isAcceptable()) {

                        SocketChannel channel = serverSocket.accept();

                        channel.configureBlocking(false);

                        channel.register(selector, SelectionKey.OP_READ, new TestClient(channel, key));
                    }

                    else if (key.isReadable()) {

                        TestClient channel = (TestClient) key.attachment();

                        channel.onRead(selector);
                    }

                    else if (key.isWritable()) {

                        TestClient channel = (TestClient) key.attachment();

                        channel.onWrite(selector);
                    }
                 }
            }



        } catch (Exception e) {

            e.printStackTrace();
        }


    }
}
