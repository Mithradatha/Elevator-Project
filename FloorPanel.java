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
    
    private MultiplePersonElevator elevator;
    
    public FloorPanel (MultiplePersonElevator elevator) {
        this.elevator = elevator;
        this.x = elevator.X_PIXELS;
        this.y = elevator.Y_PIXELS;
        //int dimension_offset = y % elevator.floors;
        setPreferredSize(new Dimension((x - (x / 4)) / 2, y));
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        int nFloors = elevator.floors;
        //int odd = (getHeight() % nFloors == 0) ? 0 : 1;
        
        int width = getWidth();
        int height = (getHeight() / nFloors); 
                //+ odd;
        int offset = height / 2;
        
        Font level_font = new Font("Times New Roman", Font.BOLD, 17);
        String standard = new String("Floor: 1000");
        
        int font_width = g.getFontMetrics(level_font).stringWidth(standard);
        int j = nFloors;
        
        g.setColor(Color.BLACK);
        g.setFont(level_font);
        
        for (int i = 1; i <= nFloors; i++) {
            g.drawLine(0, i * height, width, i * height);
            g.drawString("Floor: " + j, width/3 - font_width, i * height - offset);
            j--;
        }
    }
}
