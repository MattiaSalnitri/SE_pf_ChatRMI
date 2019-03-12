package it.polimi.deib.mattiaSalnitri.se2019.chatRMI;

import java.rmi.Naming;
import java.util.Scanner;

public class SimpleChatClient {

    public static void main (String[] argv) {
        try {

            //set up
            Scanner s=new Scanner(System.in);
            System.out.println("Enter Your name and press Enter:");
            String name=s.nextLine().trim();

            //create the instance of the client
            ChatInterface client = new Chat(name);

            //get the server remote object
            ChatInterface server = (ChatInterface) Naming.lookup("rmi://localhost/ABC");

            //send a msg to the server that is conneted
            String msg="["+client.getName()+"] got connected";
            server.send(msg);

            System.out.println("[System] Chat Remote Object is ready:");

            //set up the server remote object with the te remote object of the client
            server.setClient(client);

            //keep sending msgs
            while(true){
                msg=s.nextLine().trim();
                msg="["+client.getName()+"] "+msg;
                server.send(msg);
            }

        }catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }

}
