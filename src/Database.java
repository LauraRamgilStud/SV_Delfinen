import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
public class Database {

    private static ArrayList<Member> memberList = new ArrayList<>();

    public static void addStandardMember(){
        Scanner scanner = new Scanner(System.in);
        /*System.out.println("Is competitive?");
        boolean comp = scanner.nextBoolean();*/
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
        //intage det samme + disciplin
        //Ud fra disciplin, tilføj til respektive coach's arraylist
        //updateDBFile
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
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

    public static void updateDBFile(){
        try{
            File f = new File("memberDB.txt");
            PrintStream output = new PrintStream(f);
            for(Member m: memberList){
                output.println(m.getName() + " " + m.getBirthday() + " " + m.getStatus());
            }

        } catch(FileNotFoundException e){
            e.printStackTrace();
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
                //boolean isCompetitive = scanLine.nextBoolean();
                String name = scanLine.next() + " ";
                name += scanLine.next();
                String birthday = scanLine.next();
                boolean status = scanLine.nextBoolean();
                memberList.add(new Member(name, birthday, status));
            }
        }
    }
}