package basics;

import java.time.LocalDate;
import java.util.LinkedList;

public class Compilation extends Release{
    private LinkedList<Artist> Artists;

    public Compilation(LinkedList<Artist> Artists, String Title, String Language, String[] Format, String Status, LocalDate ReleaseDate, int TrackCount, String ID) {
        super(Title, Language, Format, Status, ReleaseDate, TrackCount, ID);
        this.Artists = Artists;
    }

    @Override
    public String toString() {
        super.toString();
        for (Artist i:Artists){
            System.out.println("Artists: " + i.Name);
        }
        //return super.toString() + "{" + Artists.toString() + '}';
        return null;
    }
    

    public LinkedList<Artist> getArtists() {
        return Artists;
    }
    public void setArtists(LinkedList Artists) {
        this.Artists = Artists;
    }
    
}