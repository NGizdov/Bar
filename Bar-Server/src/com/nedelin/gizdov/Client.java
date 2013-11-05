package com.nedelin.gizdov;
import java.io.*;
import java.net.Socket;

public class Client extends Thread
{
    public static final String ENTER = "enter";
    public static final String EXIT = "exit";
    private static final int SLEEP_COEF = 10000;

    private String smoker;
    private PrintWriter exit;
    private BufferedReader enter;
    private Socket socket;
    private Bar bar;

    public Client(Socket socket, Bar bar) throws IOException
    {
        this.socket = socket;
        this.bar = bar;
        this.enter = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.exit = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
    }

    @Override
    public void run()
    {
        try
        {
            this.smoker = enter.readLine();
            enterBar(this.bar);
            sleep((long) (Math.random() * SLEEP_COEF));
            exitBar(this.bar);
            exit.println(EXIT);
            closeConnections();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void closeConnections() throws IOException
    {
        this.exit.close();
        this.enter.close();
        this.socket.close();
    }
    
    private void enterBar(Bar bar){
        bar.entering(this);
    }
    
    private void exitBar(Bar bar){
         bar.exiting();
    }

    public String getSmoker()
    {
        return smoker;
    }

    public BufferedReader getEnter()
    {
        return enter;
    }

    public PrintWriter getExit()
    {
        return exit;
    }

    public Socket getSocket()
    {
        return socket;
    }
}
