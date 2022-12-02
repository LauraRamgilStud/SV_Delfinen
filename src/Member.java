import java.util.Scanner;
public class Member {
    private String name;
    private int age;
    //Vi behøver ikke have status som attribute her
    boolean status;
    private Membership membership;
    //Scanner bliver ikke brugt, hvad er tanken med den?
    Scanner scanner = new Scanner(System.in);

    public Member(String name, int age, boolean status) {
        this.name = name;
        this.age = age;
        this.status = status;
        this.membership = new Membership(status, age);

    }


    //Jeg skulle lige bruge denne
    public String getName() {
        return name;
    }
}