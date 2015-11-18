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
    
    private int[] elevatorOffPassengers;
    
    public FloorPanel (Elevator elevator, int X_PIXELS, int Y_PIXELS, int[] elevatorOffPassengers) {
        this.elevator = elevator;
        this.x = X_PIXELS;
        this.y = Y_PIXELS;
        this.elevatorOffPassengers = elevatorOffPassengers;

        setPreferredSize(new Dimension((x - (x / 4)) / 2, y));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int nFloors = elevator.floors;
        
        int width = getWidth();
        int height = (getHeight() / nFloors); 

        int offset = height / 3;
        //int font_size = (nFloors < 5) ? height / 8: height / 4;
        
        /*if (nFloors < 3) font_size = height / 16;
        if (nFloors > 3 && nFloors < 5) font_size = height / 8;
        if (nFloors > 5 && nFloors < 12) font_size = height / 4;
        if (nFloors > 12 && nFloors < 20) font_size = height / 2;
        if (nFloors > 20) font_size = height;*/
        
        int font_size = (nFloors < 15) ? (nFloors / 2) * (height / 10) : height / 2;
        
        Font level_font = new Font("Microsoft Sans Serif", Font.BOLD, font_size);
        String standard = new String("+ 1000");
        
        //int font_width = g.stringWidth(standard);
        int j = nFloors;
        
        g.setColor(Color.BLACK);
        g.setFont(level_font);

        int x_origin = getWidth() - g.getFontMetrics(level_font).stringWidth(standard);
        
        for (int i = 1; i <= nFloors; i++) {
            g.drawLine(0, i * height, width, i * height);
            g.drawString("Floor: " + j, font_size, i * height - offset);
            
            int y_origin = getHeight() - ((i-1) * height);
            if (elevatorOffPassengers[i] > 0)
            g.drawString("+ " +elevatorOffPassengers[i], x_origin, y_origin - offset);

            j--;
        }
    }
}
