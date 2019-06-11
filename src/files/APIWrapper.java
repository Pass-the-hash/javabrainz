package files;

import basics.*;

import com.jayway.jsonpath.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class APIWrapper {

    static class Request extends Thread{
        public Request(){}
    
        public static String getResponse(String u) throws IOException{

            URL url = new URL(u);
            url.openConnection();

            String response;
            try (Scanner scanner = new Scanner(url.openStream())) {
                response = scanner.useDelimiter("\\Z").next();
            }
            url.openStream().close();
            return response;
        }
        public void run(){ 
            try { 
            // Displaying the thread that is running 
            System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running"); 
  
            } 
            catch (Exception e) 
            { 
                // Throwing an exception 
                System.out.println ("Exception is caught"); 
            } 
        }
    }
    
    /*private static String getResponse(String u) throws IOException{

        URL url = new URL(u);
        url.openConnection();

        String response;
        try (Scanner scanner = new Scanner(url.openStream())) {
            response = scanner.useDelimiter("\\Z").next();
        }
        url.openStream().close();
        return response;
    }*/
    private static int CheckDate(String date){
       return date.length() - date.replaceAll("-","").length();
    }

    
    private static ArrayList<Artist> getArtists(String u) throws IOException{
        String response = Request.getResponse(u);
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        
        List<String> names=JsonPath.read(response, "$.artists[*].name"), ids=JsonPath.read(response, "$.artists[*].id"), types=JsonPath.using(conf).parse(response).read("$.artists[*].type");
        List area=JsonPath.using(conf).parse(response).read("$.artists[*].area"), begin_area=JsonPath.using(conf).parse(response).read("$.artists[*].begin-area"), begins=JsonPath.using(conf).parse(response).read("$.artists[*].life-span.begin"), ends=JsonPath.using(conf).parse(response).read("$.artists[*].life-span.end");
        List<String> genders=JsonPath.using(conf).parse(response).read("$.artists[*].gender"), aliascheck=JsonPath.using(conf).parse(response).read("$.artists[*].aliases"), tagscheck=JsonPath.using(conf).parse(response).read("$.artists[*].tags");
        String city, country, begin, end;
        List<String> aliases, tags;
        LinkedList members=null;
        LinkedList<String> alias, tag;
        ArrayList artists=new ArrayList();
        Person person; Group group;
        int requests=0;
        for (int i=0; i<ids.size(); i++){
            if (types.get(i)==null) continue;
            alias=new LinkedList(); tag=new LinkedList();
            country=null; city=null;
            if (aliascheck.get(i)!=null){ aliases=JsonPath.parse(response).read("$.artists["+i+"].aliases[*].name"); for (int j=0; j<aliases.size(); j++) alias.add(aliases.get(j));}
            if (tagscheck.get(i)!=null){ tags=JsonPath.parse(response).read("$.artists["+i+"].tags[*].name"); for (int j=0; j<tags.size(); j++) tag.add(tags.get(j));}
            if (area.get(i)!=null) country=JsonPath.parse(area.get(i)).read("$.name");
            if (begin_area.get(i)!=null) city=JsonPath.parse(begin_area.get(i)).read("$.name");
            begin=(String) begins.get(i);
            end=(String) ends.get(i);
            if (types.get(i).equals("Person")){
                if (begin!=null && end!=null && CheckDate(begin)==2 && CheckDate(end)==2) person=new Person(genders.get(i), names.get(i), country, city, LocalDate.parse(begin), LocalDate.parse(end), alias, tag, ids.get(i));
                else if (begin!=null && CheckDate(begin)==2) person=new Person(genders.get(i), names.get(i), country, city, LocalDate.parse(begin), alias, tag, ids.get(i));
                else person=new Person(genders.get(i), names.get(i), country, city, null, null, alias, tag, ids.get(i));
                artists.add(person);
            } else if(types.get(i).equals("Group")){
                members=populateRelations(ids.get(i));
                if (begin!=null && end!=null && CheckDate(begin)==2 && CheckDate(end)==2) group=new Group(names.get(i), country, city, LocalDate.parse(begin), LocalDate.parse(end), alias, tag, members, ids.get(i));
                else if (begin!=null && CheckDate(begin)==2) group=new Group(names.get(i), country, city, LocalDate.parse(begin), null, alias, tag, members, ids.get(i));
                else group=new Group(names.get(i), country, city, null, alias, tag, members, ids.get(i));
                artists.add(group);
            }
        }
        return artists;
    }

    private static ArrayList<Release> getReleases(String u) throws IOException {
        String response = Request.getResponse(u);
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        ArrayList<Release> releases=new ArrayList();
        
        List<String> titles=JsonPath.read(response, "$.releases[*].title"), ids=JsonPath.read(response, "$.releases[*].id"), statuses=JsonPath.using(conf).parse(response).read("$.releases[*].status");
        List<String> dates=JsonPath.using(conf).parse(response).read("$.releases[*].date");
        List<String> list;
        List media=JsonPath.using(conf).parse(response).read("$.releases[*].media");
        List<Integer> tracks=JsonPath.using(conf).parse(response).read("$.releases[*].track-count");
        String language;
        String[] format;
        for (int i=0; i<ids.size(); i++) {
            format=null;
            language=null; format=new String[5];
            language=JsonPath.using(conf).parse(response).read("$.releases["+i+"].text-representation.language");
            if (media.get(i)!=null){list=JsonPath.using(conf).parse(response).read("$.releases["+i+"].media[*].format"); format=new String[5]; for (int j=0; j<list.size()&&j<5; j++) format[j]=list.get(j);}
            if (dates.get(i)!=null && CheckDate(dates.get(i))==2) releases.add(new Release(titles.get(i), language, format, statuses.get(i), LocalDate.parse(dates.get(i)), tracks.get(i), ids.get(i)));
            else releases.add(new Release((String)titles.get(i), language, format, statuses.get(i), null, tracks.get(i), ids.get(i)));
        }
        return releases;
    }
    
    private static ArrayList<Album> getAlbums(String u) throws IOException{
        String response = Request.getResponse(u);
        ArrayList albums=new ArrayList();
        ArrayList<Artist> artists;
        
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        List<String> titles=JsonPath.read(response, "$.release-groups[*].title"), ids=JsonPath.read(response, "$.release-groups[*].id"), artist_ids=JsonPath.using(conf).parse(response).read("$.release-groups[*].artist-credit[0].artist.id");;
        List compilations=JsonPath.using(conf).parse(response).read("$.release-groups[*].secondary-types");
        
        for (int i=0; i<ids.size(); i++){
            if (compilations.get(i)!=null) continue;
                //artists = APIWrapper.getArtists("http://musicbrainz.org/ws/2/artist/?query=arid:" + artist_ids.get(i) + "&fmt=json");
                albums.add(new Album(null, titles.get(i), null, ids.get(i)));
        }
        return albums;
    }
    
    private static ArrayList<Compilation> getCompilations(String u) throws IOException{
        String response = Request.getResponse(u);
        ArrayList<Compilation> compilations=new ArrayList();
        ArrayList<Artist> artists=new ArrayList();
        ArrayList<Artist> tmp;
        
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        List<String> titles=JsonPath.read(response, "$.release-groups[*].title"), ids=JsonPath.read(response, "$.release-groups[*].id"), artist_ids;
        
        for (int i=0; i<ids.size(); i++){
            /*artist_ids=JsonPath.using(conf).parse(response).read("$.release-groups["+i+"].artist-credit[*].artist.id");
            System.out.println(i);
            tmp=getArtists("http://musicbrainz.org/ws/2/artist/?query=arid:" + artist_ids.get(i) + "&fmt=json");
            for (Artist obj:tmp){
                artists.add(obj);
            }*/
            compilations.add(new Compilation(null, titles.get(i), null, ids.get(i)));
        }
        return compilations;
    }
    
    
    private static LinkedList<String> populateRelations(String id) throws IOException{ //member gathering
        String url = "https://musicbrainz.org/ws/2/artist/"+ id +"?inc=artist-rels&fmt=json";
        Request.sleep(2500);
        
        String results = Request.getResponse(url);
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        List<String> names=JsonPath.parse(results).read("$.relations[*].artist.name"), types=JsonPath.parse(results).read("$.relations[*].type");
        LinkedList<String> members=new LinkedList();
        for (int i=0; i<types.size(); i++){
             if (types.get(i).equals("member of band") && names.get(i)!=null) members.add(names.get(i));
        }
        return members;
    }
    
    public static ArrayList<Album> getAlbumsWithName(String index) throws IOException{

        index=index.replace(' ', '_');
        String u = "http://musicbrainz.org/ws/2/release-group/?query=" + index + "&fmt=json";

        return getAlbums(u);
    }
    
    public static ArrayList<Artist> getArtistsWithName(String index) throws IOException{

        index=index.replace(' ', '_');
        String u = "http://musicbrainz.org/ws/2/artist/?query=" + index + "&fmt=json";

        return getArtists(u);
        
    }
    
    public static ArrayList<Release> getReleasesWithName(String index) throws IOException {

        index=index.replace(' ', '_');
        String u = "http://musicbrainz.org/ws/2/release/?query=" + index + "&fmt=json";

        return getReleases(u);
    }
    
    public static ArrayList<Compilation> getCompilationsWithName(String index) throws IOException{
        index=index.replace(' ', '_');
        String u = "http://musicbrainz.org/ws/2/release-group/?query=" + index + "%20AND%20secondarytype:compilation&fmt=json";

        return getCompilations(u);
    }
}
