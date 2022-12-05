public class Chairman extends Employee{
    public Chairman(String id) {
        super(id);
    }

    System.out.println("Please enter your Chairman id");
    if (cmScan.next().equals("d72x7G")){
        System.out.println("Perfect");
        switch (menu){
            case mainMenu:
                menu.printMenu();
        }
    }else {
        System.out.println("WRONG DUDE");
    }

}
