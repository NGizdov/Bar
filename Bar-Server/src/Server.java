import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    public static void main(String[] args)
    {
        ServerSocket server = null;
        Bar bar = new Bar(5);
        bar.start();
        try
        {
            server = new ServerSocket(6060);
            System.out.println("Server working [port: " + 6060 + "] ");
            while (true)
            {
                Socket socket = server.accept();
                System.out.println("Accept: " + socket);
                try
                {
                    Client client = new Client(socket, bar);
                    client.start();
                }
                catch (IOException ex)
                {
                    socket.close();
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                server.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

    }
}
