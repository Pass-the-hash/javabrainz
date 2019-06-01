package basics;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;

public class Person extends Artist {
    String Gender;
    private LocalDate BirthDate, DeathDate;
    
    //public Person(){}
    
    public Person(String Gender, String Name, String Country, String Cities, LocalDate BirthDate, LocalDate DeathDate, LinkedList<String> Aliases, LinkedList<String> Tags, String ID){
        super(Name, Country, Cities, Aliases, Tags, ID);
        this.BirthDate=BirthDate;
        this.DeathDate=DeathDate;
        this.Gender=Gender;
        this.ID=ID;
    }
    public Person(String Gender, String Name, String Country, String Cities, LocalDate BirthDate, LinkedList<String> Aliases, LinkedList<String> Tags, String ID){
        super(Name, Country, Cities, Aliases, Tags, ID);
        this.BirthDate=BirthDate;
        this.Gender=Gender;
        this.ID=ID;
    }


    @Override
    public String toString() {
        System.out.println("Name: " + Name);
        if (Country!=null) System.out.println("Country: " + Country);
        if (Gender!=null) System.out.println("Gender: " + Gender);
        //if (Cities[0]!=null) System.out.println("Cities: " + Arrays.toString(Cities));
        if (Cities!=null) System.out.println("City: " + Cities);
        if (BirthDate!=null) System.out.println("Birth date: " + BirthDate);
        if (DeathDate!=null) System.out.println("Death date: " + DeathDate);
        if (Aliases!=null && !Aliases.isEmpty()) System.out.println("Aliases: " + Aliases.toString());
        if (Tags!=null && !Tags.isEmpty()) System.out.println("Tags: " + Tags.toString());
        System.out.println();
        //return "{" + "Name=" + Name + ", Country=" + Country + ", Gender=" + Gender + ", Cities=" + Arrays.toString(Cities) + ", BirthDate=" + BirthDate + ", DeathDate=" + DeathDate + ", Aliases=" + Aliases.toString() + ", Tags=" + Tags.toString() + '}';
        return null;
    }

    public String getGender() {
        return Gender;
    }
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    
    public LocalDate getBirthDate() {
        return BirthDate;
    }
    public void setBirthDate(LocalDate BirthDate) {
        this.BirthDate = BirthDate;
    }

    public LocalDate getDeathDate() {
        return DeathDate;
    }
    public void setDeathDate(LocalDate DeathDate) {
        this.DeathDate = DeathDate;
    }   
}

