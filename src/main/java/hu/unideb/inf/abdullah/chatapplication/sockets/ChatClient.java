package hu.unideb.inf.abdullah.chatapplication.sockets;

import java.io.*;
import java.net.Socket;


public class ChatClient implements Runnable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
   // private boolean done;

    public String m = "Abdullah";


    @Override
    public void run() {
        try{
            client = new Socket("Localhost", 8080);
            out = new PrintWriter(client.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String inMessage;
            while((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
                //HelloController.updateHistoryBox(inMessage); //TODO Delete this

            }
        } catch (IOException e){
            shutdown();
        }
    }

    public void sendMessage(String message){
        out.println(message);
    }

    public void shutdown(){
       // done = true;
        try {
            in.close();
            out.close();
            if (!client.isClosed()){
                client.close();
            }
        } catch (IOException e) {
            //ignore
        }
    }
}

