package basics;

import java.util.LinkedList;

public abstract class Artist {      // Αφηρημένη κλάση, την οποία κληρονομούν οι κλάσεις Person και Group.
    protected String Name, Country, ID, Cities;
    //protected String[] Cities;
    protected LinkedList<String> Aliases, Tags;
    
    public Artist(String Name, String Country, String Cities, LinkedList<String> Aliases, LinkedList<String> Tags, String ID){
        this.Name = Name;
        this.Country = Country;
        this.Cities = Cities;
        this.Aliases = Aliases;
        this.Tags = Tags;
        this.ID=ID;
    }

    @Override
    public String toString() {
        return "Artist{" + "Name=" + Name + ", Country=" + Country + ", ID=" + ID + ", Cities=" + Cities  + ", Aliases=" + Aliases + ", Tags=" + Tags + '}';
    }


    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCities() {
        return Cities;
    }

    public void setCities(String Cities) {
        this.Cities = Cities;
    }

    public LinkedList<String> getAliases() {
        return Aliases;
    }

    public void setAliases(LinkedList<String> Aliases) {
        this.Aliases = Aliases;
    }

    public LinkedList<String> getTags() {
        return Tags;
    }

    public void setTags(LinkedList<String> Tags) {
        this.Tags = Tags;
    }
    
}
