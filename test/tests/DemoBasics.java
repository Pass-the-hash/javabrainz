package tests;

import java.util.LinkedList;
import java.time.LocalDate;
import basics.*;
import db.Database;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemoBasics {
    public static void main(String[] args) throws SQLException{
        //LocalDate date=LocalDate.of(1970, 1, 10);
        //LocalDate.parse(date.toString(), DateTimeFormatter.ofPattern("dd/MM/uuuu"));
        //System.out.println(date);
        LinkedList<String> aliases, genre, members, tags;
        aliases=new LinkedList<String>();
        aliases.add("Demon");
        aliases.add("Space");
        aliases.add("Star");
        aliases.add("Cat");
        genre=new LinkedList<String>();
        genre.add("Rock");
        genre.add("Metal");
        members=new LinkedList<String>();
        members.add("Gene Simons");
        members.add("Paul Stanley");
        members.add("Ace Frehley");
        members.add("Peter Criss");
        tags=new LinkedList<String>();
        tags.add("80's");
        tags.add("Fire effects");
        String[] format = {"LP", "CD", "CS"};
        //String[] cities="Queens";
        int TrackCount = 7;

        //Είσοδος στοιχείων.
        System.out.println(System.lineSeparator() + "Creating group...");
        Group kiss=new Group("Kiss", "USA", "Queens", LocalDate.of(1973, 1, 10) , aliases, genre, members, "549");
        LinkedList artists = new LinkedList();
        artists.add(kiss);
        System.out.println("OK!" + System.lineSeparator());
        
        System.out.println(System.lineSeparator() + "Creating artist...");
        Person gene = new Person("Male", "Gene Simons", "USA", "New York", LocalDate.of(1949, 8, 25), aliases, tags, "96856");
        artists.add(gene);
        System.out.println("OK!" + System.lineSeparator());
        
        System.out.println(System.lineSeparator() + "Creating album...");
        Album dynasty = new Album(kiss,"Dynasty", LocalDate.of(1979, 5, 23), "79679");
        System.out.println("OK!" + System.lineSeparator());
        
        System.out.println(System.lineSeparator() + "Creating compilation...");
        Compilation comp = new Compilation(artists, "80's hits", LocalDate.of(2019, 3, 30), "4685");
        System.out.println("OK!" + System.lineSeparator());

        System.out.println(System.lineSeparator() + "Creating release...");
        Release rel = new Release("Dynasty","English",format,"official", LocalDate.of(1979, 5, 23), TrackCount, "9579");
        System.out.println("OK!" + System.lineSeparator());
        
       
        System.out.println(System.lineSeparator() + "###Artist###");
        gene.toString();                                            // Σε όλες τις κλάσεις, η μέθοδος toString(), που περιέχεται σε κάθε κλάση , υπερβαίνεται ώστε να εκτυπώνει 
        System.out.println(System.lineSeparator() + "###Group###"); // άμεσα τα γνωρίσματα και όχι να τα επιστρέφει σε μορφή String.
        kiss.toString();
        System.out.println(System.lineSeparator() + "###Release###");
        rel.toString();
        System.out.println(System.lineSeparator() + "###Album###");
        dynasty.toString();
        System.out.println(System.lineSeparator() + "###Compilation###");
        comp.toString();
        System.out.println();
        
        ArrayList<Artist> test;
        ArrayList<Release> test2;
        Database.CreateDatabase();
        /*Database.WriteArtist(kiss);
        Database.WriteArtist(gene);
        Database.WriteAlbum(dynasty);
        Database.WriteCompilation(comp);
        test=Database.ReadArtists();
        test2=Database.ReadReleases();
        test.get(0).toString();
        test2.get(0).toString();
        Database.ReadAlbums().toString();
        Database.ReadCompilations();*/
        System.out.println("OOF!");
    }
}