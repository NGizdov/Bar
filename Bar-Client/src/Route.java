import java.io.*;
import java.net.*;

public class Route
{
    private final static int PORT = 6060;
    private final static String HOST = "localhost";
    private Socket socket;
    private PrintWriter send;
    private BufferedReader comeback;

    public Route() throws UnknownHostException
    {
        this(HOST, PORT);
    }

    public Route(String host) throws UnknownHostException
    {
        this(host, PORT);
    }

    public Route(int port) throws UnknownHostException
    {
        this(HOST, port);
    }

    public Route(String host, int port)
    {
        InetAddress hostAdress = null;
        try
        {
            hostAdress = InetAddress.getByName(host);
            socket = new Socket(hostAdress, 6060);
            send = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
            comeback = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (UnknownHostException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void enterBar(Client client)
    {
        try
        {
            send.println(client.getSmoker());
//            System.out.println(this + " is knocking on the door of the BAR.");
            String premmision = comeback.readLine();
            System.out.println(this + " is entering BAR.");
            send.println(client.getSmoker());
            comeback.readLine();
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
                send.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public String exitBar()
    {
        try
        {
            comeback = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String smoker = comeback.readLine();
            return smoker;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return "FAIL";
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
        }
    }

    public Socket getSocket()
    {
        return socket;
    }

    public boolean closeRoute()
    {
        try
        {
            socket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
