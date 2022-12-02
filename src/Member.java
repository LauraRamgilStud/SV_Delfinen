import java.util.Scanner;
public class Member {
    private String name;
    private int age;
    boolean status;
    private Membership membership;
    Scanner scanner = new Scanner(System.in);

    public Member(String name, int age, boolean status) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.membership = new Membership(status, age);

    }
}