package db;

import basics.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class Database{
    
    static Connection connection;
    
    private static void Init(){
        if (connection==null){
            System.out.println();
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
            } catch (ClassNotFoundException e){
                System.out.println("No Oracle JDBC driver found!");
                e.printStackTrace();
                return;
            }
            System.out.println("Oracle JDBC driver registered!");
            try {
                String url="jdbc:oracle:thin:@oracle12c.hua.gr:1521:orcl";
                connection=DriverManager.getConnection(url, "it21741", "it21741");
            } catch(SQLException e){
                System.out.println("Connection failed! Checkout output console.");
                e.printStackTrace();
                return;
            }
            if (connection!=null) System.out.println("DB connection established!");
            else System.out.println("Failed to make connection to DB!");
        }
    }
    public static void CreateDatabase() throws SQLException {
        Init();
        DeleteDatabase();
        Statement statement=connection.createStatement();
        statement.execute("drop type SARRAY force");
        statement.execute("CREATE TYPE SARRAY AS VARRAY(100) OF NVARCHAR2(50)");
        statement.execute("create table ARTISTS("
                + "id VARCHAR(50), "
                + "name NVARCHAR2(50), "
                + "gender VARCHAR(8), "
                + "country VARCHAR(50), "
                + "cities VARCHAR(50), "
                + "begindate VARCHAR(10), "
                + "enddate VARCHAR(10), "
                + "aliases NVARCHAR2(1000), "
                + "members NVARCHAR2(1000), "
                + "tags NVARCHAR2(1000),"
                + "constraint person_id primary key(id))");
       
        statement.execute("create table RELEASES("
                + "id VARCHAR(50), "
                + "title NVARCHAR2(50), "
                + "language VARCHAR(10), "
                + "format VARCHAR(50), "
                + "status VARCHAR(20), "
                + "release_date VARCHAR(10), "
                + "track NUMBER(4), "
                + "artist VARCHAR(50), "
                + "artists VARCHAR(1000), "
                + "constraint release_id primary key (id))");
    }

    public static void DeleteDatabase() throws SQLException {
        Statement statement=connection.createStatement();
        statement.execute("drop table RELEASES");
        statement.execute("drop table ARTISTS");
    }
    
    private static String RemoveComma(String tmp){
        StringBuilder sb=new StringBuilder(tmp);
        sb.deleteCharAt(0);
        return sb.toString();
    }
    
    public static void WriteArtist(Person artist) throws SQLException {
        Init();
        PreparedStatement statement=connection.prepareStatement("insert into ARTISTS ("
                + "id, name, gender, country, cities, begindate, enddate, aliases, members, tags)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)");
        String aliases="", tags="", members="";
        for (int i=0; i<artist.getAliases().size(); i++){
            aliases=aliases + "," + artist.getAliases().get(i);
        }
        for(int i=0; i<artist.getTags().size(); i++){
            tags=tags + "," + artist.getTags().get(i);
        }
        statement.setString(1, artist.getID());
        statement.setString(2, artist.getName());
        statement.setString(3, artist.getGender());
        statement.setString(4, artist.getCountry());
        statement.setString(5, artist.getCities());
        if (artist.getBirthDate()!=null) statement.setString(6, artist.getBirthDate().toString());
        else statement.setString(6, null);
        if (artist.getDeathDate()!=null) statement.setString(7, artist.getDeathDate().toString());
        else statement.setString(7, null);
        statement.setString(8, aliases);
        statement.setString(9, members);
        statement.setString(10, tags);
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate found!");
        }
    }
    
    public static void WriteArtist(Group artist) throws SQLException {
        Init();
        PreparedStatement statement=connection.prepareStatement("insert into ARTISTS ("
                + "id, name, gender, country, cities, begindate, enddate, aliases, members, tags)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)");
        String aliases="", tags="", members="";
        /*if () aliases=artist.getAliases().get(0);
        if ()*/
        /*for (int i=1; i<artist.getCities().length; i++){
            cities=cities + "," + artist.getCities()[i];
        }*/
        for (int i=0; i<artist.getAliases().size(); i++){
            aliases=aliases + "," + artist.getAliases().get(i);
        }
        for(int i=0; i<artist.getTags().size(); i++){
            tags=tags + "," + artist.getTags().get(i);
        }
        for(int i=0; i<artist.getMembers().size(); i++){
            members=members + "," + artist.getMembers().get(i);
        }
        statement.setString(1, artist.getID());
        statement.setString(2, artist.getName());
        statement.setString(3, null);
        statement.setString(4, artist.getCountry());
        statement.setString(5, artist.getCities());
        if (artist.getBeginDate()!=null) statement.setString(6, artist.getBeginDate().toString());
        else statement.setString(6, null);
        if (artist.getEndDate()!=null) statement.setString(7, artist.getEndDate().toString());
        else statement.setString(7, null);
        statement.setString(8, aliases);
        statement.setString(9, members);
        statement.setString(10, tags);
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate found!");
        }
    }

    public static void WriteRelease(Release release) throws SQLException {
        Init();
        PreparedStatement statement=connection.prepareStatement("insert into RELEASES ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        String format="";
        /*if (release.getFormat()!=null){
            format=release.getFormat()[0];*/
            for (int i=0; i<release.getFormat().length; i++){
                format=format + "," + release.getFormat()[i];
            }
        //}
        statement.setString(1, release.getID());
        statement.setString(2, release.getTitle());
        statement.setString(3, format);
        statement.setString(4, release.getLanguage());
        statement.setString(5, release.getStatus());
        if (release.getReleaseDate()!=null) statement.setString(6, release.getReleaseDate().toString());
        else statement.setString(6, null);
        statement.setInt(7, release.getTrackCount());
        statement.setString(8, null);
        statement.setString(9, null);
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate found!");
        }
    }

    public static void WriteAlbum(Album album) throws SQLException {
        Init();
        PreparedStatement statement=connection.prepareStatement("insert into RELEASES ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        /*String format=album.getFormat()[0];
        for (int i=1; i<album.getFormat().length; i++){
            format=format + "," + album.getFormat()[i];
        }*/
        statement.setString(1, album.getID());
        statement.setString(2, album.getTitle());
        statement.setString(3, null);
        statement.setString(4, null);
        statement.setString(5, null);
        if (album.getReleaseDate()!=null) statement.setString(6, album.getReleaseDate().toString());
        else statement.setString(6, null);
        statement.setInt(7, -1);
        if (album.getArtist()!=null) statement.setString(8, album.getArtist().getID());
        else statement.setString(8, null);
        statement.setString(9, null);
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate found!");
        }
        if (album.getArtist()!=null){
            if (album.getArtist().getClass().equals(Person.class)) WriteArtist((Person) album.getArtist());
            else if (album.getArtist().getClass().equals(Group.class)) WriteArtist((Group) album.getArtist());
        }
    }
    
    public static void WriteCompilation(Compilation compilation) throws SQLException {
        Init();
        PreparedStatement statement=connection.prepareStatement("insert into RELEASES ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        String artists="";
        /*for (int i=1; i<compilation.getFormat().length; i++){
            format=format + "," + compilation.getFormat()[i];
        }*/
        for (int i=1; i<compilation.getArtists().size(); i++){
            artists=artists + "," + compilation.getArtists().get(i).getID();
        }
        statement.setString(1, compilation.getID());
        statement.setString(2, compilation.getTitle());
        statement.setString(3, null);
        statement.setString(4, null);
        statement.setString(5, null);
        if (compilation.getReleaseDate()!=null) statement.setString(6, compilation.getReleaseDate().toString());
        else statement.setString(6, null);
        statement.setInt(7, -1);
        statement.setString(8, null);
        statement.setString(9, artists);
        try {
            statement.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Duplicate found!");
        }
        WriteArtists(compilation.getArtists());
    }

    public static void WriteArtists(List<Artist> artists) throws SQLException {
        for (int i=0; i<artists.size(); i++){
            if (artists.get(i).getClass().equals(Person.class)) WriteArtist((Person) artists.get(i));
            else if (artists.get(i).getClass().equals(Group.class)) WriteArtist((Group) artists.get(i));
        }
    }

    public static void WriteReleases(List<Release> releases) throws SQLException {
        for (Release i: releases){
            WriteRelease(i);
        }
    }

    public static void WriteAlbums(List<Album> albums) throws SQLException {
        for (Album i:albums){
            WriteAlbum(i);
        }
    }

    public static void WriteCompilations(List<Compilation> compilations) throws SQLException {
        for (Compilation i:compilations){
            WriteCompilation(i);
        }
    }

    public static ArrayList<Artist> ReadArtists(String key) throws SQLException{
        Init();
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from ARTISTS where lower(name) like \'%" + key + "%\'");
        ArrayList artists=new ArrayList();
        LinkedList aliases=null, tags=null, members=null;
        LocalDate begin=null, end=null;
        String cities, tmp;
        
        while(results.next()){
            cities=results.getString(5);
            //cities=tmp.split(",");
            tmp=results.getString(8);
            if (tmp!=null){
                tmp=RemoveComma(tmp);
                aliases=new LinkedList(Arrays.asList(tmp.split(" , ")));
            }
            tmp=results.getString(9);
            if (tmp!=null){
                tmp=RemoveComma(tmp);
                members=new LinkedList(Arrays.asList(tmp.split(" , ")));
            }
            tmp=results.getString(10);
            if (tmp!=null) {
                tmp=RemoveComma(tmp);
                tags=new LinkedList(Arrays.asList(tmp.split(" , ")));
            }
            if (results.getString(6)!=null) begin=LocalDate.parse(results.getString(6));
            if (results.getString(7)!=null) end=LocalDate.parse(results.getString(7));
            if (results.getString(3)!=null)
                artists.add(new Person(results.getString(3), results.getString(2), results.getString(4), cities, begin, end, aliases, tags, results.getString(1)));
            else
                artists.add(new Group(results.getString(2), results.getString(4), cities, begin, end, aliases, members, tags, results.getString(1)));
        }
        return artists;
    }
    
    public static ArrayList<Release> ReadReleases(String key) throws SQLException{
        Init();
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES where lower(title) like \'%" + key + "%\'");
        ArrayList<Release> releases=new ArrayList();
        String tmp;
        String[] format=null;
        LocalDate date=null;
        
        while (results.next()) {
            tmp = results.getString(4);
            if (tmp!=null){
                tmp=RemoveComma(tmp);
                format = tmp.split(",");
            }
            if (results.getString(6)!=null) date=LocalDate.parse(results.getString(6));
            releases.add(new Release(results.getNString(2), results.getString(3), format, results.getString(5), null, results.getInt(7), results.getString(1)));
        }
        return releases;
    }
    
    public static ArrayList<Album> ReadAlbums(String key) throws SQLException{
        Init();
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES where title like \'%" + key + "%\'");
        ArrayList<Album> albums=new ArrayList();
        ArrayList<Artist> artists;
        Artist artist=null;
        LocalDate date=null;
        artists=ReadArtists("");

        while(results.next()){
            if (results.getString(8)==null) continue;
            for (Artist i:artists){
                 if (i.getID().equals(results.getString(8))) artist=i;
            }
            if (results.getString(6)!=null) date=LocalDate.parse(results.getString(6));
            albums.add(new Album(artist, results.getNString(2), date, results.getString(1)));
        }
        return albums;
    }
    
    public static ArrayList<Compilation> ReadCompilations(String key) throws SQLException{
        Init();
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES");
        ArrayList<Compilation> compilations=new ArrayList();
        ArrayList<Artist> artists=new ArrayList(), artist_results=ReadArtists("");
        HashSet<Artist> unique=new HashSet();
        LocalDate date=null;
        
        while(results.next()){
            if (results.getString(9)==null) continue;
            for (int i=0; i<artist_results.size(); i++){
                if (results.getString(9).contains(artist_results.get(i).getID())){
                    unique.add(artist_results.get(i));
                }
            }
            for (Artist i:unique){
                i.toString();
                artists.add(i);
            }
            if (results.getString(6)!=null) date=LocalDate.parse(results.getString(6));
            compilations.add(new Compilation(artists, results.getNString(2), date, results.getString(1)));
        }
        return compilations;
    }
}
