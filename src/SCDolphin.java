import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
public class SCDolphin {
    public static void main(String[] args) {

    System.out.println("HELLO LAURA OG FREJA :)");
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Database database = new Database();
        database.addMember();
        database.addMember();
        database.printMemberList();
    }
}
