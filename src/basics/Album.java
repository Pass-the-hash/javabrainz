package basics;

import java.time.LocalDate;

public class Album extends Release{
    private Artist Artist;

    public Album(Artist Artist, String Title, String Language, String[] Format, String Status, LocalDate ReleaseDate, int TrackCount, String ID){
        super(Title, Language, Format, Status, ReleaseDate, TrackCount, ID);
        this.Artist = Artist;
    }


    @Override
    public String toString() {
        super.toString();
        System.out.println("Artist: " + Artist.Name);
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
