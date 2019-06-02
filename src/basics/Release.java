package basics;

import java.time.LocalDate;
import java.util.Arrays;

public class Release{
    protected String Title, Language, Status, ID;
    protected String[] Format;
    protected LocalDate ReleaseDate;
    protected int TrackCount;

    public Release(String Title, String Language, String[] Format, String Status, LocalDate ReleaseDate, int TrackCount, String ID) {
        this.Title = Title;
        this.Language = Language;
        this.Format = Format;
        this.TrackCount = TrackCount;
        this.Status = Status;
        this.ReleaseDate = ReleaseDate;
        this.ID=ID;
    }

    @Override
    public String toString() {
        System.out.println();
        System.out.println("Title: " + Title);
        if (Language!=null) System.out.println("Language: " + Language);
        if (Status!=null) System.out.println("Status: " + Status);
        if (TrackCount>=0) System.out.println("Track count: " + TrackCount);
        if (Format!=null){System.out.print("Formats: "); for (int i=0; i<5; i++) if (Format[i]!=null) System.out.print(Format[i] + " "); System.out.println();}
        if (ReleaseDate!=null) System.out.println("Release date: " + ReleaseDate);
        //return "{" + "Title=" + Title + ", Language=" + Language + ", Status=" + Status + ", Format=" + Arrays.toString(Format) + ", ReleaseDate=" + ReleaseDate + '}';
        return null;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getTitle() {
        return Title;
    }
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getLanguage() {
        return Language;
    }
    public void setLanguage(String Language) {
        this.Language = Language;
    }

    public String[] getFormat() {
        return Format;
    }
    public void setFormat(String[] Format) {
        this.Format = Format;
    }

    public int getTrackCount() {
        return TrackCount;
    }
    public void setTrackCount(int TrackCount) {
        this.TrackCount = TrackCount;
    }
    
    public String getStatus() {
        return Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }

    public LocalDate getReleaseDate() {
        return ReleaseDate;
    }
    public void setReleaseDate(LocalDate ReleaseDate) {
        this.ReleaseDate = ReleaseDate;
    }
    
}
