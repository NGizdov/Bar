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
                JFrame fr = new JFrame();
                fr.setSize(680, 640);
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
                JFrame fr = new JFrame();
                fr.setSize(680, 640);
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
                JFrame fr = new JFrame();
                fr.setSize(680, 640);
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
        for (Client client : clients)
        {
            client.getImage().paintIcon(this, g, client.getX(), client.getY());
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
