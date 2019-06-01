package files;

import basics.*;

import com.google.gson.*;
import com.google.gson.reflect.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FileWrapper {
    
    private static void Writer(String filename, String buffer){
        File file=new File(filename);
        try {
            if (file.exists()==false) file.createNewFile();
            try (FileWriter out = new FileWriter(filename)) {
                out.write(buffer);
            }
        } catch (IOException ex) {
            Logger.getLogger(FileWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static String Reader(String filename) throws ClassNotFoundException{
        File file=new File(filename);
        //ObjectInputStream buffer;
        String json = null;
        if (file.exists()==false) return "ERROR";
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(filename));
            json=new String(encoded, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            Logger.getLogger(FileWrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return json;
    }
    
    public static void writeArtistsToFile(String filename, ArrayList artists){
        Gson gson=new Gson();
        String json=gson.toJson(artists);
        Writer(filename, json);
    }
    /*public static void writeGroupsToFile(String filename, ArrayList artists){
        Gson gson=new Gson();
        String json=gson.toJson(artists.get(0));
        Writer(filename, json);
    }*/
    public static void writeReleasesToFile(String filename, ArrayList<Release> collection){
        Gson gson=new Gson();
        String json=gson.toJson(collection);
        Writer(filename, json);
    }
    public static void writeAlbumsToFile(String filename, ArrayList<Album> album){
        Gson gson=new Gson();
        String json=gson.toJson(album);
        Writer(filename, json);
    }
    public static void writeCompilationsToFile(String filename, ArrayList<Compilation> compilation){
        Gson gson=new Gson();
        String json=gson.toJson(compilation);
        Writer(filename, json);
    }
    
    public static ArrayList<Artist> readArtistsFromFile(String filename) throws ClassNotFoundException{
        Gson gson=new Gson();
        String json=Reader(filename);
        Type collectionType = new TypeToken<ArrayList<Artist>>(){}.getType();
        ArrayList result=gson.fromJson(json, collectionType);
        return result;
    }
    public static ArrayList<Release> readReleasesFromFile(String filename) throws ClassNotFoundException{
        Gson gson=new Gson();
        String json=Reader(filename);
        Type collectionType = new TypeToken<ArrayList<Release>>(){}.getType();
        ArrayList result=gson.fromJson(json, collectionType);
        return result;
    }
    public static ArrayList<Album> readAlbumsFromFile(String filename) throws ClassNotFoundException{
        Gson gson=new Gson();
        String json=Reader(filename);
        Type collectionType = new TypeToken<ArrayList<Album>>(){}.getType();
        ArrayList result=gson.fromJson(json, collectionType);
        return result;
    }
    public static ArrayList<Compilation> readCompilationsFromFile(String filename) throws ClassNotFoundException{
        Gson gson=new Gson();
        String json=Reader(filename);
        Type collectionType = new TypeToken<ArrayList<Compilation>>(){}.getType();
        ArrayList result=gson.fromJson(json, collectionType);
        return result;
    }
}
