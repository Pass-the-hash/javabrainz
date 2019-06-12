package basics;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Arrays;

public class Group extends Artist {
    private LocalDate BeginDate, EndDate;
    private LinkedList<String> Members;
    
    public Group(String Name, String Country, String Cities, LocalDate BeginDate, LinkedList<String> Aliases, LinkedList<String> Tags, LinkedList<String> Members, String ID){
        super(Name, Country, Cities, Aliases, Tags, ID);
        this.Members=Members;
        this.BeginDate=BeginDate;
    }
    public Group(String Name, String Country, String Cities, LocalDate BeginDate, LocalDate EndDate, LinkedList<String> Aliases, LinkedList<String> Tags, LinkedList<String> Members, String ID){
        super(Name, Country, Cities, Aliases, Tags, ID);
        this.Members=Members;
        this.BeginDate=BeginDate;
        this.EndDate=EndDate;
    }


    @Override
    public String toString() {
        System.out.println("Name: " + Name);
        if (Country!=null) System.out.println("Country: " + Country);
        //if (Cities[0]!=null) System.out.println("Cities: " + Arrays.toString(Cities));
        if (Cities!=null) System.out.println("City: " + Cities);
        if (BeginDate!=null) System.out.println("Begin date: " + BeginDate);
        if (EndDate!=null) System.out.println("Death date: " + EndDate);
        if (Aliases!=null && !Aliases.isEmpty()) System.out.println("Aliases: " + Aliases.toString());
        if (Tags!=null && !Tags.isEmpty()) System.out.println("Tags: " + Tags.toString());
        if (Members!=null && !Members.isEmpty())System.out.println("Members: " + Members.toString());
        System.out.println();
        return super.toString() + "{" + ", BeginDate=" + BeginDate + ", EndDate=" + EndDate + ", Members=" + Members.toString() + "}" + "\n";
    }
   
    public LocalDate getBeginDate() {
        return BeginDate;
    }
    public void setBeginDate(LocalDate BeginDate) {
        this.BeginDate = BeginDate;
    }

    public LocalDate getEndDate() {
        return EndDate;
    }
    public void setEndDate(LocalDate EndDate) {
        this.EndDate = EndDate;
    } 
    
    public LinkedList<String> getMembers() {
        return Members;
    }
    public void setMembers(LinkedList<String> Members) {
        this.Members = Members;
    }

}    


