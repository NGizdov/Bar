import java.io.IOException;

public class Bar extends Thread
{
    // Tova e razmera na bara(kolko e maksimuma na bara kato hora)
    private int size;

    // Tova e broiach na horata v bara
    private int count;

    // Tova pokazwa sega poshachi li ima ili nepushachi
    private String smoker;

    public static int entries;

    public Bar(int size)
    {
        this.size = size;
        this.count = 0;
        this.smoker = "undefined";
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                sleep(10000);
                if (this.count == 0)
                {
                    this.smoker = this.smoker.equalsIgnoreCase("smoker") ? "noSmoker" : "smoker";
                }
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }

    public synchronized void entering(Client client)
    {
        while (!((this.count < this.size) && (entries < 9) && ((this.smoker.equalsIgnoreCase(client.getSmoker()) || (this.smoker
                .equalsIgnoreCase("undefined"))))))
        {
            try
            {
                wait((long) Math.random() * 2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        try
        {
            client.getExit().println("Yes");
            client.getEnter().readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        if (this.count == 0)
        {
            this.smoker = client.getSmoker();
        }
        System.out.println("(" + this.smoker + ") " + "Entering the BAR");

        this.count++;
        entries++;
    }

    public synchronized void exiting()
    {
        this.count--;
        System.out.println("The " + this.smoker + " exits the BAR!");
        if ((this.count == 0))
        {
            entries = 0;
            this.smoker = this.smoker.equalsIgnoreCase("smoker") ? "noSmoker" : "smoker";
        }
        notifyAll();
    }
}
