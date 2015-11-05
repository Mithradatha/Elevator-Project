/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ElevatorLevel extends JPanel{

    private static final long serialVersionUID = 8095056250500418491L;

    private int level;
    private boolean isActive;

    public ElevatorLevel(int level, boolean isActive) {
        this.level = level;
        this.isActive = isActive;
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (isActive) {
            g.setColor(Color.GRAY);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.DARK_GRAY);
            g.fillRect(getWidth()/4, 0, getWidth()/2, getHeight());
            g.setColor(Color.BLACK);
            g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
            Image img = new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\elevator_button3.png").getImage();
            g.drawImage(img, getWidth()/4 - getWidth()/8, getHeight()/2, getWidth()/16, getHeight()/4, this);
        }
    }
}
