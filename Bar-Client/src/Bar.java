import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Bar {

	//���� � ������� �� ����(��������� � ��������� �� ���� ���� ����)
	//Tova e razmera na bara(kolko e maksimuma na bara kato hora)
	private int size;
	
	//���� � ����� �� ������ � ����
	//Tova e broiach na horata v bara
	private int count;
	
	//���� ������� ���� ������ �� ��� ��� ��������
	//Tova pokazwa sega poshachi li ima ili nepushachi
	private String smoker;
	
	
	public static int entries;
	
	
//	public Bar(int size) 
//	{
//		this.size = size;
//		this.count = 0;
//		this.smoker = "undefined";
//	}
	
	private final static int PORT = 6060;
    private final static String HOST = "localhost";
    private Socket socket;
    private BufferedWriter send;
    private BufferedReader comeback;

    public Bar(int size) throws UnknownHostException
    {
        this(HOST, PORT, size);
    }

    public Bar(String host, int size) throws UnknownHostException
    {
        this(host, PORT, size);
    }

    public Bar(int port, int size) throws UnknownHostException
    {
        this(HOST, port, size);
    }

    public Bar(String host, int port, int size)
    {
        this.size = size;
      this.count = 0;
        InetAddress hostAdress = null;
        try
        {
            hostAdress = InetAddress.getByName(host);
            socket = new Socket(hostAdress, 6060);

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

    public void enterBar(String smoker)
    {
        try
        {
            send = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            System.out.println(this + " is knocking on the door of the BAR.");
            send.write(smoker);
            comeback = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String premmision = comeback.readLine();
            System.out.println(this + " is entering BAR.");
            send.write(smoker);
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
