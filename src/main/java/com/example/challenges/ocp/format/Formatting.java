package com.example.challenges.ocp.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Formatting {
    public static void main(String[] args) throws ParseException {
        double number = 12345.6789;
        NumberFormat format = new DecimalFormat("#,###,###.0");
        System.out.println(format.format(number));

        LocalDate date = LocalDate.of(2024, Month.JULY, 28);
        LocalTime time = LocalTime.of(18, 34, 50);
        LocalDateTime dt = LocalDateTime.of(date, time);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        var df1 = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        System.out.println(df1.format(dt));
        System.out.println(dt.format(df1));
        var df2 = DateTimeFormatter.ofPattern("MMM-dd-yy HH:mm:ss");
        System.out.println(dt.format(df2));
        var df3 = DateTimeFormatter.ofPattern("MMMM, dd yyyy hh:mm:ss a");
        System.out.println(df3.format(dt));

        Locale locale = Locale.getDefault();
        System.out.println(locale);

        System.out.println(Locale.GERMANY);
        System.out.println(Locale.JAPANESE);
        System.out.println(Locale.JAPAN);
        System.out.println(new Locale("aa", "ABCCC"));
        System.out.println(new Locale.Builder().setLanguage("en").setRegion("AB").build());

        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(number));

        var get = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(get.format(number));

        var canFr = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(canFr.format(number));

        var usCurrency = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(usCurrency.format(number));

        var japanCurrency = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.println(japanCurrency.format(number));

        var ukCurrency = NumberFormat.getCurrencyInstance(Locale.UK);
        System.out.println(ukCurrency.format(number));

        var gerCurrency = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println(gerCurrency.format(number));

        double percent = 0.15677;
        var usPercent = NumberFormat.getPercentInstance(Locale.US);
        System.out.println(usPercent.format(percent));

        var gerPercent = NumberFormat.getPercentInstance(Locale.GERMANY);
        System.out.println(gerPercent.format(percent));

        int longNumber = 8_765_432;
        var usShortCompactNumber = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.SHORT);
        var usLongCompactNumber = NumberFormat.getCompactNumberInstance(Locale.US, NumberFormat.Style.LONG);
        System.out.println(usShortCompactNumber.format(longNumber));
        System.out.println(usLongCompactNumber.format(longNumber));

        var frShortCompactNumber = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.SHORT);
        var frLongCompactNumber = NumberFormat.getCompactNumberInstance(Locale.FRANCE, NumberFormat.Style.LONG);
        System.out.println(frShortCompactNumber.format(longNumber));
        System.out.println(frLongCompactNumber.format(longNumber));

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        Locale mxLocale = new Locale("es", "MX");
        System.out.println(dateTimeFormatter.withLocale(mxLocale).format(dt));

        var enResourceBundle = ResourceBundle.getBundle("Museum", Locale.ENGLISH);
        var frResourceBundle = ResourceBundle.getBundle("Museum", Locale.FRENCH);
        System.out.printf("%s! %s%n", enResourceBundle.getString("greeting"), enResourceBundle.getString("open"));
        System.out.printf("%s! %s%n", frResourceBundle.getString("greeting"), frResourceBundle.getString("open"));

        Locale hrLocale = new Locale("es", "HR");
        System.out.println(hrLocale.getDisplayCountry());
        System.out.println(hrLocale.getDisplayLanguage());
        System.out.println(new Locale("aa", "ABCCC").getDisplayCountry());
    }
}
