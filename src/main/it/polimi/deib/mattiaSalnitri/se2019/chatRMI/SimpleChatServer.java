package it.polimi.deib.mattiaSalnitri.se2019.chatRMI;

import java.rmi.Naming;
import java.util.Scanner;

public class SimpleChatServer {

    public static void main (String[] argv) {
        try {
            //System.setSecurityManager(new RMISecurityManager());

            //set up
            Scanner s=new Scanner(System.in);
            System.out.println("Enter Your name and press Enter:");
            String name=s.nextLine().trim();

            //create the object to be exposed
            Chat server = new Chat(name);

            //bind the object
            Naming.rebind("rmi://localhost/ABC", server);

            System.out.println("[System] Chat Remote Object is ready:");

            //keep waiting new mesages from shell
            while(true){
                //read a message ( and wait)
                String msg=s.nextLine().trim();

                //if there is no server skip
                if (server.getClient()!=null){

                    //when a client call the remote instance of the server it register the client
                    ChatInterface client=server.getClient();
                    //set up the message
                    msg="["+server.getName()+"] "+msg;
                    //send the message ( i.e. print on the client the message)
                    client.send(msg);
                }
            }

        }catch (Exception e) {
            System.out.println("[System] Server failed: " + e);
        }
    }

}
