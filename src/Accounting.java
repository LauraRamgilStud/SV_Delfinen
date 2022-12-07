import java.util.ArrayList;
import java.util.Scanner;

public class Accounting {
    private static double sum;

    public static void viewOverdueInvoices(){
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
            System.out.println("Expected Income: " + sum + " DKK");
        }
    }

    public static void calculateActualIncome(){
        for(Member member : Database.getMemberList()){
            if(member.getHasPaid()){        //(member.getHasPaid() == true)
                sum += member.getMembership().getFee();
                System.out.println("Actual Income " + sum + " DKK");
            } else {
                sum += 0;
            }
        }
    }

    public static void changePaymentStatus(){
        System.out.println("[1] Change specific payment status\n[2] Change all payment status'");
        Scanner scanner = new Scanner(System.in);
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
        }

    }

    public static void changeSpecificPayment(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member's name: ");
        String input = scanner.nextLine();
        for(Member member : Database.getMemberList()){
            if(input.equals(member.getName())){
                System.out.println("Change status to: ");
                boolean inputBoolean = scanner.nextBoolean();
                member.setHasPaid(inputBoolean);
                Database.updateDBFile();
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public static void changeAllPaymentStatus(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Change payment status to: ");
        boolean input = scanner.nextBoolean();
        for(Member member : Database.getMemberList()){
            if(input){
                member.setHasPaid(true);
                Database.updateDBFile();
                System.out.println("The payment status of each member has been changed to \"paid\".");
            } else {
                member.setHasPaid(false);
                Database.updateDBFile();
                System.out.println("The payment status of each member has been changed to \"unpaid\".");
            }
        }
    }
}
