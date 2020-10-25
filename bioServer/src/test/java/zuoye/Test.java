package zuoye;

import com.bio.zuoye.client.Client;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jerry
 * @Date 2020/10/25 10:17 上午
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("请出入你想发送的消息：");
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.next();
            Client client = new Client();
            try {
                client.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
