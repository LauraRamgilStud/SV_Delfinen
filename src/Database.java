import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Database {

    private static ArrayList<Member> memberList = new ArrayList<>();

    public static void addStandardMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================\nPlease enter\nName: ");
        String name = scanner.nextLine();
        System.out.println("Birthday: ");
        String inputBirthday = scanner.nextLine();
        System.out.println("Status: ");
        //intast nummer 1 eller 2 i stedet for
        boolean status = scanner.nextBoolean();
        memberList.add(new Member(name, inputBirthday, status));
        updateDBFile();
    }

    public static void addCompetitiveMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("====================\nPlease enter\nName: ");
        String name = scanner.nextLine();
        System.out.println("Birthday: ");
        String inputBirthday = scanner.nextLine();
        System.out.println("Status: ");
        //intast nummer 1 eller 2 i stedet for
        boolean status = scanner.nextBoolean();
        System.out.println("Discipline [1. Backcrawl], [2. Crawl], [3. Breaststroke] or [4. Butterfly]: ");
        int dis = scanner.nextInt();
        Discipline discipline = null;
        if(dis == 1){
            discipline = Discipline.BACKCRAWL;
        } else if(dis == 2){
            discipline = Discipline.CRAWL;
        } else if(dis == 3){
            discipline = Discipline.BREASTSTROKE;
        } else if(dis == 4){
            discipline = Discipline.BUTTERFLY;
        }
        memberList.add(new CompSwimmer(name, inputBirthday, status, discipline));
        updateDBFile();
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
    }
    public static void removeMemberByName(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter name: ");
    String input = scanner.nextLine();
        for(Member member : Database.getMemberList()){
        if(input.equals(member.getName())){
            Database.getMemberList().remove(member);
            updateDBFile();
        }
    }
}


        public static void printMemberList (){
            System.out.println("===== List of members =====\n");
            for(Member member : memberList){
                member.printMember();
            }
            }



    public static void updateDBFile(){
        try{
            File fileReadOnly = new File("memberDBReadOnly.txt");
            File file = new File("memberDB.txt");
            PrintStream outputReadOnly = new PrintStream(fileReadOnly);
            PrintStream output = new PrintStream(file);

            for(Member m: memberList){
                outputReadOnly.println(toStringReadOnly(m));
                //find ud af dette hehe
                //output.println(m.printMember());
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static String toStringReadOnly(Object object){
        if(object instanceof CompSwimmer){
            CompSwimmer compSwimmer = (CompSwimmer) object;
            return 2 + "\n" + compSwimmer.getName() + "\n" + compSwimmer.getBirthday() + "\n" + compSwimmer.getStatus() + "\n" + compSwimmer.getDiscipline() + "\n" + compSwimmer.getHasPaid();
        } else {
            Member member = (Member) object;
            return 1 + "\n" + member.getName() + "\n" + member.getBirthday() + "\n" + member.getStatus() + "\n" + member.getHasPaid();
        }
    }

   public static void readFile(){Scanner scanFile = null;
       try {
           scanFile = new Scanner(new File("memberDBReadOnly.txt"));
      } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }
       while(scanFile.hasNextLine()){
            //while(!scanFile.nextLine().isEmpty()){
                String num = scanFile.nextLine();
                String name = scanFile.nextLine();
                String birthday = scanFile.nextLine();
                boolean status = Boolean.parseBoolean(scanFile.nextLine());
                if(num.equals("2")){
                    Discipline discipline = Discipline.valueOf(scanFile.nextLine());
                    boolean hasPaid = scanFile.nextBoolean();
                    CompSwimmer compSwimmer = new CompSwimmer(name, birthday, status, discipline);
                    compSwimmer.setHasPaid(hasPaid);
                    memberList.add(compSwimmer);
                } else {
                    boolean hasPaid = scanFile.nextBoolean();
                    Member member = new Member(name, birthday, status);
                    member.setHasPaid(hasPaid);
                    memberList.add(member);
                }
           // }
        }
    }
}