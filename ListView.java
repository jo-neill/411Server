public class ListView extends View{

    AddressListModel addresses = new AddressListModel();
    
    public ListView (AddressListModel addresses){
        this.addresses = addresses;
    }

    public String getHTML(){
         try{
             return "<html><header><title>List</title></header><body><h3>Address List</h3>" + addresses.toString() + "</body></html>";
         } catch (Exception e){
             return "<html><header><title>List</title></header><body></body></html>";
         }
    }


}