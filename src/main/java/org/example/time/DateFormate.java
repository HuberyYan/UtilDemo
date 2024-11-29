package org.example.time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

/**
 * @author HuberyYan
 * @data 2024/9/5
 * @apiNote
 */
public class DateFormate {
    public static void main(String[] args) {

        //LocalDateTime localDateTime = LocalDateTime.ofInstant(java.util.Date.from(java.time.Instant.ofEpochMilli(epochMilli)).toInstant(), java.time.ZoneId.systemDefault());
        //DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String format = localDateTime.format(formatter2);
        //System.out.println(format);

        //String s = long2DateStr(1725416799);
        //long l = dateTimeStr2Long("2024-09-14 12:00:00");
        //String s = long2DateStr(1728475200l, "yyyy-MM-dd");
        //System.out.println(s);
        //Calendar instance = Calendar.getInstance();
        //instance.setTimeInMillis(l*1000);
        //instance.setTimeZone(TimeZone.getTimeZone("GMT"));
        //System.out.println(instance.get(Calendar.HOUR_OF_DAY));
        //System.out.println(instance.getTime());

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

        byte[] bytes = {50, 48, 50, 52, 49, 48};
        String str = new String(bytes, 0, 6);
        System.out.println(str);
    }

    /**
     * 将时间戳转换为日期字符串
     * @param timestampSeconds 时间戳（以秒为单位）
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String long2DateStr(long timestampSeconds, String pattern) {
        //// 获取当前时间戳（以秒为单位）
        //int timestampSeconds = 1725416799;
        // 将秒转换为毫秒
        long timestampMilliseconds = (long) timestampSeconds * 1000;
        // 创建一个Date对象
        Date date = new Date(timestampMilliseconds);
        // 创建一个SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        // 格式化日期
        String formattedDate = sdf.format(date);
        // 打印格式化后的日期
        System.out.println("Format: " + formattedDate);
        return formattedDate;
    }

    /**
     * 将日期字符串转换为时间戳（以秒为单位）
     * @param dateString 日期字符串，格式为 yyyy-MM-dd
     * @return 时间戳（以秒为单位）
     */
    public static long dateStr2Long(String dateString) {
        // 创建日期格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 解析日期字符串为LocalDate对象
        LocalDate date = LocalDate.parse(dateString, formatter);

        // 将日期转换为自1970-01-01以来的天数
        long daysSinceEpoch = ChronoUnit.DAYS.between(LocalDate.ofEpochDay(0), date);

        // 如果需要转换为秒，可以进一步计算
        long secondsSinceEpoch = daysSinceEpoch * 24 * 60 * 60; // 一天有86400秒

        //System.out.println("Days : " + daysSinceEpoch);
        System.out.println("Seconds: " + secondsSinceEpoch);
        return secondsSinceEpoch;
    }

    /**
     * 将日期字符串转换为时间戳（以秒为单位）
     * @param dateTimeString 日期字符串，格式为 yyyy-MM-dd HH:mm:ss
     * @return 时间戳（以秒为单位）
     */
    public static long dateTimeStr2Long(String dateTimeString) {
        // 创建日期格式化对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        /// 解析日期时间字符串为LocalDateTime对象
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        // 将日期时间转换为自1970-01-01以来的秒数
        long secondsSinceEpoch = dateTime.toEpochSecond(ZoneOffset.UTC);
        System.out.println("Seconds : " + secondsSinceEpoch);
        // 不知道什么bug，会多8小时，所以需要处理一下
        //long epochMilli = dateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() / 1000;
        //System.out.println("epochMilli : " + epochMilli);
        return secondsSinceEpoch;
    }
}
