import java.util.Scanner;
public class SCDolphin {
    public static void main(String[] args) {
        Database.readFile();
        Database.printMemberList();
        Chairman chairman = new Chairman("123");

        System.out.println("Program start successful\n\nPlease enter your ID: ");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0000){
            switch(input){
                case 1:
                    chairman.menu();
                    break;
                case 2:
                    Cashier.cashierMenu();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid input.");
                    break;
            }
            System.out.println("You are logged out.\n\nEnter ID: ");
            input = scanner.nextInt();
        }
    }
}
