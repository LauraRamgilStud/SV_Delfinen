import java.util.Scanner;
public class SCDolphin {
    public static void main(String[] args) {
        Database.readFile();
        //Database.printMemberList();
        Chairman chairman = new Chairman("122");
        Cashier cashier = new Cashier("222");
        Coach[] coach = new Coach[4];

        coach[0] = new Coach("334", Discipline.CRAWL);
        coach[1] = new Coach("335", Discipline.BACKCRAWL);
        coach[2] = new Coach("336", Discipline.BUTTERFLY);
        coach[3] = new Coach("337", Discipline.BREASTSTROKE);

        System.out.println("\n============ MENU ============");
        System.out.println("= Program start successful   =");
        System.out.println("=                            =\n= Please enter your ID       =");
        System.out.println("==============================\n");

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
                case 3:
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
