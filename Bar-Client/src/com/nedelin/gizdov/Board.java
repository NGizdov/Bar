package com.nedelin.gizdov;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.swing.*;

public class Board extends JPanel implements ActionListener
{
    private static final long serialVersionUID = 1165573283350129442L;
    private Timer tm;
    private List<Client> clients;
    private static final Random RANDOM = new Random();
    private Dimension dimendion;
    
    public Board(Dimension dim)
    {
        dimendion = dim;
        clients = new LinkedList<Client>();
        tm = new Timer(5, this);
        this.setBackground(Color.WHITE);
        JButton smoker = new JButton("Smoker");
        JButton noSmoker = new JButton("No smoker");
        JButton random = new JButton("Random");
        smoker.addActionListener(new ActionListener()
        {            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client client = new Client("smoker", RANDOM.nextInt(10) * 50, dimendion);
                clients.add(client);
                client.start();
            }
        });
        noSmoker.addActionListener(new ActionListener()
        {            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client client = new Client("noSmoker", RANDOM.nextInt(10) * 50, dimendion);
                clients.add(client);
                client.start();
            }
        });
        random.addActionListener(new ActionListener()
        {            
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Client client = new Client(((RANDOM.nextInt(50) % 2 == 0) ? "smoker" : "noSmoker"), RANDOM.nextInt(10) * 50, dimendion);
                clients.add(client);
                client.start();
            }
        });
        add(smoker);
        add(noSmoker);
        add(random);
    }
    
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        ImageIcon barIcon = new ImageIcon(getClass().getClassLoader().getResource("bar.png"));
        barIcon.paintIcon(this, g, (int) (dimendion.getWidth() - 250), 100);
//        Iterator<Client> iterator = clients.iterator();
//        while (iterator.hasNext())
//        {
//            Client client = iterator.next();
//            if (client.isFinished()) {
//                iterator.remove();
//                continue;
//            }
//            client.getImage().paintIcon(this, g, client.getX(), client.getY());
//        }
        for (int i = 0; i< clients.size();)
        {
            if (clients.get(i).isFinished()) {
//              iterator.remove();
                clients.remove(i);
              continue;
            }
            clients.get(i).getImage().paintIcon(this, g, clients.get(i).getX(), clients.get(i).getY());
            i++;
        }
        tm.start();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
          repaint();      
    }
    
    public void addClient(Client client)
    {
        clients.add(client);
    }
}
