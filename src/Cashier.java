import java.util.Scanner;
public class Cashier extends Employee {
    public Cashier(String id) {
        super(id);
    }

    public void printCashierMenu(){
        System.out.println("\n============ MENU ============\n= [1] Overdue Invoices       =\n= [2] Expected Income        =\n= [3] Actual Income          =\n= [4] Change Payment Status  =\n= [0] Log Out                =\n==============================\n");
    }

    public void cashierMenu() {
        printCashierMenu();
        Scanner scanner = new Scanner(System.in);
        int input = 10154;
        while(input == 10154){
            if(scanner.hasNextInt()){
                input = scanner.nextInt();
            }else{
                scanner.nextLine();
                System.out.println("""
                        =========INVALID INPUT==============
                        =  Enter [1], [2], [3], [4] or [0] =
                        ====================================
                        """);
            }

        }
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