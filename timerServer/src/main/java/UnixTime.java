import java.util.Date;

/**
 * despaction
 *
 * @Author: jerry
 * @date: 2019/10/14 17:27
 * @description:
 **/
public class UnixTime {
    private final long value;

    public UnixTime() {
        this(System.currentTimeMillis() / 1000L + 2208988800L);
    }

    public UnixTime(long value) {
        this.value = value;
    }

    public long value() {
        return value;
    }

    @Override
    public String toString() {
        return new Date((value() - 2208988800L) * 1000L).toString();
    }

    public static void main(String[] args) {
        System.out.println(new UnixTime());
    }
}
