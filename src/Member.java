import java.util.Scanner;
public class Member {
    private String name;
    private String birthday;
    private boolean status;
    private boolean hasPaid;
    private boolean isCompetitive;
    private Membership membership;

    public String toString() {
        return
                "Name: " + getName() +
                "\nBirthday: " + getBirthday() +
                "\nActive: " + getStatus() +
                "\nPaid: " + getHasPaid() +
                "\nFee:" + getMembership().getFee() +
                "\n========================";
    }
    public Member(String name, String birthday, boolean status) {
        this.name = name;
        this.birthday = birthday;
        this.status = status;
        this.membership = new Membership(status, birthday);
    }
    public boolean getHasPaid() { return hasPaid; }
    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }
    public String getName(){ return name; }
    public boolean isCompetitive() { return isCompetitive; }
    public String getBirthday() { return birthday; }
    public boolean getStatus(){ return status; }
    public Membership getMembership() { return membership; }
}