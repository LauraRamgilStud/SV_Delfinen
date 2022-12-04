import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Training {
    Date date;
    String time;

    public Training(String date, String time) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.parse(date);
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time){
        this.time = time;
    }
}
