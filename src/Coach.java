import java.util.*; //(?)
public class Coach extends Employee {
    private Discipline discipline;
    private ArrayList<CompSwimmer> seniorList = new ArrayList<>();
    private ArrayList<CompSwimmer> juniorList = new ArrayList<>();

    public Coach(String id, Discipline discipline) {
        super(id);
        this.discipline = discipline;
    }

    public void coachMenu(Discipline discipline) {
        populateCoachStudentList(discipline);
        printMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0) {
            try {
                switch (input) {
                    case 1:
                        printPopulateCoachStudentList();
                        break;
                    case 2: //Add training or view trainings
                        System.out.println("========================================\n=  Do you want to View [1] or add [2]  =\n========================================");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1: //view training list
                                CompSwimmer compS = getSwimmerByName();
                                compS.viewTrainings();
                                break;
                            case 2: //add training
                                try {
                                    addTraining();

                                } catch (Exception e) {
                                    e.getStackTrace();
                                }
                                break;
                        }
                        break;
                    case 3:
                        System.out.println("==============================================================\n=  Do you want to View [1], add [2], edit [3] or delete [4]  =\n==============================================================");
                        input = scanner.nextInt();
                        switch (input) {
                            case 1: //view
                                CompSwimmer compS = getSwimmerByName();
                                compS.viewEvents();
                                break;
                            case 2: //add
                                try {
                                    addEvent();

                                } catch (Exception e) {
                                    e.getStackTrace();
                                }
                                break;
                            case 3:
                                //edit
                                System.out.println("===========================================================\n=  Do you want to add time [1], position [2] or both [3]  =\n===========================================================");
                                input = scanner.nextInt();
                                switch (input) {
                                    case 1: //edit time
                                        addTimeToEvent();
                                        break;
                                    case 2: //edit position
                                        addPositionToEvent();
                                        break;
                                    case 3:
                                        addTimeAndPositionToEvent();
                                        break;
                                }
                                break;
                            case 4:
                                deleteEvent();
                                break;
                        }
                        //event
                        break;
                    case 4: //top 5 swimmers
                        getTopFiveSwimmersMenu();
                        break;
                    default:
                        System.out.println("=== Wrong input ===");
                        break;
                }
                printMenu();
                input = scanner.nextInt();
            }catch(Exception e){
                System.out.println("\n=========INVALID INPUT==========\n=    Enter [1], [2], [3] or [4]    =\n================================\n");
                scanner.nextInt();
            }
        }
    }

    public void getTopFiveSwimmersMenu() {
        System.out.println("\n======================================\n= [1] View Top 5 Junior Swimmers     =\n= [2] View Top 5 Senior Swimmers     =\n======================================\n");
        Scanner scanner = new Scanner(System.in);
        int input;
        try {
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    getTopFiveSwimmersJunior();
                    break;
                case 2:
                    getTopFiveSwimmersSenior();
                    break;
                default:
                    System.out.println("\n==============================\n=       INVALID INPUT        =\n==============================\n");
                    break;
            }
        } catch (Exception e) {
            System.out.println("\n==============================\n=       INVALID INPUT        =\n==============================\n");
        }
    }

    public void getTopFiveSwimmersJunior() {
        ArrayList<CompSwimmer> toppiList = (ArrayList<CompSwimmer>) juniorList.clone();
        Collections.sort(toppiList);
        System.out.println("\n=========== TOP 5 ==============\n");
        int index = 0;
        for (CompSwimmer toppi : toppiList) {
            if(toppi.getBestTraining() != null) {
                System.out.println((index+1)+". Time: " + toppi.getBestTraining() + " Name: " + toppi.getName());
                index++;
                if (index == 5) {
                    break;
                }
            }
        }
    }

    public void getTopFiveSwimmersSenior() {
        ArrayList<CompSwimmer> toppiList = (ArrayList<CompSwimmer>) seniorList.clone();
        Collections.sort(toppiList);
        System.out.println("\n=========== TOP 5 ==============\n");
        int index = 0;
        for (CompSwimmer toppi : toppiList) {
            if(toppi.getBestTraining() != null) {
                System.out.println((index+1)+". Time: " + toppi.getBestTraining() + " Name: " + toppi.getName());
                index++;
                if (index == 5) {
                    break;
                }
            }
        }
    }

    public void printMenu() {
        System.out.println("\n=============== MENU =================");
        System.out.println("= [1] View student list              =");
        System.out.println("= [2] View or add training           =");
        System.out.println("= [3] View add, edit or delete event =");
        System.out.println("= [4] view top 5 swimmers            =");
        System.out.println("= [0] Log Out                        =");
        System.out.println("======================================\n");
    }

    public void populateCoachStudentList(Discipline discipline) {
        try {
            for (Member c : Database.getMemberList()) {
                if (c instanceof CompSwimmer && ((CompSwimmer) c).getDiscipline() == discipline) {
                    CompSwimmer compSwimmer = (CompSwimmer) c;
                    int age = compSwimmer.getMembership().getAge(compSwimmer.getBirthday());
                    if (age <= 18) {
                        juniorList.add(compSwimmer);
                    } else {
                        seniorList.add(compSwimmer);
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void printPopulateCoachStudentList() {
        System.out.print("===== Junior Swimmers =====\n");
        for (CompSwimmer compSwimmer : juniorList) {
            System.out.println(compSwimmer.getName());
        }
        System.out.println();
        System.out.print("===== Senior Swimmers =====\n");
        for (CompSwimmer compSwimmer : seniorList) {
            System.out.println(compSwimmer.getName());
        }
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public CompSwimmer getSwimmerByName() {
        System.out.println("\n==============================\n=   ENTER NAME OF SWIMMER    =\n==============================\n");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for(CompSwimmer cS: seniorList){
            if(cS.getName().equals(name)){
                return cS;
            }
        }
        for(CompSwimmer cJ: juniorList){
            if(cJ.getName().equals(name)){
                return cJ;
            }
        }

        return null;
    }

    public void addTimeToEvent() {
        try{
            Scanner scanner = new Scanner(System.in);
            CompSwimmer compS = getSwimmerByName();
            if (compS == null) {
                System.out.println("==== Member does not exist ====");
            } else {
                System.out.println("\n==============================\n=    ENTER NAME OF EVENT     =\n==============================\n");
                String eventName = scanner.nextLine();
                System.out.println("\n==============================\n=     ENTER TIME TO ADD      =\n=        [MM-SS-MS]          =\n==============================\n");
                String time = scanner.nextLine();
                compS.addTime(eventName, time);
                System.out.println("==== Time successfully added ====");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addPositionToEvent() {
        try{
            Scanner scanner = new Scanner(System.in);
            CompSwimmer compS = getSwimmerByName();
            if(compS == null){
                System.out.println("==== Could not find swimmer ====");
            }else {
                System.out.println("\n==============================\n=    ENTER NAME OF EVENT     =\n==============================\n");
                String eventName = scanner.nextLine();
                System.out.println("\n==============================\n=       ENTER POSITION       =\n==============================\n");
                int position = scanner.nextInt();
                compS.addPosition(eventName, position);
                System.out.println("==== Position successfully added ====");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addTimeAndPositionToEvent() {
        try{
            Scanner scanner = new Scanner(System.in);
            CompSwimmer compS = getSwimmerByName();
            if(compS == null){
                System.out.println("==== Could not find swimmer ====");
            }else {
                System.out.println("\n==============================\n=    ENTER NAME OF EVENT     =\n==============================\n");
                String eventName = scanner.nextLine();
                System.out.println("\n==============================\n=       ENTER POSITION       =\n==============================\n");
                int position = scanner.nextInt();
                System.out.println("\n==============================\n=     ENTER TIME TO ADD      =\n=        [MM-SS-MS]          =\n==============================\n");
                String time = scanner.next();
                compS.addTimeAndPosition(eventName, position, time);
                System.out.println("==== Time and position successfully added ====");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteEvent() {
        try{
            Scanner scanner = new Scanner(System.in);
            CompSwimmer compS = getSwimmerByName();
            if(compS == null){
                System.out.println("==== Could not find swimmer ====");
            }else {
                System.out.println("\n==============================\n=    ENTER NAME OF EVENT     =\n==============================\n");
                String eventName = scanner.nextLine();
                compS.removeEvent(eventName);
                System.out.println("==== Event successfully deleted ====");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addEvent() {
        try {
            Scanner scanner = new Scanner(System.in);
            CompSwimmer compS = getSwimmerByName();
            if(compS == null) {
                System.out.println("==== Could not find swimmer ====");
            }else {
                System.out.println("\n==============================\n=    ENTER NAME OF EVENT     =\n==============================\n");
                String eventName = scanner.nextLine();
                System.out.println("\n==============================\n=    ENTER DATE OF EVENT     =\n=       [DD-MM-YYYY]         =\n==============================\n");
                String date = scanner.nextLine();
                compS.addEvent(eventName, date);
                System.out.println("==== Event successfully added ====");
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void addTraining() {
        try {
            CompSwimmer compS = getSwimmerByName();
            Scanner scanner = new Scanner(System.in);
            if(compS == null){
                System.out.println("==== Could not find swimmer ====");
            }else {
                System.out.println("\n==============================\n=   ENTER DATE OF TRAINING   =\n=       [DD-MM-YYYY]         =\n==============================\n");
                String date = scanner.next();
                System.out.println("\n==============================\n=  ENTER TIME FROM TRAINING  =\n=        [MM-SS-MS]          =\n==============================\n");
                String time = scanner.next();
                compS.addTraining(date, time);
                System.out.println("==== Training successfully added ====");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}