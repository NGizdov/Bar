import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client extends Thread
{

    private String name;
    static int num = 0;
    private final String smoker;
    private PrintWriter send;
    private BufferedReader comeback;
    private static final String LOCAL_HOST = "localhost";
    private static final int PORT = 6060;
    private static final int SLEEP_COEF = 2000;

    public String getSmoker()
    {
        return smoker;
    }

    public Client(String smoker)
    {
        this(smoker, LOCAL_HOST, PORT);
    }

    public Client(String smoker, String host, int port)
    {
        num++;
        this.name = "Client - " + num;
        this.smoker = smoker;
        InetAddress hostAdress = null;
        Socket socket = null;
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
            System.out.println(this + " walk to BAR!");
            sleep((long) (Math.random() * SLEEP_COEF));
            System.out.println(this + " is knocking on the door of the BAR.");
            send.println(smoker);
            String premmision = comeback.readLine();
            System.out.println("You have : " + premmision);
            System.out.println(this + " is entering BAR.");
            send.println("Enter");
            comeback.readLine();
            System.out.println(this + " is exiting BAR.");
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
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

    @Override
    public String toString()
    {
        return this.name + " (" + this.smoker + ") ";
    }

}
