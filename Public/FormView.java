public class FormView extends View{

    public String getHTML(){
        return "<html><body><form action='/submit'>Name: <input type='text' name='name'><br>Street: <input type='text' name='street'><br>State: <input type='text' name='state'><br>Zip: <input type='text' name='zip'><br><input type='submit' value='Submit'><br></form></body></html>";
    }

    public String getErrorHTML(String[] inputs){
        String error = "";
        if("".equals(inputs[0])){
            error += "<p>Error with name.</p><br>";
        }
        if("".equals(inputs[1])){
            error += "<p>Error with street.</p><br>";
        }
        if("".equals(inputs[2])){
            error += "<p>Error with state.</p><br>";
        }
        if("".equals(inputs[3])){
            error += "<p>Error with zip.</p><br>";
        }
        
        return "<html><body><form action='/submit'>Name: <input type='text' name='name' value=" + inputs[0] + "><br>Street: <input type='text' name='street' value=" + inputs[1] + "><br>State: <input type='text' name='state' value=" + inputs[2] + "><br>Zip: <input type='text' name='zip' value=" + inputs[3] + "><br><input type='submit' value='Submit'><br></form><br>" + error + "</body></html>";
    }

}