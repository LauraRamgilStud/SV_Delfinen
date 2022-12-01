import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;
public class Member {
    private String name;
    private int age;

    boolean status;
    private Membership membership;
    Scanner scanner = new Scanner(System.in);
    String birthday = scanner.nextLine();
    LocalDate localDate = LocalDate.parse(birthday);

    public static int calculateAge(LocalDate localDate){
        LocalDate currentDate = LocalDate.now();

        if((localDate != null) && (currentDate != null)){
            return Period.between(localDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public Member(String name, int age, boolean status) {
        this.name = name;
        this.age = age;
        this.status = status;
    }

    }
