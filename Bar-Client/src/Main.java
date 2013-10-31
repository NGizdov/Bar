import java.net.UnknownHostException;
import java.util.Random;

import javax.swing.JFrame;

public class Main extends JFrame
{
    Board board;
    public Main() {
        board = new Board();
        add(board);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(680, 640);
        setLocationRelativeTo(null);
        setTitle("Bar");
        setResizable(false);
        setVisible(true);

    }
    
    public static void main(String[] args) throws UnknownHostException
    {
        new Main();
        
//        // generirane na proizvolen broi klienti
        Random rand = new Random();
        int clients = rand.nextInt(40);
        for (int i = 0; i < clients; i++)
        {
            Client cl = new Client(((rand.nextInt(50) % 2 == 0) ? "smoker" : "noSmoker"));
            cl.start();
        }
    }
}
