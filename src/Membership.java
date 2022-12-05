public class Membership {
    /*//Vi skal ikke have en fee attribute her?
    Så i stedet for getFee metoden der er nu, kunne
    vi kalde den assignMembership, hvor den så giver
    attribute fee sin værdi her i stedet for at returnere den med det samme.
    Dermed bruger vi kun if-else én gang for hver oprettelse og ikke
    hver gang vi skal bruge værdien af fee til noget*/
    /*Jeg kan godt se, hvad du mener, men bliver alderen så automatisk opdateret? :o */

    private int age;
    private boolean status;
    private double fee;
    private static final double PASSIVE_PRICE = 500.00;
    private static final double JUNIOR_PRICE = 1000.00;
    private static final double SENIOR_PRICE = 1600.00;
    private static final double RETIRED_PRICE = SENIOR_PRICE - 0.25 * SENIOR_PRICE;

    Membership(boolean status, int age){
        this.status = status;
        this.age = age;
        fee = getFee();
    }

    public String toString() {
        return " " + fee + " DKK";
    }

    public double getFee() {
        if (status) {
            if (age < 18) {
                return JUNIOR_PRICE;
            } else if (age > 60) {
                return RETIRED_PRICE;
            } else {
                return SENIOR_PRICE;
            }
        } else {
            return PASSIVE_PRICE;
        }
    }
}