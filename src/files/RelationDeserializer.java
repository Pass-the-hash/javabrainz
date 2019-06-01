package files;

//import java.time.LocalDateTime;
//import java.util.LinkedList;
import java.util.ArrayList;
//import com.google.gson.annotations.SerializedName;

public class RelationDeserializer {

    public ArrayList<Relation> relations;

    public class Relation{
        Artist artist;
        String type;
    }

    public class Artist{
        String name;
    }
}
