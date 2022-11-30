import java.util.Date;

public class Event {
    String name;
    Date date;
    int positionPlaced = 0;

    public Event(String name, Date date){
        this.name = name;
        this.date = date;
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
