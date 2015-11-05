/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class GuiControl extends JFrame implements ActionListener{

    private static final long serialVersionUID = -771450482608656155L;

    private int currentLevel;
    private int floors;

    public GuiControl(int floors, int currentLevel) {
        setTitle("Elevator");
        this.currentLevel = currentLevel;
        this.floors = floors;
        setBackground(Color.WHITE);
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("Actions");
        menuBar.add(menu);

        JMenuItem menuItem1 = new JMenuItem("Add Passanger");
        menu.add(menuItem1);
        menuItem1.addActionListener(this);
        JMenuItem menuItem2 = new JMenuItem("Restart");
        menu.add(menuItem2);
        menuItem2.addActionListener(this);
        menu.addSeparator();
        JMenuItem menuItem3 = new JMenuItem("Exit");
        menu.add(menuItem3);
        menuItem3.addActionListener(this);


        add(new ElevatorShaftPanel(floors, currentLevel), BorderLayout.CENTER);
        add(new LevelPanel(floors, true), BorderLayout.WEST);
        add(new LevelPanel(floors, false), BorderLayout.EAST);
        setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent e) {
        String event = e.getActionCommand();
        switch(event) {
            case "Exit": 
                System.exit(0);
                break;
            default:
        }
    }


}
