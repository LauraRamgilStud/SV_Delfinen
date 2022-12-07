import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Array;
import java.io.IOException;
import java.util.*;

public class Coach extends Employee {
    private Discipline discipline;
    private static ArrayList<CompSwimmer> seniorList = new ArrayList<>();
    private static ArrayList<CompSwimmer> juniorList = new ArrayList<>();

    public Coach(String id, Discipline discipline) {
        super(id);
        this.discipline = discipline;
    }
    public void populateCoachStudentList(Discipline discipline) {
        //while((Member member = reader.readMember())!= null) {
        for (Member member : Database.getMemberList()) {
            if (member.getMembership().getStatus() && member instanceof CompSwimmer) {
                int age = member.getMembership().getAge(member.getBirthday());
                Discipline memberDiscipline = ((CompSwimmer) member).getDiscipline();
                if (age >= 18) {  //if (member.getBirthday())
                    if (memberDiscipline == discipline) {
                        seniorList.add((CompSwimmer) member);
                        /*Writer.write(String.valueOf(seniorList));
                        Writer.close();*/
                    }
                } else {
                    if (memberDiscipline == discipline) {
                        juniorList.add((CompSwimmer) member);
                    }

                }
            }
        }
        //reader.close(); /* https://stackoverflow.com/questions/5343689/java-reading-a-file-into-an-arraylist */
    }
    public void coachMenu(Discipline discipline) {
        populateCoachStudentList(discipline);

        System.out.println("\n================ MENU =================");
        System.out.println("= [1] View student list               =");
        System.out.println("= [2] View or add training            ="); //&view som 1
        System.out.println("= [3] View, add, edit or delete event ="); //&view som 1
        System.out.println("= [4] view top 5 swimmers             =");
        System.out.println("= [0] Log Out                         =");
        System.out.println("=======================================\n");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0) {
            switch (input) {
                case 1:
                    printPopulateCoachStudentList();
                    break;
                case 2: //Add training or view trainings
                    System.out.println("Do you want to View [1] or add [2]");
                    input = scanner.nextInt();
                    switch (input){
                        case 1: //view training list
                            printPopulateCoachStudentList();
                            System.out.println("Enter name of swimmer:");
                            Scanner scan1 = new Scanner(System.in);
                            String name1 = scan1.nextLine();
                            CompSwimmer compS1 = getSwimmerByName(name1);
                            compS1.viewTrainings();
                            break;
                        case 2: //add training
                            try{
                                System.out.println("Enter name of swimmer:");
                                Scanner scan2 = new Scanner(System.in);
                                String name2 = scan2.nextLine();
                                System.out.println("Enter Date: [dd-MM-yyyy]");
                                String date = scanner.next();
                                System.out.println("Enter time:");
                                String time = scanner.next();
                                CompSwimmer compS2 = getSwimmerByName(name2);
                                compS2.addTraining(date, time);

                            }catch (Exception e){
                                e.getStackTrace();
                            }

                            break;
                    }
                    break;
                case 3:
                    System.out.println("Do you want to View [1], add [2], edit [3] or delete [4]");
                    input = scanner.nextInt();
                    switch (input){
                        case 1: //view
                            printPopulateCoachStudentList();
                            System.out.println("Enter name of swimmer:");
                            Scanner scan1 = new Scanner(System.in);
                            String name1 = scan1.nextLine();
                            CompSwimmer compS1 = getSwimmerByName(name1);
                            compS1.viewEvents();
                            break;
                        case 2: //add
                            try{
                                System.out.println("Enter name of swimmer:");
                                Scanner scan2 = new Scanner(System.in);
                                String name2 = scan2.nextLine();
                                System.out.println("Enter name of event");
                                String eventName = scanner.next();
                                System.out.println("Enter date of event:");
                                String date = scanner.next();
                                CompSwimmer compS2 = getSwimmerByName(name2);
                                compS2.addEvent(eventName, date);

                            }catch (Exception e){
                                e.getStackTrace();
                            }
                            break;
                        case 3:
                            //edit
                            System.out.println("Do you want to add time [1], position [2] or both [3]");
                            input = scanner.nextInt();
                            switch (input){
                                case 1: //edit time
                                    System.out.println("Enter name of swimmer:");
                                    Scanner scan3 = new Scanner(System.in);
                                    String name3 = scan3.nextLine();
                                    System.out.println("Enter name of event");
                                    String eventName = scanner.next();
                                    System.out.println("Enter time to add");
                                    String time = scanner.next();
                                    CompSwimmer compS3 = getSwimmerByName(name3);
                                    compS3.addTime(eventName, time);

                                    break;
                                case 2: //edit position
                                    System.out.println("Enter name of swimmer:");
                                    Scanner scan4 = new Scanner(System.in);
                                    String name4 = scan4.nextLine();
                                    System.out.println("Enter name of event:");
                                    String eventName1 = scanner.next();
                                    System.out.println("Enter position:");
                                    int position = scanner.nextInt();
                                    CompSwimmer compS4 = getSwimmerByName(name4);
                                    compS4.addPosition(eventName1, position);
                                    break;
                                case 3:
                                    //edit both
                                    //CompSwimmer.addTimeAndPosition;
                                    break;
                            }
                            break;
                        case 4:
                            //delete
                            //CompSwimmer.removeEvent;
                            break;
                    }
                    //event
                    break;
                case 4: //top 5 swimmers
                    getTopFiveSwimmersMenu();
                    break;
                default:
                    System.out.println("Wrong input");
                    break;
            }
            printMenu();
            input = scanner.nextInt();
        }
    }
    public static void getTopFiveSwimmersMenu(){
        System.out.println("\n======================================\n= [1] View Top 5 Junior Swimmers     =\n= [2] View Top 5 Senior Swimmers     =\n======================================\n");
        Scanner scanner = new Scanner(System.in);

        try{
            int input = scanner.nextInt();
            switch (input){
                case 1:
                    Coach.getTopFiveSwimmersJunior();
                    break;
                case 2:
                    Coach.getTopFiveSwimmersSenior();
                    break;
                default:
                    System.out.println("\n==============================\n=       INVALID INPUT        =\n==============================\n");
                    break;
            }} catch(Exception e){
            System.out.println("\n==============================\n=       INVALID INPUT        =\n==============================\n");
        }
    }
    public static void getTopFiveSwimmersJunior(){
        ArrayList<CompSwimmer> topSwimmers = (ArrayList<CompSwimmer>) juniorList.clone();
        Collections.sort(topSwimmers);
        System.out.println("\n===========TOP 5==============\n");
        int index = 0;
        for(CompSwimmer toppi : topSwimmers){
            System.out.println("Time: " + toppi.getBestTraining() + " Name: " + toppi.getName());
            index++;
            if(index == 5){
                break;
            }
        }
    }
    public static void getTopFiveSwimmersSenior(){
        ArrayList<CompSwimmer> topSwimmers = (ArrayList<CompSwimmer>) seniorList.clone();
        Collections.sort(topSwimmers);
        int index = 0;
        for(CompSwimmer toppi : topSwimmers){
            System.out.println(toppi);
            index++;
            if(index == 5){
                break;
            }
        }
    }
    public void printMenu(){
        System.out.println("\n=============== MENU =================");
        System.out.println("= [1] View student list              =");
        System.out.println("= [2] View or add training           =");
        System.out.println("= [3] View add, edit or delete event =");
        System.out.println("= [4] view top 5 swimmers            =");
        System.out.println("= [0] Log Out                        =");
        System.out.println("======================================\n");
    }
    private void printPopulateCoachStudentList() {
        for(CompSwimmer compSwimmer: juniorList){
            System.out.println(compSwimmer.toString());
        }
        System.out.println();
        for(CompSwimmer compSwimmer: seniorList){
            System.out.println(compSwimmer.toString());
        }
    }
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }
    public Discipline getDiscipline() { return discipline; }
    public CompSwimmer getSwimmerByName(String name){
        for (CompSwimmer compS : juniorList) {
            if (compS.getName().equals(name)) {
                return compS;
            }else{
                for (CompSwimmer compS1 : seniorList) {
                    if (compS1.getName().equals(name)) {
                        return compS1;
                    }
                }
            }
        }
        return null;
    }
}