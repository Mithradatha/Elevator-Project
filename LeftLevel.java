/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LeftLevel extends JPanel {

    private static final long serialVersionUID = 4224400657620918518L;

    private int level;
    private int nPassangers;

    public LeftLevel(int level) {
        setBackground(Color.WHITE);
        this.level = level;
        Random rnd = new Random();
        this.nPassangers = rnd.nextInt(10);
        System.out.println(nPassangers);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth(), 0);

        int total_width = getWidth();
        int passanger_height = getHeight()/2;
        int passanger_width = 30;
        int current_point = total_width - passanger_width;

        Image img = new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\stick_figure.png").getImage();
        for (int i = 0; i < nPassangers; i++) {
            g.drawImage(img, current_point, getHeight()/2, passanger_width, passanger_height, this);
            current_point -= passanger_width;
        }
    }
}
