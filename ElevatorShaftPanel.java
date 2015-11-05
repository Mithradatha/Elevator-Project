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

public class ElevatorShaftPanel extends JPanel{

    private static final long serialVersionUID = 6892588095905304070L;
    
    private int floors;
    private int currentLevel;
    private JPanel[] levels;
    
    public ElevatorShaftPanel(int floors, int currentLevel) {
        setPreferredSize(new Dimension(200, 600));
        this.floors = floors;
        setLayout(new GridLayout(floors,1));
        setBackground(Color.BLACK);
        this.levels = new JPanel[floors];
        this.currentLevel = currentLevel;
        
        int j = 0;
        boolean isActive = false;
        for (int i = floors; i > 0; i--) {
            if (i == currentLevel) {
                isActive = true;
            }
            ElevatorLevel newElevatorLevel = new ElevatorLevel(i, isActive);
            if (isActive) isActive = false;
            levels[j] = newElevatorLevel;
            add(newElevatorLevel);
            j++;
        }
    }
}
