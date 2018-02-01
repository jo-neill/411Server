import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class EventLoopCtrl {
    public static void main(String args[]) {
        System.out.println("Simple Echo Client");
        try {
            System.out.println("Waiting for connection.....");
            InetAddress localAddress = InetAddress.getLocalHost();
            try (Socket clientSocket = new Socket(localAddress, 80);
                PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(
                new InputStreamReader(
                clientSocket.getInputStream()))) {
                    System.out.println("Connected to server");
                    Scanner scanner = new Scanner(System.in);
                    while (true) {
                        System.out.print("Enter text: ");
                        String inputLine = scanner.nextLine();
                        if ("quit".equalsIgnoreCase(inputLine)) {
                            break;
                        }
                        ServerHTMLView view = new ServerHTMLView();
                        String html = view.getHTML();
                        out.write("HTTP/1.1 200 OK\n\n");
                        out.write(html);
                        out.write("\n");
                        System.out.println("Reached while true loop");
                    }
            }
            catch (Exception e){ 

            }
        
        } catch (IOException ex) {
        // Handle exceptions
        }
        
    }
    
}
//Success
// out.write("HTTP/1.1 200 OK\n\n");
// out.write(html);
// out.write("\n");

// //Failure
// out.write("HTTP/1.1 404 Not Found\n\n");
/*
Server (most of the work; basically edited version of class from book), Request (type protocol and url) and View (returns HTML code) classes
*/