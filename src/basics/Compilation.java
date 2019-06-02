package basics;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Compilation extends Release{
    private List<Artist> Artists;

    public Compilation(List<Artist> Artists, String Title, LocalDate ReleaseDate, String ID) {
        super(Title, null, null, null, ReleaseDate, -1, ID);
        this.Artists = Artists;
    }

    @Override
    public String toString() {
        super.toString();
        if (Artists!=null) for (Artist i:Artists)System.out.println("Artists: " + i.Name);
        //return super.toString() + "{" + Artists.toString() + '}';
        return null;
    }
    

    public List<Artist> getArtists() {
        return Artists;
    }
    public void setArtists(List Artists) {
        this.Artists = Artists;
    }
    
}