package hu.unideb.inf.abdullah.chatapplication.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer implements Runnable{

    private ArrayList<ConnectionHandler> connections;
    private ServerSocket server;
    private boolean done;
    private ExecutorService pool;

    public ChatServer() {
        connections = new ArrayList<>();
        done = false;
    }

    @Override
    public void run() {
        System.out.println("Server is working");

        try {
            server = new ServerSocket(8080);
            pool = Executors.newCachedThreadPool();
            while (!done){
                Socket client = server.accept();
                ConnectionHandler handler = new ConnectionHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (Exception e) {
            shutdown();
        }

    }

    public void broadcast(String message){
        for (ConnectionHandler ch : connections){
            ch.sendMessage(message);
        }
    }

    public void shutdown(){
            try {
                done = true;
                pool.shutdown();
                if (!server.isClosed()){
                    server.close();
                }
                for (ConnectionHandler ch : connections){
                    ch.shutdown();
                }
            } catch (IOException e) {
                //ignore it
            }
    }

    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;

        private String nickName;

        public ConnectionHandler(Socket client){
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(),true);
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out.println("Please enter a nickname: ");
                nickName = in.readLine();
                System.out.println(nickName + " Connected!");
                broadcast(nickName + " joined the chat!");
                String message;
                while ((message = in.readLine())!= null){
                    if (message.startsWith("/nick ")){
                        //TODO : change nickname
                    }else if (message.startsWith("/quit")){
                        broadcast(nickName + " has left the chat!");
                        shutdown();
                    }else {
                        broadcast(nickName + ": " + message);
                    }
                }
            } catch (IOException e){
                shutdown();
            }
        }
        public void sendMessage(String message){
            out.println(message);
        }

        public void shutdown(){
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e){
                //
            }
        }
    }
}
