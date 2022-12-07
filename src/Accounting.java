import java.util.Scanner;
public class Accounting {
    private static double sum;
    public static void viewOverdueInvoices(){
        System.out.println("\n======OVERDUE INVOICES=======\n");
        for(Member member : Database.getMemberList()){
            if (!member.getHasPaid()){  //if (member.getHasPaid() == false)
                System.out.println(member);
            } else {
                System.out.println("No overdue Invoices (Yay!)");
            }
        }
    }
    public static void calculateExpectedIncome(){
        for(Member member : Database.getMemberList()){
            sum += member.getMembership().getFee();
            System.out.println("\n======EXPECTED INCOME========\n=        "+ sum +" DKK\n==============================\n");
        }
    }
    public static void calculateActualIncome(){
        for(Member member : Database.getMemberList()){
            if(member.getHasPaid()){        //(member.getHasPaid() == true)
                sum += member.getMembership().getFee();
                System.out.println("\n=======ACTUAL INCOME=========\n=        "+ sum +" DKK\n==============================\n");
            } else {
                sum += 0;
            }
        }
    }
    public static void changePaymentStatus(){
        System.out.println("\n======================================");
        System.out.println("= [1] Change specific payment status =\n= [2] Change all payment status      =\n======================================\n");

        Scanner scanner = new Scanner(System.in);
        try{
        int input = scanner.nextInt();

        switch (input){
            case 1:
                Accounting.changeSpecificPayment();
                break;
            case 2:
                Accounting.changeAllPaymentStatus();
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }} catch(Exception e){
                System.out.println("\n==============================\n=       INVALID INPUT        =\n==============================\n");
        }
    }
    public static void changeSpecificPayment(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n==============================\n=    Enter member's name     =\n==============================\n");
        try{
        String input = scanner.nextLine();
        for(Member member : Database.getMemberList()){
            if(input.equals(member.getName())){
                System.out.println("\n========CHANGE STATUS=========\n= [1] paid                   =\n= [2] unpaid                 =\n==============================\n");
                int inputInt = scanner.nextInt();
                if(inputInt == 1) {
                    member.setHasPaid(true);
                    Database.updateDBFile();
                    System.out.println("\nThe payment status has been changed to \"paid\".");
                } else if(inputInt == 2) {
                    member.setHasPaid(false);
                    System.out.println("The payment status has been changed to \"unpaid\"");
                    Database.updateDBFile();
                }
            }
        } } catch (Exception e){
            System.out.println("\n========INVALID INPUT=========\n=      ENTER [1] OR [2]      =\n==============================\n");
            scanner.nextInt();
        }
    }
    public static void changeAllPaymentStatus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n========CHANGE STATUS=========\n= [1] paid                   =\n= [2] unpaid                 =\n==============================\n");
        try{
        int input = scanner.nextInt();
        for(Member member : Database.getMemberList()){
            if(input == 1){
                member.setHasPaid(true);
                Database.updateDBFile();
                System.out.println("The payment status of each member has been changed to \"paid\".");
            } else if (input == 2){
                member.setHasPaid(false);
                Database.updateDBFile();
                System.out.println("The payment status of each member has been changed to \"unpaid\".");
            }
        }} catch(Exception e){
            System.out.println("\n========INVALID INPUT=========\n=      ENTER [1] OR [2]      =\n==============================\n");
        }
    }
}