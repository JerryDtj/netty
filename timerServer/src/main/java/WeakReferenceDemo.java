import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * 弱引用何时被收集
 * 运行参数 -Xmx200m -XX:+PrintGC
 *
 * @author Jerry
 * @Date 2019/11/3 12:07 下午
 */
public class WeakReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        //100M的缓存数据
//        byte[] cacheData = new byte[100 * 1024 * 1024];
        GC gc1 = new GC();
        //将缓存数据用软引用持有
//        WeakReference<byte[]> cacheRef = new WeakReference(cacheData);
//        WeakReference<GC> cacheRef = new WeakReference(gc1);
        SoftReference<GC> cacheRef = new SoftReference(gc1);
        gc1 = null;
        System.out.println("第一次GC前" + gc1);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + gc1);
        System.out.println("第一次GC后" + cacheRef.get());
        GC gc2 = new GC();
        //将缓存数据的强引用去除
//        cacheData = null;

        System.gc();
        Thread.sleep(500);
        System.out.println("第二次GC后" + gc1);
        System.out.println("第二次GC后" + cacheRef.get());
        GC gc3 = new GC();
        //等待GC
        Thread.sleep(500);
        System.out.println("第三次GC后" + gc1);
        System.out.println("第三次GC后" + cacheRef.get());
    }

}
