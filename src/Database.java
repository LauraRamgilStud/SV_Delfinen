import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Database {

    private static ArrayList<Member> memberList = new ArrayList<>();

    public static void addStandardMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n============ ENTER =============\n=                              =\n============= NAME =============\n");
        String name = scanner.nextLine();
        System.out.println("============ BIRTHDAY ============\n=          [DD-MM-YYYY]          =\n");
        String inputBirthday = scanner.nextLine();
        System.out.println("============ STATUS ============\n=   [1] active   [2] passive   =\n");
        int inputStatus = scanner.nextInt();
        boolean status = false;
        if(inputStatus == 1){
            status = true;
        } else if(inputStatus == 2){
            status = false;
        }
        memberList.add(new Member(name, inputBirthday, status));
        updateDBFile();
        System.out.println("========== SUCCESSFUL ==========");
    }

    public static void addCompetitiveMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n============ ENTER =============\n=                              =\n============= NAME =============\n");
        String name = scanner.nextLine();
        System.out.println("=========== BIRTHDAY ===========\n=         [DD-MM-YYYY]         =\n");
        String inputBirthday = scanner.nextLine();
        System.out.println("============ STATUS ============\n=   [1] active   [2] passive   =\n");
        int inputStatus = scanner.nextInt();
        boolean status = false;
        if(inputStatus == 1){
            status = true;
        } else if(inputStatus == 2){
            status = false;
        }
        System.out.println("=========== DISCIPLINE =============\n= [1] backcrawl    [2] crawl       =\n= [3] breaststroke [4] butterfly   =\n");
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
        System.out.println("========== SUCCESSFUL ==========");
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
    }

    public static void removeMemberByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Enter name of member to remove ====");
        String name = scanner.nextLine();
        Member memberToDelete = null;
        boolean sentinel = true;

        while(sentinel) {
            try{
                for(Member m: memberList){
                    if(m.getName().equals(name)){
                        memberToDelete = m;
                    }
                }
                if(memberToDelete != null){
                    System.out.println("= Delete member "+name+" \n= [1] Yes\n= [2] No");
                    if(scanner.hasNextInt()){
                        int delete = scanner.nextInt();
                        if(delete == 1){
                            memberList.remove(memberToDelete);
                            System.out.println("==== Member "+name+" has been removed =====");
                            sentinel = false;
                        }else if(delete == 2){
                            System.out.println("==== Cancellation of removal of member "+name+" ====");
                            sentinel = false;
                        }
                    }else{
                        scanner.nextLine();
                        System.out.println("\n========= INVALID INPUT ==========\n=         Enter [1] or [2]         =\n==================================\n");
                    }
                }else {
                    System.out.println("==== Could not find member "+name+" ====");
                    System.out.println("==== Enter name of member to remove =====");
                    name = scanner.nextLine();
                }


            }catch(Exception e){
                System.out.println("\n========= INVALID INPUT ==========\n=         Enter [1] or [2]         =\n==================================\n");
            }

        }
        updateDBFile();
    }

    public static void printMemberList (){
            System.out.println("===== List of members =====\n");
            for(Member member : memberList){
                System.out.println(member.toString());
            }
    }

    public static void updateDBFile(){
        try{
            File fileReadOnly = new File("memberDBReadOnly.txt");
            File file = new File("memberDB.txt");
            PrintStream outputReadOnly = new PrintStream(fileReadOnly);
            PrintStream output = new PrintStream(file);

            for(Member m: memberList){
                outputReadOnly.println(toStringReadOnlyMembers(m));
                output.println(m.toString());
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

   public static void readFile(){
       Scanner scanFile = null;
       try {
           scanFile = new Scanner(new File("memberDBReadOnly.txt"));
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       }

       while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            Scanner scanLine = new Scanner(line);
            scanLine.useDelimiter("/");
            while(scanLine.hasNext()) {
                String num = scanLine.next();
                if(num.equals("1")){
                    String name = scanLine.next();
                    String birthday = scanLine.next();
                    boolean status = scanLine.nextBoolean();
                    boolean hasPayed = scanLine.nextBoolean();
                    Member member = new Member(name, birthday, status);
                    member.setHasPaid(hasPayed);
                    memberList.add(member);
                }else{
                    String name = scanLine.next();
                    String birthday = scanLine.next();
                    boolean status = scanLine.nextBoolean();
                    Discipline discipline = Discipline.valueOf(scanLine.next());
                    boolean hasPayed = scanLine.nextBoolean();
                    CompSwimmer compSwimmer = new CompSwimmer(name, birthday, status, discipline);
                    compSwimmer.setHasPaid(hasPayed);
                    memberList.add(compSwimmer);
                }
            }
       }
   }

    public static String toStringReadOnlyMembers(Object object){
        if(object instanceof CompSwimmer){
            CompSwimmer compSwimmer = (CompSwimmer) object;
            return 2 + "/" + compSwimmer.getName() + "/" + compSwimmer.getBirthday() + "/" + compSwimmer.getMembership().getStatus() + "/" + compSwimmer.getDiscipline() + "/" + compSwimmer.getHasPaid();
        } else {
            Member member = (Member) object;
            return 1 + "/" + member.getName() + "/" + member.getBirthday() + "/" + member.getMembership().getStatus() + "/" + member.getHasPaid();
        }
    }

    public static void updateEventFile(){
        try{
            File eventsFile = new File("eventsFile.txt");
            PrintStream output = new PrintStream(eventsFile);

            for(Member m: memberList){
                if(m instanceof CompSwimmer){
                    for(Event e: ((CompSwimmer) m).getEventList()){
                        output.println(m.getName()+"/"+e.getName()+"/"+e.getStringDate()+"/"+e.getPositionPlaced()+"/"+e.getTime());
                    }
                }
            }
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void readEventFile(){
        try{
            File eventsFile = new File("eventsFile.txt");
            Scanner scanFile = new Scanner(eventsFile);

            while(scanFile.hasNextLine()){
                String line = scanFile.nextLine();
                Scanner scanLine = new Scanner(line);
                scanLine.useDelimiter("/");
                while(scanLine.hasNext()){
                    String name = scanLine.next();
                    String eventName = scanLine.next();
                    String date = scanLine.next();
                    int position = scanLine.nextInt();
                    String time = scanLine.next();
                    for(Member m: memberList){
                        if(m.getName().equals(name)){
                            ((CompSwimmer)m).addEvent(eventName, date);
                            ((CompSwimmer)m).addTimeAndPosition(eventName, position, time);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void updateTrainingFile(){
        try{
            File trainingsFile = new File("trainingsFile.txt");
            PrintStream output = new PrintStream(trainingsFile);

            for(Member m: memberList){
                if(m instanceof CompSwimmer){
                    for(Training t: ((CompSwimmer) m).getTrainingList()){
                        output.println(m.getName()+"/"+t.getStringDate()+"/"+t.getTime());
                    }
                }
            }
            output.close();
        } catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }
    public static void readTrainingFile(){
        try{
            File trainingFile = new File("trainingsFile.txt");
            Scanner scanFile = new Scanner(trainingFile);

            while(scanFile.hasNextLine()){
                String line = scanFile.nextLine();
                Scanner scanLine = new Scanner(line);
                scanLine.useDelimiter("/");
                while(scanLine.hasNext()){
                    String name = scanLine.next();
                    String date = scanLine.next();
                    String time = scanLine.next();
                    for(Member m: memberList){
                        if(m.getName().equals(name)){
                            ((CompSwimmer)m).addTraining(date, time);
                        }
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

}