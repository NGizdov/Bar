import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends JFrame
{
    public Main() {

        add(new Board());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(280, 240);
        setLocationRelativeTo(null);
        setTitle("Star");
        setResizable(false);
        setVisible(true);

    }
    
    public static void main(String[] args) throws UnknownHostException
    {
        Main main = new Main();
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
