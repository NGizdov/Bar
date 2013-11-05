package com.nedelin.gizdov;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
    private static final int DEFAULT_PORT = 6060;

    public static void main(String[] args)
    {

        ServerSocket server = null;
        Bar bar = new Bar(5);
        bar.start();
        try
        {
            if (args.length > 0)
            {
                server = new ServerSocket(Integer.parseInt(args[0]));
            }
            else
            {
                server = new ServerSocket(DEFAULT_PORT);
            }
            System.out.println("Server working [port: " + server.getLocalPort() + "] ");
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
