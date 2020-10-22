/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/11/4 11:10
 * @description:
 **/
public class GC {
    public static  byte[] cacheData;
    public GC(){
       cacheData = new byte[120 * 1024 * 1024];
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("execute method finalize()");
//        gc = this;
    }
}
