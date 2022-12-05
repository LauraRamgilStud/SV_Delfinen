public class Chairman extends Employee{
    //String menu;
    String chairmanId = "d72x7G";
    Scanner cmScan = new Scanner(System.in);
    Menu menu = new Menu();

    public Chairman(String id) {
        super(id) = chairmanId;
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
