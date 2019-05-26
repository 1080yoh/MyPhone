package ralter.real.myphone.exceptions;

public class DateSaiException extends Exception {
    @Override
    public String getMessage() {
        return "Sai định dạng ngày tháng!";
    }
}
