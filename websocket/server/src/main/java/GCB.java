import java.lang.ref.SoftReference;

/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/11/4 11:37
 * @description:
 **/
public class GCB {
    public static void main(String[] args) throws InterruptedException {
        //100M的缓存数据
//        byte[] cacheData = new byte[100 * 1024 * 1024];
        GC gc = new GC();
        //将缓存数据用软引用持有
        SoftReference<GC> cacheRef = new SoftReference(gc);
//        SoftReference<byte[]> cacheRef = new SoftReference(cacheData);
        //将缓存数据的强引用去除
        gc = null;
//        cacheData = null;
        System.out.println("第一次GC前" + gc);
//        System.out.println("第一次GC前" + cacheRef);
        System.out.println("第一次GC前" + cacheRef.get());
        //进行一次GC后查看对象的回收情况
        System.gc();
        //等待GC
        Thread.sleep(500);
        System.out.println("第一次GC后" + gc);
//        System.out.println("第一次GC后" + cacheRef);
        System.out.println("第一次GC后" + cacheRef.get());

        //在分配一个120M的对象，看看缓存对象的回收情况
        GC gc1 = new GC();
//        byte[] newData = new byte[120 * 1024 * 1024];
        System.out.println("分配后" + gc);
//        System.out.println("分配后" + cacheRef);
        System.out.println("分配后" + cacheRef.get());


    }
}
