import java.util.Scanner;
public class Member {
    private boolean isCompetitive;
    private String name;
    private String birthday;
    private boolean status;
    private boolean hasPaid;
    private Membership membership;

    public String toString() {
        return
                "Name: " + name +
                "\nBirthday: " + birthday +
                "\nActive: " + status +
                "\nPaid: " + hasPaid +
                "\nFee:" + membership +
                "\n========================";
    }

    public Member(String name, String birthday, boolean status) {
        this.name = name;
        this.birthday = birthday;
        this.status = status;
        this.membership = new Membership(status, birthday);
    }

    public void payFee(){
        hasPaid = true;
    }

    public void openFee(){
        hasPaid = false;
    }

    public String getName(){
        return name;
    }

    public boolean isCompetitive() {
        return isCompetitive;
    }

    public String getBirthday() {
        return birthday;
    }

    public boolean getStatus(){
        return status;
    }
}