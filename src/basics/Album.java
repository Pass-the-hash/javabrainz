package basics;

import java.time.LocalDate;

public class Album extends Release{
    private Artist Artist;

    public Album(Artist Artist, String Title, LocalDate ReleaseDate, String ID){
        super(Title, null, null, null, ReleaseDate, -1, ID);
        this.Artist = Artist;
    }


    @Override
    public String toString() {
        super.toString();
        if (Artist!=null){
            System.out.println(System.lineSeparator() + "Artist: ");
            Artist.toString();
        }
        //return super.toString() + "{" + "Artist=" + Artist + '}';
        return null;
    }

    public Artist getArtist(){
        return Artist;
    }
    public void setArtist(Artist Artist){
        this.Artist = Artist;
    }
    
}
