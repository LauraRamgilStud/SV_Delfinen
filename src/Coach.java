import java.util.ArrayList;
import java.util.Scanner;

public class Coach extends Employee{
    private Discipline discipline;
    private static ArrayList<CompSwimmer> studentsList = new ArrayList<>();
    public Coach(String id, Discipline discipline){
        super(id);
        this.discipline = discipline;
    }
    public void coachMenu(){
        System.out.println("\n====== MENU =====");
        System.out.println("[1] View student list");
        System.out.println("[2] Add, edit or delete training");
        System.out.println("[3] Add, edit or delete event");
        System.out.println("[0] Log Out");
        System.out.println("=================\n");

        Scanner scanner = new Scanner(System.in);
        int input; scanner.nextInt();

        while (input != 0){
            switch (input){
                case 1:
                    //studentList
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