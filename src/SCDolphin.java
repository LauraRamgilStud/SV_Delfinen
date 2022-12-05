import javax.xml.crypto.Data;

public class SCDolphin {
    public static void main(String[] args) {
        Database.readFile();
        Database.printMemberList();

        Database.addStandardMember();
        Database.printMemberList();

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
