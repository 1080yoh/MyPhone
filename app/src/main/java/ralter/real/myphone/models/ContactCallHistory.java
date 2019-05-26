package ralter.real.myphone.models;

import java.util.Date;

public class ContactCallHistory {
    private int avatar;
    private String contactName;
    private String phoneNumber;
    private String duration;

    private Date date;
    private int type;

    public ContactCallHistory(int avatar, String contactName, String phoneNumber, String duration, Date date, int type) {
        this.avatar = avatar;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.duration = duration;
        this.date = date;
        this.type = type;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
