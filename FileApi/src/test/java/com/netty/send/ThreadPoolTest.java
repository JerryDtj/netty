package com.netty.send;

import java.util.concurrent.*;

/**
 * @author Jerry
 * @Date 2020/10/21 8:32 下午
 */
public class ThreadPoolTest {
    private static int i;
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolTest test = new ThreadPoolTest();

        System.out.println(test.add(0));

        ExecutorService service = new ThreadPoolExecutor(2,4,10, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>(600000));
        for (int j=0;j<500000;j++){
            service.submit(new Runnable() {
                @Override
                public void run()  {
                    i++;
                }
            });
        }

        service.shutdown();
        System.out.println(i);
    }

    public int add(int num){
        return ++num;
    }

}
