import java.util.ArrayList;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddressListModel{

    ArrayList<AddressModel> addresses;
    String addressString = "";
    private static final String NEW_LINE_SEPARATOR = "\n";

    public AddressListModel(){
        addresses = new ArrayList<AddressModel>();
    }

    public void addAddress(AddressModel address){
        addresses.add(address);
    }

    @Override
    public String toString(){
        for (AddressModel add : addresses){
            addressString += "<p>" + "<strong>Name:</strong> " + add.name + "</p>";
            addressString += "<p>" + "<strong>Street:</strong> " + add.street + "</p>";
            addressString += "<p>" + "<strong>State:</strong> " + add.state + "</p>";
            addressString += "<p>" + "<strong>Zip:</strong> " + add.zip + "</p>";
            addressString += "<br>";
        }
        return addressString;
    }


    public void saveToFile(String fileName){
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(fileName);
            for (AddressModel add : addresses){
                fileWriter.append(add.serializeToString());
                fileWriter.append(NEW_LINE_SEPARATOR);
            }

        } catch (Exception e) {
            System.out.println("Error in file writing");
            e.printStackTrace();

        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Error while flushing/closing fileWriter !!!");
                e.printStackTrace();
            }
        }
    }

    //Method for getting an AddressListModel object from the CSV file
    public static AddressListModel makeAddressListModelFromFile (String fileName){
        BufferedReader fileReader = null;
        AddressListModel adds;
        try {
            //Create a new list of student to be filled by CSV file data
            adds = new AddressListModel();
            String line = "";
                
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
            
            //Read the file line by line starting from the second line
            while ((line = fileReader.readLine()) != null) {
                AddressModel add = AddressModel.deSerializeFromString(line);
                //Perhaps the naming here could be better
                adds.addAddress(add);
            }
            return adds;
        } catch (Exception e) {
            System.out.println("Error in file reading !!!");
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!!");
                e.printStackTrace();
            }
        }
        return null;
            
    }

}