package db;

import basics.*;

import oracle.jdbc.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

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
                + "country VARCHAR(10), "
                + "cities VARCHAR(50), "
                + "begindate VARCHAR(10), "
                + "enddate VARCHAR(10), "
                + "aliases NVARCHAR2(1000), "
                + "members NVARCHAR2(1000), "
                + "tags NVARCHAR2(1000), "
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
                + "constraint release_id primary key (id) )");
    }

    public static void DeleteDatabase() throws SQLException {
        Statement statement=connection.createStatement();
        statement.execute("drop table RELEASES");
        statement.execute("drop table ARTISTS");
    }
    
    public static void WriteArtist(Person artist) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("insert into ARTISTS ("
                + "id, name, gender, country, cities, begindate, enddate, aliases, members, tags)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)");
        String cities=artist.getCities(), aliases=(String) artist.getAliases().get(0), tags=(String) artist.getTags().get(0);
        /*for (int i=1; i<artist.getCities().length; i++){
            cities=cities + "," + artist.getCities()[i];
        }*/
        for (int i=1; i<artist.getAliases().size(); i++){
            aliases=aliases + "," + artist.getAliases().get(i);
        }
        for(int i=1; i<artist.getTags().size(); i++){
            tags=tags + "," + artist.getTags().get(i);
        }
        statement.setString(1, artist.getID());
        statement.setString(2, artist.getName());
        statement.setString(3, artist.getGender());
        statement.setString(4, artist.getCountry());
        statement.setString(5, cities);
        statement.setString(6, artist.getBirthDate().toString());
        if (artist.getDeathDate()!=null) statement.setString(7, artist.getDeathDate().toString());
        else statement.setString(7, null);
        statement.setString(8, aliases);
        statement.setString(9, null);
        statement.setString(10, tags);
        statement.executeUpdate();
    }
    
    public static void WriteArtist(Group artist) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("insert into ARTISTS ("
                + "id, name, gender, country, cities, begindate, enddate, aliases, members, tags)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?)");
        String cities=artist.getCities(), aliases=(String) artist.getAliases().get(0), tags=(String) artist.getTags().get(0), members=(String) artist.getMembers().get(0);
        /*for (int i=1; i<artist.getCities().length; i++){
            cities=cities + "," + artist.getCities()[i];
        }*/
        for (int i=1; i<artist.getAliases().size(); i++){
            aliases=aliases + "," + artist.getAliases().get(i);
        }
        for(int i=1; i<artist.getTags().size(); i++){
            tags=tags + "," + artist.getTags().get(i);
        }
        for(int i=1; i<artist.getMembers().size(); i++){
            members=members + "," + artist.getMembers().get(i);
        }
        statement.setString(1, artist.getID());
        statement.setString(2, artist.getName());
        statement.setString(3, null);
        statement.setString(4, artist.getCountry());
        statement.setString(5, cities);
        statement.setString(6, artist.getBeginDate().toString());
        if (artist.getEndDate()!=null) statement.setString(7, artist.getEndDate().toString());
        else statement.setString(7, null);
        statement.setString(8, aliases);
        statement.setString(9, members);
        statement.setString(10, tags);
        statement.executeUpdate();
        System.out.println(artist.getBeginDate().toString());
    }

    public static void WriteRelease(Release release) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("insert into ARTISTS ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        String format=release.getFormat()[0];
        for (int i=1; i<release.getFormat().length; i++){
            format=format + "," + release.getFormat()[i];
        }
        statement.setString(0, release.getID());
        statement.setString(1, release.getTitle());
        statement.setString(2, format);
        statement.setString(4, release.getLanguage());
        statement.setString(5, release.getStatus());
        statement.setString(6, release.getReleaseDate().toString());
        statement.setInt(7, release.getTrackCount());
        statement.setString(8, null);
        statement.setString(9, null);
        
    }

    public static void WriteAlbum(Album album) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("insert into RELEASES ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        String format=album.getFormat()[0];
        for (int i=1; i<album.getFormat().length; i++){
            format=format + "," + album.getFormat()[i];
        }
        statement.setString(1, album.getID());
        statement.setString(2, album.getTitle());
        statement.setString(3, format);
        statement.setString(4, album.getLanguage());
        statement.setString(5, album.getStatus());
        statement.setString(6, album.getReleaseDate().toString());
        statement.setInt(7, album.getTrackCount());
        statement.setString(8, album.getArtist().getID());
        statement.setString(9, null);
        statement.executeUpdate();
        System.out.println(album.getArtist().getClass());
        /*if (album.getArtist().getType().equals("Group")) WriteArtist((Group) album.getArtist());
        else WriteArtist((Group) album.getArtist());*/
    }
    
    public static void WriteCompilation(Compilation compilation) throws SQLException {
        PreparedStatement statement=connection.prepareStatement("insert into RELEASES ("
                + "id, title, format, language, status, release_date, track, artist, artists)"
                + "VALUES(?,?,?,?,?,?,?,?,?)");
        String format=(String) compilation.getFormat()[0], artists=compilation.getArtists().get(0).getID();
        for (int i=1; i<compilation.getFormat().length; i++){
            format=format + "," + compilation.getFormat()[i];
        }
        for (int i=1; i<compilation.getArtists().size(); i++){
            artists=artists + "," + compilation.getArtists().get(i).getID();
        }
        statement.setString(1, compilation.getID());
        statement.setString(2, compilation.getTitle());
        statement.setString(3, format);
        statement.setString(4, compilation.getLanguage());
        statement.setString(5, compilation.getStatus());
        statement.setString(6, compilation.getReleaseDate().toString());
        statement.setInt(7, compilation.getTrackCount());
        statement.setString(8, null);
        statement.setString(9, artists);
        statement.executeUpdate();
        WriteArtists(compilation.getArtists());
    }

    public static void WriteArtists(LinkedList<Artist> artists) throws SQLException {
        for (int i=0; i<artists.size(); i++){
            if (artists.getClass().equals("basics.Person")) WriteArtist((Person) artists.get(i));
            else WriteArtist((Group) artists.get(i));
        }
    }

    public static void WriteReleases(LinkedList<Release> releases) throws SQLException {
        for (Release i: releases){
            WriteRelease(i);
        }
    }

    public static void WriteAlbums(LinkedList<Album> albums) throws SQLException {
        for (Album i:albums){
            WriteAlbum(i);
        }
    }

    public static void WriteCompilations(LinkedList<Compilation> compilations) throws SQLException {
        for (Compilation i:compilations){
            WriteCompilation(i);
        }
    }

    public static ArrayList<Artist> ReadArtists() throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from ARTISTS");
        ArrayList artists=new ArrayList();
        LinkedList aliases, tags, members=null;
        LocalDate begin=null, end=null;
        String cities, tmp;
        
        while(results.next()){
            cities=results.getString(5);
            //cities=tmp.split(",");
            tmp=results.getString(8);
            aliases=new LinkedList(Arrays.asList(tmp.split(" , ")));
            tmp=results.getString(9);
            if (tmp!=null) members=new LinkedList(Arrays.asList(tmp.split(" , ")));
            tmp=results.getString(10);
            tags=new LinkedList(Arrays.asList(tmp.split(" , ")));
            if (results.getString(6)!=null) begin=LocalDate.parse(results.getString(6));
            if (results.getString(7)!=null) end=LocalDate.parse(results.getString(7));
            if (results.getString(3)!=null)
                artists.add(new Person(results.getString(3), results.getString(2), results.getString(4), cities, begin, end, aliases, tags, results.getString(1)));
            else
                artists.add(new Group(results.getString(2), results.getString(4), cities, begin, end, aliases, members, tags, results.getString(1)));
        }
        return artists;
    }
    
    public static ArrayList<Release> ReadReleases() throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES");
        ArrayList<Release> releases=new ArrayList<Release>();
        String tmp;

        while (results.next()) {
            tmp = results.getString(4);
            String[] format = tmp.split(",");
            releases.add(new Release(results.getNString(2), results.getString(3), format, results.getString(5), LocalDate.parse(results.getString(6)), results.getInt(7), results.getString(1)));
        }
        return releases;
    }
    
    public static ArrayList<Album> ReadAlbums() throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES");
        ArrayList<Album> albums=new ArrayList<Album>();
        String tmp;
                
        while(results.next()){
            tmp=results.getString(4);
            String[] format=tmp.split(",");
            albums.add(new Album(null, results.getNString(2), LocalDate.parse(results.getString(6)), results.getString(1)));
        }
        
        return albums;
    }
    
    public static ArrayList<Compilation> ReadCompilations() throws SQLException{
        Statement statement=connection.createStatement();
        ResultSet results=statement.executeQuery("select * from RELEASES");
        ArrayList<Compilation> compilations=new ArrayList<Compilation>();
        String tmp;
                
        while(results.next()){
            tmp=results.getString(4);
            String[] format=tmp.split(",");
            compilations.add(new Compilation(null, results.getNString(2), LocalDate.parse(results.getString(6)), results.getString(1)));
        }
        return compilations;
    }
}
