/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class LevelPanel extends JPanel {

    private static final long serialVersionUID = 2479385964705321871L;
    private JPanel[] levels;
    private boolean isPassanger;
    private int floors;

    public LevelPanel(int floors, boolean isLeft) {
        this.floors = floors;
        setLayout(new GridLayout(floors,1));
        setPreferredSize(new Dimension(300, 600));
        setBackground(Color.WHITE);
        this.levels = new JPanel[floors];
        this.isPassanger = isLeft;
        
        int j = 0;
        for (int i = floors; i > 0; i--) {
            JPanel newLevel = (isPassanger) ? new LeftLevel(i) : new RightLevel(i);
            add(newLevel);
            levels[j] = newLevel;
            j++;
        }
    }
}
