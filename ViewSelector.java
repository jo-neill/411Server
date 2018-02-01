import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;


public class ViewSelector extends View {

    Path path;
    int status = 0;
    
    public ViewSelector(String fileName){
        if (fileName.equals("/")|| fileName.equals("")){
            fileName = "/index.html";
        }
        path = Paths.get("public" + fileName);
        if (Files.exists(path)){

        }
        else{
            status = -1;
        }
    }
    
    public String getType() {
        if (path.toString().endsWith(".jpeg")){
            return "image/jpeg";
        }
        return "text/html";
    }

    public static void checkFiles(String fileName){
        //BufferedReader fileReader = null;
        try {
            File f = new File("public/" + fileName);
            if(f.exists() && !f.isDirectory()) {
                
            }
        } catch (Exception e) {
            System.out.println("No public document with this name");
        } 
    }

    public boolean isOKStatus(){
        return (status ==0);
    }

    public byte[] makeBytes() {
        try{
            return Files.readAllBytes(path);
        } catch (IOException e){
            return new byte[0];
        }
    }

    @Override
    public String makeHTML(){
        try{
            String s = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
        } catch (IOEception ex){
            //Logger.getLogger(FileView.class.getName()).log
        }
    }

}