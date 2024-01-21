package Task6;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Time {
   public static LocalDate date = LocalDate.now();
   public static LocalDateTime localDateTime = LocalDateTime.now();
   public static String dateTimeCreate = date + " " + localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
    public static String timeToLunch = localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
}
