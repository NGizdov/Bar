package com.nedelin.gizdov;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import javax.swing.ImageIcon;

public class Client extends Thread
{

    private String name;
    static int num = 0;
    private final String smoker;
    private PrintWriter send;
    private BufferedReader comeback;
    private static final String LOCAL_HOST = "localhost";
    private static final int PORT = 6060;
    private static final String ENTER_EXTENSION = ".png";
    private static final String EXIT_EXTENSION = "Mirror.png";
    private int x;
    private int varX;
    private int y;
    private int varY;
    private ImageIcon image;
    private Dimension board;
    private int delay;

    public Client(String smoker, int delay, Dimension board)
    {
        this(smoker, delay, board, LOCAL_HOST, PORT);
    }
    
    public Client(String smoker, int delay, Dimension board, String host, int port)
    {
        this.delay = delay;
        num++;
        this.name = "Client - " + num;
        this.smoker = smoker;
        this.image = new ImageIcon("/home/bitmix/workspace/Bar/Bar-Client/resources/" + this.smoker + ENTER_EXTENSION);
        InetAddress hostAdress = null;
        Socket socket = null;
        varX = 20;
        varY = 20;
        this.board = board;
        y = 100;
        if ("smoker".equalsIgnoreCase(this.smoker))
        {
            y = 300;
        }
        try
        {
            hostAdress = InetAddress.getByName(host);
            socket = new Socket(hostAdress, port);
            send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            comeback = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        try
        {
            walkToBar();
            System.out.println(this + " is knocking on the door of the BAR.");
            send.println(smoker);
            String premmision = comeback.readLine();
            System.out.println("You have : " + premmision);
            System.out.println(this + " is entering BAR.");
            
            send.println("Enter");
            comeback.readLine();
            this.image = new ImageIcon("/home/bitmix/workspace/Bar/Bar-Client/resources/" + this.smoker + EXIT_EXTENSION);
            walkFromBar();
            System.out.println(this + " is exiting BAR.");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                comeback.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            send.close();
        }
    }

    private void walkToBar()
    {
        while (x <= (this.board.getWidth() - 90)){
            x += varX;
            try
            {
                sleep((long) delay);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    private void walkFromBar()
    {
        while (x > 0){
            x -= varX;
            try
            {
                sleep((long) delay);
            }
            catch (InterruptedException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString()
    {
        return this.name + " (" + this.smoker + ") ";
    }

    public String getSmoker()
    {
        return smoker;
    }
    
    public int getVarY()
    {
        return varY;
    }

    public void setVarY(int varY)
    {
        this.varY = varY;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getVarX()
    {
        return varX;
    }

    public void setVarX(int varX)
    {
        this.varX = varX;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public ImageIcon getImage()
    {
        return image;
    }

    public void setImage(ImageIcon image)
    {
        this.image = image;
    }
}
