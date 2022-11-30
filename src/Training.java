import java.util.Date;

public class Training {
    Date date;
    Date time;

    public Training(Date date, Date time){
        this.date = date;
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public Date getTime() {
        return time;
    }
}
