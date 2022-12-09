import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
public class Database{

    private static ArrayList<Member> memberList = new ArrayList<>();

    public static void addStandardMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ==============================
                =         ENTER NAME         =
                ==============================
                """);
        String name = scanner.nextLine();

        String inputBirthday = getValidDateInput();

        System.out.println("""
                ============STATUS============
                =  [1] ACTIVE   [2] PASSIVE  =
                ==============================
                """);
        int inputStatus = scanner.nextInt();
        boolean status = false;
        if(inputStatus == 1){
            status = true;
        } else if(inputStatus == 2){
            status = false;
        }
        memberList.add(new Member(name, inputBirthday, status));
        updateDBFile();
        System.out.println("==========SUCCESSFUL==========");
    }

    public static void addCompetitiveMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ==============================
                =         ENTER NAME         =
                ==============================
                """);
        String name = scanner.nextLine();
        String inputBirthday = getValidDateInput();
        System.out.println("""
                ============STATUS============
                =  [1] ACTIVE   [2] PASSIVE  =
                ==============================
                """);
        int inputStatus = 0;
        boolean status = false;
        while(inputStatus == 0){
            if(scanner.hasNextInt()) {
                inputStatus = scanner.nextInt();
                if(inputStatus == 1){
                    status = true;
                } else if(inputStatus == 2){
                    status = false;
                }
            }else{
                scanner.nextLine();
                System.out.println("""
                        ======== INVALID INPUT =========
                        =       Enter [1] or [2]       =
                        ================================
                        """);
            }
        }

        System.out.println("""
                ===========DISCIPLINE=============
                = [1] BACKCRAWL    [2] CRAWL     =
                = [3] BREASTSTROKE [4] BUTTERFLY =
                ==================================
                """);
        int dis = 0;
        Discipline discipline = null;
        while(dis == 0) {
            if(scanner.hasNextInt()){
                dis = scanner.nextInt();
                if (dis == 1) {
                    discipline = Discipline.BACKCRAWL;
                } else if (dis == 2) {
                    discipline = Discipline.CRAWL;
                } else if (dis == 3) {
                    discipline = Discipline.BREASTSTROKE;
                } else if (dis == 4) {
                    discipline = Discipline.BUTTERFLY;
                }
            }else{
                System.out.println("""
                        ======== INVALID INPUT =========
                        =  Enter [1], [2], [3] or [4]  =
                        ================================
                        """);
                scanner.nextLine();
            }
        }
        memberList.add(new CompSwimmer(name, inputBirthday, status, discipline));
        updateDBFile();
        System.out.println("==========SUCCESSFUL==========");
    }

    public static ArrayList<Member> getMemberList() {
        return memberList;
    }

    public static void removeMemberByName(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name of member to remove");
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
                    System.out.println("Delete member "+name+"\n[1] Yes\n[2] No");
                    if(scanner.hasNextInt()){
                        int delete = scanner.nextInt();
                        if(delete == 1){
                            memberList.remove(memberToDelete);
                            System.out.println("Member "+name+" has been removed");
                            sentinel = false;
                        }else if(delete == 2){
                            System.out.println("Cancellation of removal of member "+name);
                            sentinel = false;
                        }
                    }else{
                        scanner.nextLine();
                        System.out.println("""
                        ======== INVALID INPUT =========
                        =       Enter [1] or [2]       =
                        ================================
                        """);
                    }
                }else {
                    System.out.println("Could not find member "+name);
                    System.out.println("Enter name of member to remove");
                    name = scanner.nextLine();
                }


            }catch(Exception e){
                System.out.println("Invalid input");
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

    public static String getValidDateInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                ===========BIRTHDAY===========
                =         dd-mm-yyyy         =
                ==============================
                """);
        String inputBirthday = scanner.nextLine();
        while(!isValid(inputBirthday)) {
            System.out.println("""
                ================================
                =        Invalid input         =
                =   Enter format: dd-mm-yyyy   =
                ================================
                """);
            inputBirthday = scanner.nextLine();
        }

        return inputBirthday;
    }

    public static boolean isValid(String dateStr) {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
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