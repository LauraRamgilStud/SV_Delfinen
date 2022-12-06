import java.util.Scanner;

public class Chairman extends Employee{
    public Chairman(String id) {
        super(id);
    }

    public void menu(){
       System.out.println("********* You have the following options *********");
       System.out.println("Add Member [1], Edit Member [2], Remove Member [3] or View Members [4]");
       System.out.println("Exit to main menu, press 0");
       Scanner scanInput = new Scanner(System.in);
       int input = scanInput.nextInt();

       //Catch if the input is not a number
        // Hvad  hvis de har et mellemnavn
       while(input != 0){
           switch (input){
               case 1:
                   System.out.println("Add new COMPETITIVE [1] or STANDARD [2] member");
                   int inputNew = scanInput.nextInt();
                   if(inputNew == 1){
                       Database.addCompetitiveMember();
                   } else if(inputNew == 2){
                       Database.addStandardMember();
                   }

                   break;
               case 2:
                   System.out.println("Edit not implemented yet");
                   break;
               case 3:
                   System.out.println("Enter name of member to remove: ");
                   scanInput.nextLine();
                   Database.removeMember(scanInput.nextLine());
                   break;
               case 4:
                   Database.printMemberList();
                   break;
               default:
                   System.out.println("Wrong input");
                   break;
           }
           System.out.println("********* You have the following options *********");
           System.out.println("Add Member [1], Edit Member [2], Remove Member [3] or View Members [4]");
           System.out.println("Exit to main menu, press 0");
           input = scanInput.nextInt();
       }

    }
}
