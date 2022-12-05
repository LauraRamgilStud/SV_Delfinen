import java.util.ArrayList;
public class Coach extends Employee{
    private Discipline discipline;
    private static ArrayList<CompSwimmer> studentsList = new ArrayList<>();

    public Coach(String id, Discipline discipline){
        super(id);
        this.discipline = discipline;
    }

    public void menu(){
        /*Lave Menu for coach
            Han skal kunne:
            - view student list
            - tilføje, redigere og delete training
            - tilføje, redigere og delete event
            - logout til hovedmenu (MAIN)

            Valgene skal være ud fra tal, altså [view list, press 1]

         */
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public static void addCompSwimmer(CompSwimmer compSwimmer){
        studentsList.add(compSwimmer);
    }
    public void removeCompSwimmer(String name){
        for (CompSwimmer cs: studentsList){
            if (cs.getName().equals(name)){
                studentsList.remove(cs);
                System.out.println("Competitive swimmer has been removed");
            }else{
                System.out.println("The competitive swimmer does not exist");
            }
        }
    }
}
