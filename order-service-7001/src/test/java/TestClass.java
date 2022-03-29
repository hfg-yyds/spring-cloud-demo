import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: Zero
 * @Date: 2022/3/29 10:14
 * @Description:
 */
public class TestClass {

    @Test
    public void testBigDecimal() {
        System.out.println(0.1+0.2);
        System.out.println(0.3 - 0.2);
        System.out.println(3 * 0.2);
        System.out.println(0.3 / 0.1);

        BigDecimal first = new BigDecimal("36");
        BigDecimal second = new BigDecimal("12");

        System.out.println(first.divide(second));
    }

    /**
     * Java8之前是这样处理的时间
     */
    @SneakyThrows
    @Test
    public void testBeforeJava8() {
        //获取Date对象，存放的是时间戳
        Date date = new Date();
        //获取时间戳(毫秒)
        long seconds = date.getTime();
        System.out.println("当前时间戳: " + seconds);

        //当前GMT(格林威治)时间、当前计算机系统所在时区的时间
        SimpleDateFormat beijingFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("本地(东八区)时间: " + beijingFormat.format(date) +"; GMT时间: " + date.toGMTString());

        //东八区时间转换成东九区(东京)时间,比北京早一个小时
        SimpleDateFormat tokyoFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        tokyoFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
        System.out.println("东京(东九区)时间: "+tokyoFormat.format(date));

        //时间戳转化成Date
        SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fotmatString = timestampFormat.format(seconds);
        Date parseDate = timestampFormat.parse(fotmatString);
        System.out.println("时间戳转化成Date之后的时间: "+parseDate + ";格式化之后的: "+ fotmatString);

    }

    @Test
    public void testAfterJava8() {
        //获取当前时区的日期
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);
        //时间
        LocalTime localTime = LocalTime.now();
        System.out.println("localTime: " + localTime);
        //根据上面两个对象，获取日期时间
        LocalDateTime localDateTime = LocalDateTime.of(localDate,localTime);
        System.out.println("localDateTime: " + localDateTime);
        //使用静态方法生成此对象
        LocalDateTime localDateTime2 = LocalDateTime.now();
        System.out.println("localDateTime2: " + localDateTime2);
        //格式化时间
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
        System.out.println("格式化之后的时间: " + localDateTime2.format(formatter));

        //转化为时间戳(秒)
        long epochSecond = localDateTime2.toEpochSecond(ZoneOffset.of("+8"));
        //转化为毫秒
        long epochMilli = localDateTime2.atZone(ZoneOffset.systemDefault()).toInstant().toEpochMilli();
        System.out.println("时间戳为:(秒) " + epochSecond + "; (毫秒): " + epochMilli);

        //时间戳(毫秒)转化成LocalDateTime
        Instant instant = Instant.ofEpochMilli(epochMilli);
        LocalDateTime localDateTime3 = LocalDateTime.ofInstant(instant, ZoneOffset.systemDefault());
        System.out.println("时间戳(毫秒)转化成LocalDateTime: " + localDateTime3.format(formatter));
        //时间戳(秒)转化成LocalDateTime
        Instant instant2 = Instant.ofEpochSecond(epochSecond);
        LocalDateTime localDateTime4 = LocalDateTime.ofInstant(instant2, ZoneOffset.systemDefault());
        System.out.println("时间戳(秒)转化成LocalDateTime: " + localDateTime4.format(formatter));

    }
    // Date 转化成 LocalDateTime
    public static LocalDateTime dateToLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return instant.atZone(zoneId).toLocalDateTime();
    }
    // LocalDateTime 转化成 Date
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }
    //由于 LocalDate、LocalTime 或者只含有日期，或者只含有时间，因此，不能和Date直接进行转化。


    @Test
    public void test() {
        System.out.println(LocalTime.now());
    }
}
