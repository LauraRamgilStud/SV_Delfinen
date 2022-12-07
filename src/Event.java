import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    String name;
    Date date;
    String time = "[NONE]";
    int positionPlaced = 0;
    String stringDate;

    public Event(String name, String date) throws ParseException {
        this.name = name;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        this.date = format.parse(date);
        stringDate = date;
    }

    public void setPositionPlaced(int position){
        positionPlaced = position;
    }

    public String getName(){
        return name;
    }

    public Date getDate(){
        return date;
    }

    public int getPositionPlaced(){
        return positionPlaced;
    }

    public String getTime(){
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStringDate() { return stringDate; }
}
