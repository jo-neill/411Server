public class ThankYouView extends View{

    String name;

    public ThankYouView (String name){
        this.name = name;
    }

    public String getHTML(){
         return "<html><header><title>Accepted</title></header><body>Thank you, " + name + ". Your address has been received.</body></html>";
    }

}