public class Membership {

    private int age;
    private boolean status;
    private static final double PASSIVE_PRICE = 500.00;
    private static final double JUNIOR_PRICE = 1000.00;
    private static final double SENIOR_PRICE = 1600.00;
    private static final double RETIRED_PRICE = SENIOR_PRICE - 0.25 * SENIOR_PRICE;

    Membership(boolean status, int age){
        this.status = status;
        this.age = age;
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