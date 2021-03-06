package ralter.real.myphone.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ralter.real.myphone.exceptions.DateSaiException;

public class DateSQL {

    /**
     * Chuyển string sang kiểu date SQL
     *
     * @param input String định dạng dd/MM/yyyy or yyyy/MM/dd or dd-MM-yyyy or
     *              yyyy-MM-dd
     * @return SQLDate
     * @throws DateSaiException
     */
    public static Date parseDate(String input) throws DateSaiException {
        String[] data;
        if (input.indexOf('/') != -1) {
            data = input.split("/");
        } else {
            data = input.split("-");
        }
        if (data.length != 3)
            throw new DateSaiException();

        try {
            int year = 0, month = 0, day = 0;
            if (data[0].length() == 4) {
                year = Integer.parseInt(data[0]);
                month = Integer.parseInt(data[1]);
                day = Integer.parseInt(data[2]);
            } else if (data[2].length() == 4) {
                year = Integer.parseInt(data[2]);
                month = Integer.parseInt(data[1]);
                day = Integer.parseInt(data[0]);
            }

            if (year < 1900)
                throw new DateSaiException();

            // Check ngày, tháng
            switch (month) {
                case 1:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 2:
                    if (day < 1)
                        throw new DateSaiException();
                    else if ((year % 4 != 0 || (year % 100 == 0 && year % 400 != 0)) && day > 28)
                        throw new DateSaiException();
                    else if (day > 29)
                        throw new DateSaiException();
                    else
                        break;
                case 3:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 4:
                    if (day > 30 || day < 1)
                        throw new DateSaiException();
                    break;
                case 5:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 6:
                    if (day > 30 || day < 1)
                        throw new DateSaiException();
                    break;
                case 7:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 8:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 9:
                    if (day > 30 || day < 1)
                        throw new DateSaiException();
                    break;
                case 10:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                case 11:
                    if (day > 30 || day < 1)
                        throw new DateSaiException();
                    break;
                case 12:
                    if (day > 31 || day < 1)
                        throw new DateSaiException();
                    break;
                default:
                    throw new DateSaiException();
            }

            SimpleDateFormat fm = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date date = fm.parse(year + "/" + month + "/" + day);
            Date d = new Date(date.getTime());
            return d;
        } catch (NumberFormatException e) {
            throw new DateSaiException();
        } catch (ParseException e) {
            throw new DateSaiException();
        }
    }

    public static int Compare(Date date1, Date date2) {
        String[] data1 = date1.toString().split("-");
        String[] data2 = date2.toString().split("-");

        int year1 = Integer.parseInt(data1[0]);
        int month1 = Integer.parseInt(data1[1]);
        int day1 = Integer.parseInt(data1[2]);

        int year2 = Integer.parseInt(data2[0]);
        int month2 = Integer.parseInt(data2[1]);
        int day2 = Integer.parseInt(data2[2]);

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

    public static String toVNDate(Date date) {
        String[] data = date.toString().split("-");
        int year = Integer.parseInt(data[0]);
        int month = Integer.parseInt(data[1]);
        int day = Integer.parseInt(data[2]);
        return day + "-" + month + "-" + year;
    }
}
