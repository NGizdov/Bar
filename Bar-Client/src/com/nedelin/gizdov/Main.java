package com.nedelin.gizdov;

import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Dimension dim = new Dimension(1000, 640);
        Board board = new Board(dim);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(dim);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Bar");
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setBackground(Color.WHITE);
        frame.add(board);

        Random rand = new Random();
        int clients = rand.nextInt(40);
        for (int i = 0; i < clients; i++)
        {
            Client cl = null;
            if(args.length >= 2){
                cl = new Client(((rand.nextInt(50) % 2 == 0) ? "smoker" : "noSmoker"), rand.nextInt(10) * 50, dim, args[0], Integer.parseInt(args[1]));
            } else {
                cl = new Client(((rand.nextInt(50) % 2 == 0) ? "smoker" : "noSmoker"), rand.nextInt(10) * 50, dim);
            }
            board.addClient(cl);
            cl.start();
        }
    }
}
