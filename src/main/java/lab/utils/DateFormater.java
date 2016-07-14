package lab.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormater {
    public static final String DATE_FORMAT = "yyyy.MM.dd";
    public static SimpleDateFormat dateFormater = new SimpleDateFormat(DATE_FORMAT);

    private DateFormater(){
    }

    public static String simpleDateFormat(Date date) {
        return dateFormater.format(date);
    }

    public static Date parseDate(String date) {
        Date parsedDate = null;
        try {
            parsedDate = dateFormater.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parsedDate;
    }
}
