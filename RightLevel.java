/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class RightLevel extends JPanel {

    private static final long serialVersionUID = 8726665299825285300L;
    private int level;

    public RightLevel(int level) {
        setBackground(Color.WHITE);
        this.level = level;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, 0, getWidth(), 0);

        Font levelFont = new Font("Tahoma", Font.BOLD, 17);
        g.setFont(levelFont);
        String sLevel = new String("Floor: "+level);
        String largestLevel = new String("Floor: 100");
        int fontWidth = g.getFontMetrics(levelFont).stringWidth(largestLevel);
        g.drawString(sLevel, getWidth()/3 - fontWidth, getHeight()/2);
        //g.drawRect(getWidth()/3 - fontWidth, 0, getWidth()/3 + fontWidth, getHeight()/2 + fontWidth);
    }
}
