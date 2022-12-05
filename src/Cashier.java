import java.util.Scanner;

public class Cashier extends Employee{
    public Cashier(String id) {
        super(id);
    }
    public static void cashierMenu(){
        System.out.println("[1] Overdue Invoices");
        System.out.println("[2] Expected Income");
        System.out.println("[3] Actual Income");
        System.out.println("[4] Change Payment Status");
        System.out.println("[5] Log Out");
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();

        while(input != 5){
            switch(input){
                case 1:
                    // View Overdue Invoices
                    Accounting.viewOverdueInvoices();
                    break;
                case 2:
                    // Calculate Expected income
                    Accounting.calculateExpectedIncome();
                    break;
                case 3:
                    // Calculate Actual Income
                    Accounting.calculateActualIncome();
                    break;
                case 4:
                    // Change Payment Status
                    break;
                default:
                    System.out.println("Invalid Input.");
                    break;
            }
            input = scanner.nextInt();
        }
    }
}