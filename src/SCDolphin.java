import java.util.Scanner;
public class SCDolphin {
    public static void main(String[] args) {
        Database.readFile();
        Database.readEventFile();
        Database.readTrainingFile();
        //Database.printMemberList();
        Chairman chairman = new Chairman("122");
        Cashier cashier = new Cashier("222");
        Coach[] coach = new Coach[4];

        coach[0] = new Coach("334", Discipline.CRAWL);
        coach[1] = new Coach("335", Discipline.BACKCRAWL);
        coach[2] = new Coach("336", Discipline.BUTTERFLY);
        coach[3] = new Coach("337", Discipline.BREASTSTROKE);

        System.out.println("\n============ MENU ============\n=  Program start successful  =\n=                            =\n=    Please enter your ID    =\n==============================\n");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0000){
            switch(input){
                case 122:
                    chairman.menu();
                    break;
                case 222:
                    Cashier.cashierMenu();
                    break;
                case 334:
                    coach[0].coachMenu(coach[0].getDiscipline());
                    break;
                case 335:
                    coach[1].coachMenu(coach[1].getDiscipline());
                    break;
                case 336:
                    coach[2].coachMenu(coach[2].getDiscipline());
                    break;
                case 337:
                    coach[3].coachMenu(coach[3].getDiscipline());
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            System.out.println("\n==============================\n= You are logged out.        =\n==============================\n\n==============================\n= Please enter your ID       =\n==============================\n");
            input = scanner.nextInt();
        }
    }
}