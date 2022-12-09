import java.util.Scanner;
public class Cashier extends Employee {
    public Cashier(String id) {
        super(id);
    }

    public static void printCashierMenu(){
        System.out.println("\n============ MENU ============\n= [1] Overdue Invoices       =\n= [2] Expected Income        =\n= [3] Actual Income          =\n= [4] Change Payment Status  =\n= [0] Log Out                =\n==============================\n");
    }

    public static void cashierMenu() {
        printCashierMenu();
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while (input != 0) {
            switch (input) {
                case 1:
                    Accounting.viewOverdueInvoices();
                    break;
                case 2:
                    Accounting.calculateExpectedIncome();
                    break;
                case 3:
                    Accounting.calculateActualIncome();
                    break;
                case 4:
                    Accounting.changePaymentStatus();
                    break;
                default:
                    System.out.println("\n=========INVALID INPUT==========\n=    Enter [1], [2], [3] or [4]    =\n================================\n");
                    break;
            }
            printCashierMenu();
            input = scanner.nextInt();
        }
    }
}