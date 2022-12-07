import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;

public class CompSwimmer extends Member{
    private Discipline discipline;
    private ArrayList<Event> eventList = new ArrayList<>();
    private ArrayList<Training> trainingList = new ArrayList<>();

    public CompSwimmer(String name, String birthday, boolean status, Discipline discipline){
        super(name, birthday, status);
        this.discipline = discipline;
    }

    public void setDiscipline(Discipline discipline){
        this.discipline = discipline;
    }

    public Discipline getDiscipline(){
        return discipline;
    }

    public void addEvent(String eventName, String date) throws ParseException {
        eventList.add(new Event(eventName, date));
        eventList.sort(Comparator.comparing(Event::getDate));
    }

    public void removeEvent(String eventName){
        for(int i = 0; i < eventList.size(); i++){
            if(eventList.get(i).getName().equals(eventName)){
                eventList.remove(i);
            }
        }
    }

    public void addTimeAndPosition(String eventName, int position, String time){
        addPosition(eventName, position);
        addTime(eventName, time);
    }

    public void addPosition(String eventName, int position){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                e.setPositionPlaced(position);
                System.out.println("Position: " + position + " is added to event: " + e.getName());
            }
        }
    }

    public void addTime(String eventName, String time){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                e.setTime(time);
                System.out.println("Time: " + e.getTime() + " is added to event " + e.getName());
            }
        }
    }

    public void addTraining(String date, String time) throws ParseException {
        trainingList.add(new Training(date, time));
        trainingList.sort(Comparator.comparing(Training::getDate));
    }

    public void viewTrainings(){
        System.out.println("******************** " + super.getName() + "'s TRAININGS ********************");
        for(Training t: trainingList){
            System.out.println("Date: " + t.getDate() + "  Time: " + t.getTime());
        }
    }

    public void viewEvents(){
        System.out.println("********************* " + super.getName() + "'s EVENTS *********************");
        for(Event e: eventList){
            System.out.println("Event: "+ e.getName() + "  Date: " + e.getDate() + "  Time: " + e.getTime() + "  Position: " + e.getPositionPlaced());
        }
    }
}
