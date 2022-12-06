public class Member {
    private String name;
    private String birthday;
    private boolean status;
    private boolean hasPaid;
    private Membership membership;

    public String toString(){
        return
                "Name: " + getName() +
                "\nBirthday: " + getBirthday() +
                "\nActive: " + getStatus() +
                "\nPaid: " + getHasPaid() +
                "\nFee:" + getMembership().getFee() +
                "\n========================";
    }

    public void printMember(){
        System.out.println("======================");
        System.out.println("Name: " + getName());
        System.out.println("Birthday: " + getBirthday());
        if(getStatus()){
            System.out.println("Status: active");
        } else {
            System.out.println("Status: passive");
        }
        if(getHasPaid()){
            System.out.println("Payment status: paid");
        } else {
            System.out.println("Payment status: unpaid");
        }
        System.out.println("Fee: " + getMembership().getFee());
    }

    public Member(String name, String birthday, boolean status) {
        this.name = name;
        this.birthday = birthday;
        this.status = status;
        this.membership = new Membership(status, birthday);
    }
    public boolean getHasPaid() { return hasPaid; }
    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }
    public String getName(){ return name; }
    public String getBirthday() { return birthday; }
    public boolean getStatus(){ return status; }
    public Membership getMembership() { return membership; }
}