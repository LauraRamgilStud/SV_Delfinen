import java.util.Scanner;

public class Chairman extends Employee{
    public Chairman(String id) {
        super(id);
    }

    public void menu(){
       presentOptions();
       Scanner scanInput = new Scanner(System.in);
       int input = scanInput.nextInt();

       //Catch if the input is not a number
        // Hvad  hvis de har et mellemnavn
       while(input != 0){
           switch (input){
               case 1:
                   addNewMember();
                   break;
               case 2:
                   System.out.println("Edit not implemented yet");
                   break;
               case 3:
                   Database.removeMemberByName();
                   break;
               case 4:
                   Database.printMemberList();
                   break;
               default:
                   System.out.println("Wrong input");
                   break;
           }
           presentOptions();
           input = scanInput.nextInt();
       }
    }

    public void addNewMember(){
        System.out.println("Add new COMPETITIVE [1] or STANDARD [2] member");
        Scanner scanInput = new Scanner(System.in);
        boolean control = true;
        while (control){
            try{
                int inputNew = scanInput.nextInt();
                if(inputNew == 1){
                    Database.addCompetitiveMember();
                    control = false;
                } else if(inputNew == 2){
                    Database.addStandardMember();
                    control = false;
                }else{
                    System.out.println("Input not valid: Enter [1] or [2]");
                }
            }catch(Exception e){
                System.out.println("Input not valid: Enter [1] or [2]");
                scanInput.nextLine();
            }
        }
    }

    public void presentOptions(){
        System.out.println("[1] Add Member");
        System.out.println("[2] Edit Member");
        System.out.println("[3] Remove Member");
        System.out.println("[4] View Members");
        System.out.println("[0] Log Out");
    }
}
