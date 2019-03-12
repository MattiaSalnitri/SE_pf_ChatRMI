package it.polimi.deib.mattiaSalnitri.se2019.chatRMI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Chat extends UnicastRemoteObject implements ChatInterface   {

    public String name;
    public ChatInterface client=null;

    public Chat(String n)  throws RemoteException {
        this.name=n;
    }
    public String getName() throws RemoteException {
        return this.name;
    }

    public void setClient(ChatInterface c){
        client=c;
    }

    public ChatInterface getClient(){
        return client;
    }

    public void send(String s) throws RemoteException{
        System.out.println(s);
    }

}
