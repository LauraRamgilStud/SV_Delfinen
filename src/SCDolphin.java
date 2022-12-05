import javax.xml.crypto.Data;
import java.util.Scanner;

public class SCDolphin {
    public static void main(String[] args) {
        Database.readFile();
        //Database.printMemberList();

        //Database.addStandardMember();
        //Database.printMemberList();

        System.out.println("Program start successful\n\nPlease enter your ID: ");

        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0000){
            switch(input){
                case 1:
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
        }




        /*System.out.println("Please enter your Chairman id");
        if (cmScan.next().equals("d72x7G")){
            System.out.println("Perfect");
            switch (menu){
                case mainMenu:
                    menu.printMenu();
            }
        }else {
            System.out.println("WRONG DUDE");
        }*/
    }
}
