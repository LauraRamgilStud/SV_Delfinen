import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Collections;

public class CompSwimmer extends Member implements Comparable<CompSwimmer>{
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
        Database.updateEventFile();
    }

    public void removeEvent(String eventName){
        for(int i = 0; i < eventList.size(); i++){
            if(eventList.get(i).getName().equals(eventName)){
                eventList.remove(i);
                Database.updateEventFile();
            }
        }
    }

    public void addTimeAndPosition(String eventName, int position, String time){
        addPosition(eventName, position);
        addTime(eventName, time);
        Database.updateEventFile();
    }

    public void addPosition(String eventName, int position){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                e.setPositionPlaced(position);
                System.out.println("Position: " + position + " is added to event: " + e.getName());
                Database.updateEventFile();
            }
        }
    }

    public void addTime(String eventName, String time){
        for(Event e: eventList){
            if(e.getName().equals(eventName)){
                e.setTime(time);
                System.out.println("Time: " + e.getTime() + " is added to event " + e.getName());
                Database.updateEventFile();
            }
        }
    }

    public void addTraining(String date, String time) throws ParseException {
        trainingList.add(new Training(date, time));
        trainingList.sort(Comparator.comparing(Training::getDate));
        Database.updateTrainingFile();
    }

    public String getBestTraining(){
        ArrayList<String> times = new ArrayList<>();
        for(Training t: trainingList){
            times.add(t.getTime());
        }
        Collections.sort(times);
        if(times.size() > 0){
            return times.get(0);
        }
        return null;
    }

    @Override
    public int compareTo(CompSwimmer o){
        if(this.getBestTraining() == null || o.getBestTraining() == null){
            return 0;
        }
        return this.getBestTraining().compareTo(o.getBestTraining());
    }
    public void viewTrainings(){
        System.out.println("========" + super.getName() + "'s TRAININGS========");
        for(Training t: trainingList){
            System.out.println("Date: " + t.getDate() + "  Time: " + t.getTime());
        }
    }

    public void viewEvents(){
        System.out.println("========" + super.getName() + "'s EVENTS========");
        for(Event e: eventList){
            System.out.println("Event: "+ e.getName() + "  Date: " + e.getDate() + "  Time: " + e.getTime() + "  Position: " + e.getPositionPlaced());
        }
    }

    public ArrayList<Training> getTrainingList(){
        return trainingList;
    }

    public ArrayList<Event> getEventList(){
        return eventList;
    }
}
