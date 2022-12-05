import java.util.ArrayList;
public class Coach extends Employee{
    Discipline discipline;
    String coachId;
    ArrayList<CompSwimmer> studentsList = new ArrayList<>();

    public Coach(String id, Discipline discipline){
        super(id);
        this.discipline = discipline;
    }

    /* Getter&setter SLET*/
    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public void setCoachId(String coachId){
        this.coachId = coachId;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public String getCoachId(String coachId){
        return coachId;
    }

    public void addCompSwimmer(String id, Discipline discipline){
        studentsList.add(new CompSwimmer(id, discipline));
    }

    public void removeCompSwimmer(String id){
        for (CompSwimmer cs: studentsList){
            if (cs.getCoachId().equals(id)){
                studentsList.remove(cs);
                System.out.println("Competition swimmer has been removed");
            }else{
                System.out.println("The competition swimmer does not exist");
            }
        }
    }
}
