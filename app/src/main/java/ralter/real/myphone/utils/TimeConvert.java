package ralter.real.myphone.utils;

import java.text.DecimalFormat;

public class TimeConvert {
    private static DecimalFormat decimalFormat = new DecimalFormat(".#");

    public static String fromSecToMinutes(long sec) {
        if (sec > 60) {
            StringBuilder stringBuilder = new StringBuilder();
            double min = sec / 60.0;
            stringBuilder.append(decimalFormat.format(min));
            stringBuilder.append(" phút");
            return stringBuilder.toString();
        } else if (sec == 60) {
            return "1 phút";
        } else {
            return sec + " giây";
        }
    }
}
