import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class Coach extends Employee {
    private Discipline discipline;
    private static ArrayList<CompSwimmer> sCrawlList = new ArrayList<>();
    private static ArrayList<CompSwimmer> jCrawlList = new ArrayList<>();
    private static ArrayList<CompSwimmer> sBackcrawlList = new ArrayList<>();
    private static ArrayList<CompSwimmer> jBackcrawlList = new ArrayList<>();
    private static ArrayList<CompSwimmer> sButterflyList = new ArrayList<>();
    private static ArrayList<CompSwimmer> jButterflyList = new ArrayList<>();
    private static ArrayList<CompSwimmer> sBreaststrokeList = new ArrayList<>();
    private static ArrayList<CompSwimmer> jBreaststrokeList = new ArrayList<>();


    BufferedReader reader = new BufferedReader(new FileReader("crawlStudents.txt"));
    BufferedWriter writer = new BufferedWriter(new FileWriter("crawlStudents.txt"));

    public Coach(String id, Discipline discipline) {
        super(id);
        this.discipline = discipline;
    }

    public void pubulateCoachStudentList() {
        /*noget med database.getMemberList*/
        //while((Member member = reader.readMember())!= null) {
        for (Member member : Database.getMemberList()) {
            if (member.getStatus() && member instanceof CompSwimmer) {
                int age = Membership.getAge(member.getBirthday());
                Discipline memberDiscipline = ((CompSwimmer) member).getDiscipline();
                if (age >= 18) {  //if (member.getBirthday())
                    if (memberDiscipline == Discipline.CRAWL && getDiscipline() == Discipline.CRAWL) {
                        sCrawlList.add((CompSwimmer) member);
                        Writer.write(String.valueOf(sCrawlList));
                        Writer.close();
                    }
                    if (memberDiscipline == Discipline.BACKCRAWL && getDiscipline() == Discipline.BACKCRAWL) {
                        sBackcrawlList.add((CompSwimmer) member);
                    }
                    if (memberDiscipline == Discipline.BUTTERFLY && getDiscipline() == Discipline.BUTTERFLY) {
                        sButterflyList.add((CompSwimmer) member);
                    }
                    if (memberDiscipline == Discipline.BREASTSTROKE && getDiscipline() == Discipline.BREASTSTROKE) {
                        sBreaststrokeList.add((CompSwimmer) member);
                    }
                } else {
                    if (memberDiscipline == Discipline.CRAWL && getDiscipline() == Discipline.CRAWL) {
                        jCrawlList.add((CompSwimmer) member);
                    }
                    if (memberDiscipline == Discipline.BACKCRAWL && getDiscipline() == Discipline.BACKCRAWL) {
                        jBackcrawlList.add((CompSwimmer) member);
                    }
                    if (memberDiscipline == Discipline.BUTTERFLY && getDiscipline() == Discipline.BUTTERFLY) {
                        jButterflyList.add((CompSwimmer) member);
                    }
                    if (memberDiscipline == Discipline.BREASTSTROKE && getDiscipline() == Discipline.BREASTSTROKE) {
                        jBreaststrokeList.add((CompSwimmer) member);
                    }
                }
            }
        }
        //}
        //reader.close(); /* https://stackoverflow.com/questions/5343689/java-reading-a-file-into-an-arraylist */
    }


    public void coachMenu() {
        System.out.println("\n====== MENU =====");
        System.out.println("[1] View student list");
        System.out.println("[2] Add, edit or delete training");
        System.out.println("[3] Add, edit or delete event");
        System.out.println("[0] Log Out");
        System.out.println("=================\n");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0) {
            switch (input) {
                case 1:
                    //studentList
                    printCoachStudentList();
                    break;
                case 2:
                    //training
                    break;
                case 3:
                    //event
                    break;
                default:
                    //leave
                    break;
            }
            System.out.println("\n====== MENU =====");
            System.out.println("[1] View student list");
            System.out.println("[2] Add, edit or delete training");
            System.out.println("[3] Add, edit or delete event");
            System.out.println("[0] Log Out");
            System.out.println("=================\n");
        }

        //input scanner.nextInt();

        /*Lave Menu for coach
            Han skal kunne:
            - view student list
            - tilføje, redigere og delete training
            - tilføje, redigere og delete event
            - logout til hovedmenu (MAIN)

            Valgene skal være ud fra tal, altså [view list, press 1]*/
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }
}