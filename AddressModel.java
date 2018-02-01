import java.io.StreamTokenizer;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.HashMap;

public class AddressModel {

    public String name;
    public String street;
    public int zip;
    public String state;

    public List states = Arrays.asList("AK", "AL", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI" ,"WY");

    public AddressModel (Map<String,String> fields){
        try{
            name = fields.get("name");
            name.replace('+', ' ');
        } catch(Exception e){
            System.out.println("Invalid name entry");
        }
        try{
            street = fields.get("street");
            street.replace('+', ' ');
        } catch(Exception e){
            System.out.println("Invalid street entry");
        }
        try{
            zip = Integer.parseInt(fields.get("zip"));
        } catch(Exception e){
            System.out.println("Invalid zip entry: " + zip);
        }
        try{
            state = fields.get("state");
        } catch(Exception e){
            System.out.println("Invalid state entry");
        }
    }

    public String validate(){
        String error = "Error code:";
        if (name == null){
            error += "name";
        }
        if (street == null) {
            error += "street";
        }
        if (state == null || !states.contains(state)){
            error += "state";
        }
        if(!(zip > 0 && zip < 100000)){
            error += "zip";
        }
        return error;
    }

    public String serializeToString(){
        return "name=" + name + "&street=" + street + "&zip=" + zip + "&state=" + state;
    }

    public static AddressModel deSerializeFromString(String s){
        String[] keyVal;
        Map<String,String> params = new HashMap<String, String>();
        for (String pair : s.split("&")){
            keyVal = pair.split("=");
            if(keyVal.length == 2){
                params.put(keyVal[0], keyVal[1]);
            }
        }
        return new AddressModel(params);
    }

}