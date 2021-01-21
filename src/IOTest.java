import java.io.InputStream;
import java.util.Scanner;


// 42 자바 스터디
public class IOTest {

    public static void main(String[] args) {

        InputStream inputStream = System.in; //표준 입력 스트림

        byte[] data = new byte[100];

        try {

            for (int idx = 0; idx < 10; idx++) {

                data[idx] = (byte) inputStream.read();

                /*
                read() 메서드는 입력 스트림으로부터 한 바이트를 읽어옴. EOF, TCP 의 FIN 패킷 등 스트림의 끝을 인식하면 -1 반환
                TCP 버퍼, 키보드 버퍼 등 입력스트림의 추상화 대상에서 가져올 대상이 없을 경우에는 데이터가 들어올떄까지 IO 를 요청한
                애플리케이션 스레드가 sleep 상태가 됩니다 (Blocking IO)
                */
            }

        } catch (Exception e) {

            e.printStackTrace();

        } //Blocks application thread until 10 bytes are buffered
        // 키보드 입력은 키보드에 연결된 로컬 버퍼에 쌓이다 \n 이 입력되었을때 스트림에서 읽을 수 있는 버퍼로 넘어옴을 알 수 있습니다.

        String str = new String(data);

        System.out.println(str); // 표준 출력스트림 (PrintStream 에 문자열 출력

        Scanner scanner = new Scanner(inputStream); // inputStream 을 감싸는 Wrapper 객체 , input/output 스트림을 좀더 쉽게 사용할 수 있도록 해줍니다.

         //nextLine 메서드는 \n 이 올때까지 스트림에서 데이터를 읽고 String 타입으로 반환합니다. 스트림의 position 은 다음 줄의 시작 위치로 설정됩니다.

        System.out.println(scanner.nextLine());


    }
}
