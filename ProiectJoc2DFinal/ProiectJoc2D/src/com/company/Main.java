package com.company;

import org.w3c.dom.Text;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        // creare fereastra ce poate fi minimizata
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Proiect joc 2D");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack(); // face ca lungimea si latimea ferestrei sa se redim in mod egal

        // fixarea ferestrei pe centrul ecranului
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();

    }
}
