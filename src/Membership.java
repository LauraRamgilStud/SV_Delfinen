import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

public class Membership {
    private final String birthday;
    private boolean status;
    private static final double PASSIVE_PRICE = 500.00;
    private static final double JUNIOR_PRICE = 1000.00;
    private static final double SENIOR_PRICE = 1600.00;
    private static final double RETIRED_PRICE = SENIOR_PRICE - 0.25 * SENIOR_PRICE;

    public Membership(boolean status, String birthday){
        this.status = status;
        this.birthday = birthday;
    }

    public String toString() {
        return "= " + getFee() + " DKK =";
    }

    public double getFee() {
        if (status) {
            if (getAge(birthday) < 18) {
                return JUNIOR_PRICE;
            } else if (getAge(birthday) > 60) {
                return RETIRED_PRICE;
            } else {
                return SENIOR_PRICE;
            }
        } else {
            return PASSIVE_PRICE;
        }
    }

    public int getAge(String birthday){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = formatter.parse(birthday);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Instant instant = date.toInstant();
        ZonedDateTime zone = instant.atZone(ZoneId.systemDefault());
        LocalDate givenDate = zone.toLocalDate();
        Period period = Period.between(givenDate, LocalDate.now());
        return period.getYears();
    }

    public boolean getStatus(){
        return status;
    }

    public void setStatus(boolean status){
        this.status = status;
    }
}