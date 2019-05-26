package ralter.real.myphone.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateUtils {
    public static int compareDay(Date date1, Date date2) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String sd1 = simpleDateFormat.format(date1);
        String sd2 = simpleDateFormat.format(date2);

        String[] d1 = sd1.split("-");
        String[] d2 = sd2.split("-");

        int day1 = Integer.parseInt(d1[0]);
        int month1 = Integer.parseInt(d1[1]);
        int year1 = Integer.parseInt(d1[2]);

        int day2 = Integer.parseInt(d2[0]);
        int month2 = Integer.parseInt(d2[1]);
        int year2 = Integer.parseInt(d2[2]);

        if (year1 - year2 == 0) {
            if (month1 - month2 == 0) {
                if (day1 - day2 == 0)
                    return 0;
                else
                    return day1 - day2;
            } else
                return month1 - month2;
        } else
            return year1 - year2;
    }
}
