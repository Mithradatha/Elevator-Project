/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ElevatorPanel extends JPanel {

    private static final long serialVersionUID = 6892588095905304070L;

    private int x;
    private int y;

    private Elevator elevator;

    public ElevatorPanel(Elevator elevator, int X_PIXELS, int Y_PIXELS) {
        this.elevator = elevator;
        this.x = X_PIXELS;
        this.y = Y_PIXELS;
        
        setPreferredSize(new Dimension(x/4, y));
        setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int currentLevel = elevator.currentFloor;
        int nFloors = elevator.floors;

        int width = getWidth();
        int height = (getHeight() / nFloors);
        int x_origin = 0;
        int y_origin = Math.abs(getHeight() - (currentLevel * height));

        g.setColor(Color.GRAY);
        g.fillRect(x_origin, y_origin, width, height);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(width/4, y_origin, width/2, height);

        g.setColor(Color.BLACK);
        g.drawLine(width/2, y_origin, width/2, y_origin + height + 1);

        Image img = new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\elevator_button3.png").getImage();
        g.drawImage(img, width/8, y_origin + height/2, width/16, height/4, this);
    }
}
