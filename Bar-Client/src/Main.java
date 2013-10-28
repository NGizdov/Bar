import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends JFrame
{
    
    public static void main(String[] args) throws UnknownHostException
    {
//        // generirane na proizvolen broi klienti
        Random rand = new Random();
        int clients = rand.nextInt(40);
//
        for (int i = 0; i < clients; i++)
        {
            Client cl = new Client(((rand.nextInt(50) % 2 == 0) ? "smoker" : "noSmoker"));
            cl.start();
        }
    }
}
