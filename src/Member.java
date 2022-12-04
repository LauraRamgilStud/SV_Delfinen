import java.util.Scanner;
public class Member {
    private String name;
    private int age;
    private boolean status;
    private boolean hasPaid;
    private Membership membership;

    public String toString() {
        return "===== List of members =====\n" +
                "Name: " + name +
                "\nAge: " + age +
                "\nActive: " + status +
                "\nPaid: " + hasPaid //+
               // "\nFee: " + Membership.getFee();
                ;
    }

    public Member(String name, int age, boolean status) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.membership = new Membership(status, age);
    }
}