package com.company;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

// subclasa ce va afisa jocul
public class GamePanel extends JPanel implements Runnable {

    // setari ecran
        final int originalTileSize = 16; // 16x16 tile (marime default a player-ului/inamicilor/map tiles)
        final int scale = 4; // pentru scalarea tile-urilor (16x3=48px)

        public final int tileSize = originalTileSize * scale; // 48x48 tile
        public final int maxScreenCol = 16; // tiles afisate pe orizontala
        public final int maxScreenRow = 12; // tiles afisate pe verticala
        public final int screenWidth = tileSize * maxScreenCol; // 768 px
        public final int screenHeight = tileSize * maxScreenRow; // 576 px

        KeyHandler keyH = new KeyHandler();
        Thread gameThread;

        // ENTITY AND OBJECT
        public CollisionChecker cChecker = new CollisionChecker(this);
        public Player player = new Player(this, keyH);

        // WORLD SETTINGS
        public final int maxWorldCol = 50;
        public final int maxWorldRow = 50;
        public final int worldWidth = tileSize * maxWorldCol;
        public final int worldHeight = tileSize * maxWorldRow;

        // FPS
        int FPS = 60;

        TileManager tileM = new TileManager(this);
        public GamePanel() {
            this.setPreferredSize(new Dimension(screenWidth, screenHeight));
            this.setBackground(Color.BLACK);
            this.setDoubleBuffered(true);
            this.addKeyListener(keyH); // adaugam obiectul creat din KeyHandler pentru input
            this.setFocusable(true); // GamePanel se va putea concentra pe inputul de taste

        }

        public void startGameThread() {
            gameThread = new Thread(this); // parsing GamePanel to the Thread
            gameThread.start();
        }

//        @Override
//        public void run() {
//
//            double drawInterval = 1000000000/FPS; // 1 secunda in nanosecunde impartit la 60 pentru a "desena" pe ecran de 60 ori intr-o secunda
//            double nextDrawTime = System.nanoTime() + drawInterval;
//
//            while (gameThread != null){
//
//                // 1 UPDATE: Updatarea informatiei precum pozitia unui caracter
//                update();
//
//                // 2 DRAW: "Desenarea" pe ecran folosing informatia updatata
//                repaint(); // modul in care este chemata metoda paintComponent
//
//                try {
//                    double remainingTime = nextDrawTime - System.nanoTime(); // timpul ramas pana la urmatorul "Draw" in care thread-ul va "dormi"
//                    remainingTime = remainingTime / 1000000;
//
//                    if(remainingTime < 0) {
//                        remainingTime = 0;
//                    }
//
//                    Thread.sleep((long) remainingTime);
//
//                    //cand thread-ul isi reia activitatea
//                    nextDrawTime += drawInterval;
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
        @Override
        public void run(){
            double drawInterval = 1000000000/FPS;
            double delta = 0;
            long lastTime = System.nanoTime();
            long currentTime;
            long timer = 0;
            int drawCount = 0;

            while (gameThread != null){
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / drawInterval; // impartim timpul ramas la intervalul de afisare si il adaugam la delta
                timer += (currentTime - lastTime);
                lastTime = currentTime;

                if(delta > 1){
                    update();
                    repaint();
                    delta--;
                    drawCount++;
                }

                if(timer >= 1000000000) {
                    System.out.println("FPS: " + drawCount);
                    drawCount = 0;
                    timer = 0;
                }
            }
        }

        public void update(){
            player.update();
        }

        public void paintComponent(Graphics g){ // metoda incorporata in Java
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g; // schimbam obiectul Graphics in Graphics2D

            tileM.draw(g2);
            player.draw(g2);

            g2.dispose(); // eliberam resurse ale sistemului eliminand obiectul g2
        }
}
