import java.lang.ref.SoftReference;

/**
 * @author Jerry
 * @Date 2019/11/6 10:35 下午
 */
public class GC {
    private static final int _1M = 1024 * 1024;

    /**
     * vm参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1M];
        allocation2 = new byte[2 * _1M];
        allocation3 = new byte[2 * _1M];
        allocation4 = new byte[4 * _1M];

        GC gc = new GC();
        int a = gc.inc();
        System.out.println(a);
    }

    public int inc() {
        int x;
        try {
            x = 1;
            int a = 1 / 0;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
