/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class FloorPanel extends JPanel {

    private static final long serialVersionUID = -8369799161094261074L;
    
    private int x;
    private int y;
    
    private Elevator elevator;
    
    public FloorPanel (Elevator elevator, int X_PIXELS, int Y_PIXELS) {
        this.elevator = elevator;
        this.x = X_PIXELS;
        this.y = Y_PIXELS;

        setPreferredSize(new Dimension((x - (x / 4)) / 2, y));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int nFloors = elevator.floors;
        
        int width = getWidth();
        int height = (getHeight() / nFloors); 

        int offset = height / 2;
        int font_size = (nFloors > 4) ? height / 2 : height / 4;
        
        Font level_font = new Font("Times New Roman", Font.BOLD, font_size);
        //String standard = new String("Floor: 100");
        
        //int font_width = g.stringWidth(standard);
        int j = nFloors;
        
        g.setColor(Color.BLACK);
        g.setFont(level_font);
        
        for (int i = 1; i <= nFloors; i++) {
            g.drawLine(0, i * height, width, i * height);
            g.drawString("Floor: " + j, 0, i * height - offset);
            j--;
        }
    }
}
