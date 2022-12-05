import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Scanner;
public class Database {

    private double sum = 0;
    static ArrayList<Member> memberList = new ArrayList<>();
    public static int calculateAge(LocalDate birthDate){
        LocalDate currentDate = LocalDate.now(); //obtains actual time from systemClock in default Time Zone

        if((birthDate != null) && (currentDate != null)){
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
    public static void addMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================\nPlease enter\nName: ");
        String name = scanner.nextLine();
        System.out.println("Birthday: ");
        String inputBirthday = scanner.nextLine();
        LocalDate birthDate = LocalDate.parse(inputBirthday);       //the parse() method obtains an instance of LocalDate from a text string such as 1992-08-11
        int age = calculateAge(birthDate);
        System.out.println("Status: ");
        boolean status = scanner.nextBoolean();
        memberList.add(new Member(name, age, status));
    }

    /*public void removeMember(String name){
        for(Member member : memberList){
            memberList.removeIf()
        }
    }*/
    public static void printMemberList (){
        System.out.println("===== List of members =====\n");
        for(int i = 0 ; i < memberList.size() ; i++){
            System.out.println(memberList.get(i));
        }
    }
}