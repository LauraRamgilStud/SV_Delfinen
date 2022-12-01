import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class CompSwimmer extends Member{
    Discipline discipline;
    String coachName;
    ArrayList<Event> eventList = new ArrayList<>();
    ArrayList<Training> trainingList = new ArrayList<>();

    public CompSwimmer(String name, Date age,boolean status, Discipline discipline){
        super(name, age, status);
        this.discipline = discipline;
    }

    public void setCoachName(String coachName){
        this.coachName = coachName;
    }

    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }

    public Discipline getDiscipline(){
        return discipline;
    }

    public String getCoachName(){
        return coachName;
    }

    public void addEvent(String eventName, String date) throws ParseException {
        eventList.add(new Event(eventName, date));
    }

    public void removeEvent(String eventName){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                eventList.remove(e);
                System.out.println("Event has been removed");
            }else{
                System.out.println("Event does not exist");
            }
        }
    }

    public void addPosition(String eventName, int position){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                e.addPosition(position);
                System.out.println("Position: " + position + " is added to event: " + e.getName());
            }
        }
    }

    public void addTraining(Date date, Date time){
        trainingList.add(new Training(date, time));
    }

    public void viewTrainings(){
        for(Training t: trainingList){
            System.out.println("Date: " + t.getDate() + " Time: " + t.getTime());
        }
    }

    public void viewEvents(){
        for(Event e: eventList){
            for(int i = 1; i < eventList.size(); i++){
                if(e.getPositionPlaced() != 0){
                    System.out.println(i + ". Event: "+ e.getName() + " Date: " + e.getDate() + " Position: " + e.getPositionPlaced());
                }else{
                    System.out.println(i + ". Event: "+ e.getName() + " Date: " + e.getDate());
                }
            }
        }
    }
}
