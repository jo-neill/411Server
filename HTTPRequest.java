public class HTTPRequest{

    public String command;
    public String url;
    public String protocol;

    public HTTPRequest (String req){
        parser(req);
    }

    public void parser(String req){
        command = req.substring(0, 3);

        int urlBegin = req.indexOf('/') + 1;
        int urlEnd = req.indexOf(' ', urlBegin);
        url = req.substring(urlBegin, urlEnd);

        int protocolBegin = urlEnd + 1;
        int protocolEnd = req.indexOf('/', protocolBegin);
        protocol = req.substring(protocolBegin, protocolEnd);
    }

}