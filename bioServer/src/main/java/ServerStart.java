import com.bio.server.ServerStartHander;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Jerry
 * @Date 2020/10/22 10:12 下午
 */

public class ServerStart {
    private static Logger log = LoggerFactory.getLogger(ServerStart.class);

    private static int SERVER_POINT = 7777;
    private static ServerSocket serverSocket;

    public static void start() throws IOException {
        Start(SERVER_POINT);
    }

    private static void Start(int serverPoint)  {
        try {
            serverSocket = new ServerSocket(serverPoint);
            log.info("Bio服务端已启动，启动端口为："+serverPoint);
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(new ServerStartHander(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getLocalizedMessage());
        } finally {
            try {
                serverSocket.close();
                log.info("服务端已经关闭");
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}