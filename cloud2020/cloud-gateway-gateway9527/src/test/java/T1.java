import java.time.ZonedDateTime;
import java.util.TimeZone;

/**
 * @Author gaojunlong
 * @Date 2020-11-26
 * @Time 16:40
 * @Version 1.0
 */
public class T1 {
    public static void main(String[] args) {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
        //2020-11-26T16:47:23.968+08:00[Asia/Shanghai]
    }
}
