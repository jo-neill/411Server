import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class HTTPServer {

    //public ArrayList<Map<String, String>> submissions;

    public static void main(String args[]) {
        System.out.println("Simple server");
        runEventLoop();      
    }

    public static void runEventLoop(){
        try (ServerSocket serverSocket = new ServerSocket(12345)){
            while (true) {  
                AddressListModel addresses = AddressListModel.makeAddressListModelFromFile("addresses.txt"); 
                Socket clientSocket = serverSocket.accept();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
                        String inputLine;
                        inputLine = br.readLine();
                        HTTPRequest req = new HTTPRequest(inputLine);
                        String html = "";
                        
                        if ("quit".equalsIgnoreCase(req.url)) {
                            break;
                        }

                        else if ("hello".equalsIgnoreCase(req.url)){
                            HelloView viewHtml = new HelloView();
                            html = viewHtml.getHTML();
                        }

                        else if ("address".equalsIgnoreCase(req.url)){
                            FormView viewHtml = new FormView();
                            html = viewHtml.getHTML();                            
                        }
                        else if ("list".equalsIgnoreCase(req.url)){
                            ListView listView = new ListView(addresses); 
                            html = listView.getHTML();
                        }

                        else if ("favicon.ico".equalsIgnoreCase(req.url)){
                            ListView listView = new ListView(addresses); 
                            html = listView.getHTML();
                        }

                        else if (req.url.indexOf("?") >= 0) {
                            String[] keyVal;
                            String p = req.url.substring(req.url.indexOf("?") + 1); 
                            Map<String,String> params = new HashMap<String, String>();
                            for (String pair : p.split("&")){
                                keyVal = pair.split("=");
                                if(keyVal.length == 2){
                                    params.put(keyVal[0], keyVal[1]);
                                }
                            }

                            AddressModel mod = new AddressModel(params);

                            if(mod.validate().equalsIgnoreCase("Error code:")){
                                addresses.addAddress(mod);
                                addresses.saveToFile("addresses.txt");
                                System.out.println("Address added.");
                                ThankYouView viewHtmlSubmit = new ThankYouView(mod.name);
                                html = viewHtmlSubmit.getHTML();
                            }
                            else {
                                String name = "", street = "", state = "", zip = "";
                                System.out.println("Failed validation.");
                                if(!mod.validate().contains("name")){
                                    name = mod.name;
                                }
                                if(!mod.validate().contains("street")){
                                    street = mod.street;
                                }
                                if(!mod.validate().contains("state")){
                                    state = mod.state;
                                }
                                if(!mod.validate().contains("zip")){
                                    zip = Integer.toString(mod.zip);
                                }
                                String[] errors = {name, street, state, zip};
                                FormView viewHtmlForm = new FormView();
                                html = viewHtmlForm.getErrorHTML(errors);
                            } 
                        }
                        /*else {
                            ViewSelector view = new ViewSelector(req.url);
                            if (view.getType()){

                            }
                        }*/
                        out.write("HTTP/1.1 200 OK\n\n");
                        out.write(html);
                        out.write("\n");
                    }    
            
            catch (Exception e){ 
                    System.out.println("Couldn't connect");
            }
        }
            
        } catch (IOException ex) {
            System.out.println("IOException");
        }
    }
    
}