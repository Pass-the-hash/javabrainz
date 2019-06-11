package tests;

import basics.*;
import db.Database;
import files.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemoFilesAPI {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, InterruptedException {
        //FileWrapper out=new FileWrapper();
        ArrayList<Artist> test;
        ArrayList<Release> test2;
        /*test=APIWrapper.getArtists(hmap);
        test2.add(new Album(test.get(0), "Dynasty", "English", null, "official", LocalDate.of(1978, 9, 8), 5));
        FileWrapper.writeArtistsToFile("test", test);
        FileWrapper.writeAlbumsToFile("test2", test2);
        test=FileWrapper.readArtistsFromFile("test");
        test2=FileWrapper.readAlbumsFromFile("test2");
        System.out.println(test);
        System.out.println(test2);*/
        
        /*test=APIWrapper.getArtists(hmap);
        test2=APIWrapper.getReleases(hmap);
        for (Artist i:test){
            i.toString();
            System.out.println();
        }
        for (Release i:test2){
            i.toString(); 
            System.out.println();
        }*/
        //APIWrapper.artistDeserialize();
        /*test=APIWrapper.getArtists("kiss");
        test.toString();*/
        APIWrapper.getAlbumsWithName("dynasty").toString();
        //Database.CreateDatabase();
        //test=APIWrapper.getArtistsWithName("infected");
        //Database.WriteArtists(test);
        System.out.println(System.lineSeparator() + "OOF!");
    }
    
}
