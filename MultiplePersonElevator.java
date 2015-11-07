/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MultiplePersonElevator extends JFrame implements ActionListener {


    private static final long serialVersionUID = 5151288981155324788L;

    protected int capacity; 
    protected int timeMoveOneFloor;    
    protected int floors;  
    protected int doorDelta; 
    protected int currentFloor = 1; 
    @SuppressWarnings("deprecation")
    protected Time currentTime= new Time(8, 0, 0);  
    protected int startingFloor = 1;
    protected boolean verbose = false;
    protected Queue<PassengerRequest> servingQueue;

    private ElevatorPanel elevatorPanel;
    private PassangerPanel passangerPanel;
    private FloorPanel floorPanel;

    public int X_PIXELS;
    public int Y_PIXELS;
    private boolean running;
    private boolean up;

    public MultiplePersonElevator(int capacity, int timeMoveOneFloor, int floors, 
            int doorDelta, boolean verbose) {

        super("Elevator System");

        this.capacity = capacity;
        this.timeMoveOneFloor = timeMoveOneFloor;
        this.floors = floors;
        this.doorDelta = doorDelta;
        this.verbose = verbose;

        this.X_PIXELS = 800;
        this.Y_PIXELS = 600 - 600 % floors;
        this.elevatorPanel = new ElevatorPanel(this);
        this.passangerPanel = new PassangerPanel(this);
        this.floorPanel = new FloorPanel(this);

        
        this.running = false;
        this.up = true;

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Actions");
        JMenuItem menuItem1 = new JMenuItem("New Day");
        JMenuItem menuItem2 = new JMenuItem("Add Passanger");
        JMenuItem menuItem3 = new JMenuItem("Exit");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setJMenuBar(menuBar);

        add(elevatorPanel, BorderLayout.CENTER);
        add(passangerPanel, BorderLayout.WEST);
        add(floorPanel, BorderLayout.EAST);

        menuBar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.addSeparator();
        menu.add(menuItem3);

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);

        pack();
        setVisible(true);
    }


    public void initialize(Queue<PassengerRequest> requests) {

    }

    private void start() throws InterruptedException {
        if (running) return;
        running = true;

        int fps = floors / 2;
        int target_interval = 1000 / fps;
        int max_elapsed_cycles = 5;

        long startTime;
        long deltaTime;
        int elapsedCycles;
        int sleep;

        while (running) {

            startTime = System.currentTimeMillis();
            elapsedCycles = 0;

            move();
            repaint();

            deltaTime = System.currentTimeMillis() - startTime;
            sleep = (int)(target_interval - deltaTime);

            if (sleep > 0) {
                Thread.sleep(sleep);
            }

            while (elapsedCycles < max_elapsed_cycles && sleep < 0) {
                elapsedCycles++;
                sleep += target_interval;
                move();
            }
        }

        stop();
    }

    private void stop() {
        if (!running) return;
        running = false;
    }


    public ArrayList<PassengerReleased> move() {
        if (up) {
            if (currentFloor == floors) {
                up = false;
            } else {
                currentFloor++;
            }
        } else {
            if (currentFloor == 1) {
                up = true;
            } else {
                currentFloor--;
            }
        }
        return null;
    }



    public boolean continueOperate() {
        return true;
    }


    public ArrayList<PassengerReleased> operate() {
        ArrayList<PassengerReleased> released = new ArrayList<PassengerReleased>();
        while (this.continueOperate()) {
            ArrayList<PassengerReleased> moved = this.move();
            released.addAll(moved);
        }
        return released;
    }

    @Override
    public void actionPerformed (ActionEvent arg0) {
        String event = arg0.getActionCommand();
        switch (event) {
            case "New Day":
                break;
            case "Add Passanger":
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
        }
    }

    public static void main(String... args) throws InterruptedException {
        int capacity = 2000;
        int timeMoveOneFloor = 10;
        int floors = 15;
        int doorDelta = 5;
        boolean verbose = true;

        MultiplePersonElevator elevator = new MultiplePersonElevator(capacity, timeMoveOneFloor, floors, doorDelta, verbose);
        elevator.start();
    }
}
