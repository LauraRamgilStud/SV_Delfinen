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
    public static void calculateDifference(){

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
        Scanner scanner = new Scanner(System.in);
        int input = scanner.nextInt();
        System.out.println("[1] Change specific payment status\n[2] Change all payment status'");

        switch (input){
            case 1:
                break;
            case 2:
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }

    }

    public static void changeSpecificPayment(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        for(Member member : Database.getMemberList()){
            if(input.equals(member.getName()){
                Database.getMemberList().remove(member);
            }
        }
    }
}
