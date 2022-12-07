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

    //Kig på
    public static void removeMemberByName(){
        System.out.println("Enter name of member to remove");
        Scanner scanner = new Scanner(System.in);
        boolean sentinel = true;
        //memberList.removeIf(m1 -> m1.getName().equals(name));

        while(sentinel){
            try{
                String name = scanner.next();
                for(Member m: memberList){
                    if(m.getName().equals(name)){
                        System.out.println("Member exist in system");
                        System.out.println("Are you sure you want to remove " + name + "?");
                        System.out.println("[1] Yes\n[2] No");
                        while(!scanner.hasNextInt()){
                            //System.out.println("Input not valid: Enter [1] or [2]");
                            if(scanner.nextInt() == 1){
                                memberList.removeIf(m1 -> m1.getName().equals(name));
                            }else{
                                System.out.println("Cancellation of removal of member " + name);
                            }
                            sentinel = false;
                        }
                    }
                }
            }catch(InputMismatchException e){
                System.out.println("Input not valid");
                e.printStackTrace();
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

    public static void updateTrainingAndEventFile(){

                try{
                    File fileReadOnly = new File("trainingAndEventFile.txt");
                    PrintStream outputReadOnly = new PrintStream(fileReadOnly);

                    for(Member m: memberList){
                        outputReadOnly.println(toStringReadOnlyMembers(m));
                    }
                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }


    }

    /*public static String toStringTrainingAndEvents(Object object){
        if(object instanceof CompSwimmer){
            CompSwimmer compSwimmer = (CompSwimmer) object;
            ArrayList<Event> eventList = compSwimmer.getEventList();
            String toReturn = compSwimmer.getName();
            for(Event e: eventList){

            }
        }
    }*/

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