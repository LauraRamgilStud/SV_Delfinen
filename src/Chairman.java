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
                   editMember();
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
        System.out.println("\n=========ADD MEMBER===========\n= [1] COMPETITIVE            =\n= [2] STANDARD               =\n==============================");
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
    public void editMember(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n==============================\n=    Enter member's name     =\n==============================\n");
        try{
            String input = scanner.nextLine();
            for(Member member : Database.getMemberList()){
                if(input.equals(member.getName())){
                    System.out.println("\n========CHANGE STATUS=========\n= [1] active                 =\n= [2] passive                =\n==============================\n");
                    int inputInt = scanner.nextInt();
                    if(inputInt == 1) {
                        member.getMembership().setStatus(true);
                        Database.updateDBFile();
                        System.out.println("\nThe member's status has been changed to \"active\".");
                    } else if(inputInt == 2) {
                        member.getMembership().setStatus(false);
                        System.out.println("The member's status has been changed to \"passive\"");
                        Database.updateDBFile();
                    }
                }
            } } catch (Exception e){
            System.out.println("\n========INVALID INPUT=========\n=      ENTER [1] OR [2]      =\n==============================\n");
            scanner.nextInt();
        }
    }
    public void presentOptions(){
        System.out.println("\n============ MENU ============");
        System.out.println("= [1] Add Member             =");
        System.out.println("= [2] Edit Member            =");
        System.out.println("= [3] Remove Member          =");
        System.out.println("= [4] View Members           =");
        System.out.println("= [0] Log Out                =");
        System.out.println("==============================\n");
    }
}
