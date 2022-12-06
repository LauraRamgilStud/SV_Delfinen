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
                output.println(m.toString());
            }
            // + hasPaid

        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public static String toStringReadOnly(Object object){
        if(object instanceof CompSwimmer){
            CompSwimmer compSwimmer = (CompSwimmer) object;
            return 2 + " " + compSwimmer.getName() + " " + compSwimmer.getBirthday() + " " + compSwimmer.getStatus() + " " + compSwimmer.getDiscipline();
        } else {
            Member member = (Member) object;
            return 1 + " " + member.getName() + " " + member.getBirthday() + " " + member.getStatus();
        }
    }

   public static void readFile(){
       Scanner scanFile = null;
       try {
           scanFile = new Scanner(new File("memberDB.txt"));
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }

       while(scanFile.hasNextLine()){
            String line = scanFile.nextLine();
            Scanner scanLine = new Scanner(line);
            while(scanLine.hasNext()){
                int nr = scanLine.nextInt();
                if(nr == 1){
                    String name = scanLine.next() + " ";
                    name += scanLine.next();
                    //læs ind som navn indtil der er et tal
                    String birthday = scanLine.next();
                    boolean status = scanLine.nextBoolean();
                    memberList.add(new Member(name, birthday, status));
                } else if(nr == 2){
                    String name = scanLine.next() + " ";
                    name += scanLine.next();
                    String birthday = scanLine.next();
                    boolean status = scanLine.nextBoolean();
                    Discipline dis = Discipline.valueOf(scanLine.next());
                    memberList.add(new CompSwimmer(name, birthday, status, dis));
                }

                /*
                boolean hasPaid = scanLine.nextBoolean();
                Member member = new Member(name, birthday, status);
                member.setHasPaid(hasPaid);
                memberList.add(member);
                 */
            }
        }
    }
}