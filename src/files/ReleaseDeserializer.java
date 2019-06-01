package files;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import java.util.ArrayList;

public class ReleaseDeserializer {

    public ArrayList<ReleaseDeserializer.Release> releases;

    public class Release{
        private String title;
        private String status;
        //private TextRepresentation repr;
        private ArrayList<Media> media;
        private String date;

        @SerializedName("text-representation")
        private TextRepresentation repr;
        
        @SerializedName("track-count")
        private int trackCount;

        public String getTitle() {
            return title;
        }

        public String getStatus() {
            return status;
        }
        
        public LocalDate getDate() {
            if (date!=null) return LocalDate.parse(date);
            else return null;
        }

        public int getTrackCount() {
            return trackCount;
        }
        
        public String getLanguage() {
            return repr.language;
        }

        public String[] getFormat() {
            String[] format=new String[5];
            int j=0;
            for (Media i:media){
                format[j]=i.format;
                j++;
            }
            return format;
        }
        
    }

    public class TextRepresentation{
        private String language;
    }

    public class Media{
        private String format;
    }
}
