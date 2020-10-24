import com.bio.ketang.client.ClientStart;
import com.bio.ketang.server.ServerStart;

import java.io.IOException;
import java.util.Random;

/**
 * @author Jerry
 * @Date 2020/10/24 3:32 下午
 */

public class BioTest {

    public static void main(String[] args) throws InterruptedException {
        //start server
        Thread server = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    ServerStart.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        server.start();

        Thread.sleep(300);


        Random random = new Random(System.currentTimeMillis());
        char[] op = {'+','-','*','/'};
        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true){
                    String exprocess = random.nextInt(10)+""+random.nextInt(4)+random.nextInt(10);
                    ClientStart.send(exprocess);
                    try {
                        Thread.sleep(Long.parseLong("1000"));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

    }
}
