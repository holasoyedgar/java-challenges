package com.example.challenges.ocp.dateandtime;

import java.time.*;

public class DateAndTimeExamples {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDate ld1 = LocalDate.of(2026, Month.APRIL, 10);
        System.out.println(ld1);

        LocalDate ld2 = LocalDate.of(2026, 4, 10);
        System.out.println(ld2);

        LocalTime nowLt = LocalTime.now();
        System.out.println(nowLt);

        LocalTime lt1 = LocalTime.of(13, 55, 29, 145);
        System.out.println(lt1);

        LocalDateTime nowLdt = LocalDateTime.now();
        System.out.println(nowLdt);

        LocalDateTime ldt1 = LocalDateTime.of(2025, Month.APRIL, 10, 13, 55, 29, 145);
        System.out.println(ldt1);

        LocalDateTime composedLdt = LocalDateTime.of(now, nowLt);
        System.out.println(composedLdt);

        ZoneId zoneId = ZoneId.of("America/Mexico_City");
        ZonedDateTime zdt = ZonedDateTime.of(2026, 4, 10, 13, 59, 38, 123, zoneId);

        System.out.println(zdt);

        ZonedDateTime gmtZdt = ZonedDateTime.of(zdt.getYear(), zdt.getMonthValue(), zdt.getDayOfMonth(), zdt.getHour() + 5, zdt.getMinute(), zdt.getSecond(), zdt.getNano(), ZoneId.of("GMT"));
        System.out.println(gmtZdt);

        Period period3Days = Period.ofDays(3);
        System.out.println("Period: " + period3Days);
        System.out.println(now.plus(period3Days));
        System.out.println(now.minus(period3Days));

        Period period1Week = Period.ofWeeks(5);
        System.out.println("Period: " + period1Week);
        System.out.println(now.plus(period1Week));
        System.out.println(now.minus(period1Week));

        Duration duration5Days = Duration.ofMinutes(15);
        System.out.println("Duration: " + duration5Days);
        System.out.println(nowLt.plus(duration5Days));
        System.out.println(nowLt.minus(duration5Days));

        Instant nowInstant = Instant.now();
        System.out.println(nowInstant);

        Instant zonedInstant = zdt.toInstant();
        System.out.println(zonedInstant);
    }
}
