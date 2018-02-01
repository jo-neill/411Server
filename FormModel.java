import java.io.StreamTokenizer;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

public class FormModel {

    public String name;
    public String street;
    public int zip;
    public String state;

    public List states = Arrays.asList("AK", "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI" ,"WY");

    public FormModel (Map<String,String> fields){
        try{
            name = fields.get("name");
        } catch(Exception e){
            System.out.println("Invalid name entry");
        }
        try{
            street = fields.get("street");
        } catch(Exception e){
            System.out.println("Invalid street entry");
        }
        try{
            zip = Integer.parseInt(fields.get("zip"));
        } catch(Exception e){
            System.out.println("Invalid zip entry");
        }
        try{
            state = fields.get("state");
        } catch(Exception e){
            System.out.println("Invalid state entry");
        }
    }

    public boolean validate(){
        if ((name != null) && (street != null) && (state != null)){
            if(zip > 0 && zip < 100000){
                if (states.contains(state)){
                    System.out.println("Valid form inputs");
                    return true;
                }
            }
        }
        return false;
    }

}