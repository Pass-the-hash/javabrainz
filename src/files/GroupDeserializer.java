package files;

import java.util.LinkedList;
//import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.time.LocalDate;

public class GroupDeserializer {
    
    LinkedList<Subject> relations;

    public LinkedList<String> getRelations() {
        LinkedList names = new LinkedList();
        for (Subject i : relations) {
            names.add(i.artist.name);
        }
        return names;
    }

    public void setRelations(LinkedList<Subject> relations) {
        this.relations = relations;
    }
    
    class Subject{
        String begin, end;
        boolean ended;
        Artist artist;
        @SerializedName("target-type")
        String type;
    }
    
    class Area{
        String name;
    }
    
    class Artist{
        String name;
    }
    
}
