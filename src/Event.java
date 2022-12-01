import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    String name;
    Date date;
    int positionPlaced = 0;

    public Event(String name, String date) throws ParseException {
        this.name = name;
        if(isValidDate(date)){
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            this.date = dateFormat.parse(date);
        }
    }

    public boolean isValidDate(String date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try{
            dateFormat.parse(date.trim());
        } catch (ParseException pe){
            return false;
        }
        return true;
    }

    public void addPosition(int position){
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
}
