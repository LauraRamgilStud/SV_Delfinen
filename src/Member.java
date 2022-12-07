public class Member {
    private String name;
    private String birthday;
    private boolean hasPaid;
    private Membership membership;

    //Constructor
    public Member(String name, String birthday, boolean status) {
        this.name = name;
        this.birthday = birthday;
        this.membership = new Membership(status, birthday);
    }
    public String toString() {
            return  "= Name: " + getName() +
                    "\n= Birthday: " + getBirthday() +
                    "\n" + getStatusString() +
                    "\n" + getPayedString() +
                    "\n= Fee: " + getMembership().getFee() + " DKK" +
                    "\n===============================";

    }

    //Getters and setters
    public boolean getHasPaid() { return hasPaid; }

    public void setHasPaid(boolean hasPaid) { this.hasPaid = hasPaid; }

    public Membership getMembership() { return membership; }

    public String getName(){ return name; }

    public String getBirthday() { return birthday; }

    //Bruges i toString metoden
    public String getStatusString(){
        if(membership.getStatus()){
            return "= Status: active";
        }else{
            return "= Status: passive";
        }
    }

    //Bruges i toString metoden
    public String getPayedString(){
        if(hasPaid){
            return "= Payment status: payed";
        }else{
            return "= Payment status: not payed";
        }
    }

}