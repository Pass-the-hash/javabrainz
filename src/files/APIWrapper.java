package files;

import basics.*;

import com.google.gson.*;

import com.jayway.jsonpath.*;

import org.jsoup.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class APIWrapper {

    private static String getResponse(String u) throws IOException{

        URL url = new URL(u);
        url.openConnection();

        String response;
        try (Scanner scanner = new Scanner(url.openStream())) {
            response = scanner.useDelimiter("\\Z").next();
        }
        url.openStream().close();
        return response;
    }
    private static int CheckDate(String date){
       return date.length() - date.replaceAll("-","").length();
    }
    
    public static void artistDeserialize() throws IOException{
        String results=getResponse("http://musicbrainz.org/ws/2/release/?query=" + "dynasty" + "&fmt=json");
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        //int i=5;
        List p=JsonPath.using(conf).parse(results).read("$.releases[*].media");
        List test;
        for (int j=0; j<p.size(); j++){
            test=null;
            //if (p.get(j)!=null) test=(String) p.get(j);
            if(p.get(j)!=null) test=JsonPath.using(conf).parse(results).read("$.releases["+j+"].media[*].format");
            System.out.println(p.get(j));
            System.out.println(test);
            
        }
        //System.out.println(p.size());
        //System.out.println(test.size());
    }
    
    /* public static ReleaseDeserializer releaseDeserialize(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, ReleaseDeserializer.class);
    }*/
    
    public static ArrayList<Artist> genericArtistRequest(String u) throws IOException{
        String response = getResponse(u);
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
            System.out.println(requests);
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
                if (requests<13){members=populateRelations(ids.get(i)); requests++;}
                if (begin!=null && end!=null && CheckDate(begin)==2 && CheckDate(end)==2) group=new Group(names.get(i), country, city, LocalDate.parse(begin), LocalDate.parse(end), alias, tag, members, ids.get(i));
                else if (begin!=null && CheckDate(begin)==2) group=new Group(names.get(i), country, city, LocalDate.parse(begin), null, alias, tag, members, ids.get(i));
                else group=new Group(names.get(i), country, city, null, alias, tag, members, ids.get(i));
                artists.add(group);
            }
        }
        return artists;
    }

    private static ArrayList<Release> genericReleaseRequest(String u) throws IOException {
        String response = getResponse(u);
        Configuration conf=Configuration.defaultConfiguration().addOptions(Option.DEFAULT_PATH_LEAF_TO_NULL, Option.SUPPRESS_EXCEPTIONS);
        ArrayList<Release> releases=new ArrayList();
        
        List<String> titles=JsonPath.read(response, "$.releases[*].title"), ids=JsonPath.read(response, "$.releases[*].id"), statuses=JsonPath.using(conf).parse(response).read("$.releases[*].status");
        List<String> dates=JsonPath.using(conf).parse(response).read("$.releases[*].date");
        List<String> list;
        List media=JsonPath.using(conf).parse(response).read("$.releases[*].media");
        List<Integer >tracks=JsonPath.using(conf).parse(response).read("$.releases[*].track-count");
        String date = null, language;
        String[] format;
        for (int i=0; i<ids.size(); i++) {
            format=null;
            //System.out.println(media.get(i));
            language=null; format=new String[5];
            language=JsonPath.using(conf).parse(response).read("$.releases["+i+"].text-representation.language");
            if (media.get(i)!=null){list=JsonPath.using(conf).parse(response).read("$.releases["+i+"].media[*].format"); format=new String[5]; for (int j=0; j<list.size()&&j<5; j++) format[j]=list.get(j);}
            if (dates.get(i)!=null) date=(String) dates.get(i);
            if (dates.get(i)!=null && CheckDate(dates.get(i))==2) releases.add(new Release(titles.get(i), language, format, statuses.get(i), LocalDate.parse(dates.get(i)), tracks.get(i), ids.get(i)));
            else releases.add(new Release((String)titles.get(i), language, format, statuses.get(i), null, tracks.get(i), ids.get(i)));
        }
        return releases;
    }
    
    /*private static ArrayList genericAlbumRequest(String u) throws IOException{
        String response = getResponse(u);
        ArrayList albums=new ArrayList();
        //ArrayList compilations=new ArrayList();
        ArrayList<Artist> artist_arr;
        LinkedList<Artist> artists=new LinkedList();
        String title, status, id;
        long track;
        Genson genson=new GensonBuilder().useRuntimeType(true).create();
        Map<String, ArrayList> json = genson.deserialize(response, Map.class);
        ArrayList<HashMap> releases=json.get("release-groups");
        ArrayList<HashMap> secondary, credit, release;
        HashMap artist_id;
        String type;
        for (int i=0; i<releases.size(); i++){
            track=(long) releases.get(i).get("count");
            id=(String) releases.get(i).get("id");
            title=(String) releases.get(i).get("title");
            release=(ArrayList) releases.get(i).get("releases");
            status=(String) release.get(0).get("status");
            if (releases.get(i).get("secondary-types") != null) {
                secondary = (ArrayList) releases.get(i).get("secondary-types");
                HashMap index = (HashMap) secondary.get(0);
                type = (String) index.get(0);
                if (type.equals("Compilation")){
                    credit=(ArrayList) releases.get(i).get("artist-credit");
                    artist_id=(HashMap) credit.get(0).get("artist");
                    artist_arr=APIWrapper.genericArtistRequest("http://musicbrainz.org/ws/2/artist/"+ artist_id.get("id") +"?inc=aliases&fmt=json");
                    for (Artist j: artist_arr){
                        artists.add(j);
                    }
                    albums.add(new Compilation(artists, title, null, null, status, null, (int) track, id));
                }
            } else{
                credit = (ArrayList) releases.get(i).get("artist-credit");
                artist_id = (HashMap) credit.get(0).get("artist");
                artist_arr = APIWrapper.genericArtistRequest("http://musicbrainz.org/ws/2/artist/" + artist_id.get("id") + "?inc=aliases&fmt=json");
                for (Artist j : artist_arr) {
                    artists.add(j);
                }
                albums.add(new Album(artists.get(0), title, null, null, status, null, (int) track, id));
            }
        }
        return albums;
    }*/
    
    static String urlEncodeUTF8(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new UnsupportedOperationException(e);
        }
    }
    
    private static LinkedList<String> populateRelations(String id) throws IOException{ //member gathering
        String url = "https://musicbrainz.org/ws/2/artist/"+ id +"?inc=artist-rels&fmt=json";
        String results = Jsoup.connect(url).ignoreContentType(true).execute().body();
        Gson gson = new Gson();
        RelationDeserializer relations=gson.fromJson(results, RelationDeserializer.class);
        LinkedList<String> members=new LinkedList();
        for (RelationDeserializer.Relation i:relations.relations){
             if (i.type.equals("member of band")) members.add(i.artist.name);
        }
        return members;
    }
    /*public static ArrayList<Album> getAlbums(String index) throws IOException{

        //String encoded  = urlEncodeUTF8(hmap);
        String u = "http://musicbrainz.org/ws/2/release-group/?query=" + index + "&fmt=json";

        return genericAlbumRequest(u);
    }*/
    
    public static ArrayList<Artist> getArtists(String index) throws IOException{

        //String encoded  = urlEncodeUTF8(hmap);
        String u = "http://musicbrainz.org/ws/2/artist/?query=" + index + "&fmt=json";

        return genericArtistRequest(u);
        
    }
    
    public static ArrayList<Release> getReleases(String index) throws IOException {

        //String encoded = urlEncodeUTF8(hmap);
        String u = "http://musicbrainz.org/ws/2/release/?query=" + index + "&fmt=json";

        return genericReleaseRequest(u);
    }
    
    /*public static ArrayList<Compilation> getCompilations(String index) throws IOException{
        //String encoded  = urlEncodeUTF8(hmap);
        String u = "http://musicbrainz.org/ws/2/release-group/?query=" + index + "%20AND%20secondarytype:compilation&fmt=json";

        return genericAlbumRequest(u);
    }*/
}
