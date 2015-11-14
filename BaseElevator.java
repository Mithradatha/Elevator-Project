/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class BaseElevator extends Elevator implements ActionListener {

    private JFrame frame;
    private ElevatorPanel elevatorPanel;
    private PassengerPanel passangerPanel;
    private FloorPanel floorPanel;

    public int X_PIXELS;
    public int Y_PIXELS;

    protected int nPassengers;
    private boolean running;
    private boolean up;
    protected boolean delay;

    private long systemTime;
    private long afterHours;
    
    private int speed;
    
    protected ArrayList<PassengerRequest>[] floorPassengers;
    protected ArrayList<PassengerRequest> elevatorPassengers;

    private int weight;

    /**
     * capacity;
     * timeMoveOneFloor;
     * floors;
     * doorDelta;
     * currentFloor = 1;
     * currentTime= new Time(8, 0, 0);
     * startingFloor = 1;
     * verbose = false;
     * Queue<PassengerRequest> servingQueue;
     */


    @SuppressWarnings("unchecked")
    public BaseElevator (final int capacity, final int timeMoveOneFloor,
            final int floors, final int doorDelta, final boolean verbose) {
        super(capacity, timeMoveOneFloor, floors, doorDelta, verbose);

        this.frame = new JFrame("Base Elevator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setResizable(false);
        frame.setIconImage(new ImageIcon("C:\\Users\\Sam\\cse1002\\Eclipse\\ElevatorProject\\src\\project\\elevator_icon.gif").getImage());
        frame.setLayout(new BorderLayout());

        this.X_PIXELS = 800;
        this.Y_PIXELS = 600 - 600 % floors;
        this.running = false;
        this.floorPassengers = new ArrayList[floors+1];
        this.elevatorPassengers = new ArrayList<PassengerRequest>();
        this.systemTime = System.currentTimeMillis();
        this.afterHours = 13 * 3600000; // 1:00 pm
        this.nPassengers = 0;
        this.weight = 0;
        this.up = true;
        this.delay = false;
        this.speed = 60;

        for (int i = 1; i < floors+1; i++) {
            floorPassengers[i] = new ArrayList<PassengerRequest>();
        }

        this.elevatorPanel = new ElevatorPanel(this, X_PIXELS, Y_PIXELS);
        this.passangerPanel = new PassengerPanel(this, X_PIXELS, Y_PIXELS, floorPassengers);
        this.floorPanel = new FloorPanel(this, X_PIXELS, Y_PIXELS);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Actions");
        JMenuItem menuItem1 = new JMenuItem("New Day");
        JMenuItem menuItem2 = new JMenuItem("Statistics");
        JMenuItem menuItem3 = new JMenuItem("Exit");

        frame.setJMenuBar(menuBar);
        frame.add(elevatorPanel, BorderLayout.CENTER);
        frame.add(passangerPanel, BorderLayout.WEST);
        frame.add(floorPanel, BorderLayout.EAST);

        menuBar.add(menu);
        menu.add(menuItem1);
        menu.add(menuItem2);
        menu.addSeparator();
        menu.add(menuItem3);

        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);

        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void initialize (final Queue<PassengerRequest> requests) {
    }
    
    public void setSimulationSpeed(int speed_X) {
        this.speed *= speed_X;
    }

    public void setHours (Time start, Time end) {
        this.currentTime = start;
        this.afterHours = end.getTime();
    }

    public void addFloorPassenger () {
        Random rng = new Random();
        int floor_from = rng.nextInt(floors) + 1;
        int floor_to = rng.nextInt(floors) + 1;
        while (floor_to == floor_from) {
            floor_to = rng.nextInt(floors) + 1;
        }
        int new_weight = 250;//rng.nextInt(150) + 100;
        PassengerRequest request = new PassengerRequest(new Time(currentTime.getTime() + (System.currentTimeMillis() - systemTime)), floor_from, floor_to, new_weight);

        floorPassengers[request.getFloorFrom()].add(request);
        nPassengers++;
    }

    @Override
    public ArrayList<PassengerReleased> move () {
        ArrayList<PassengerReleased> releasedPassengers = new ArrayList<PassengerReleased>();
        if (delay) return releasedPassengers;
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

        

        for (PassengerRequest releaseRequest : elevatorPassengers) {
            if (releaseRequest.getFloorTo() == currentFloor) {
                weight -= releaseRequest.getWeight();
                Time releaseTime = new Time(currentTime.getTime() + (System.currentTimeMillis() - systemTime));
                System.out.println(releaseRequest.getTimePressedButton() + " --- " + releaseTime);
                PassengerReleased releasedPassenger = new PassengerReleased(releaseRequest, releaseTime);
                releasedPassengers.add(releasedPassenger);
                nPassengers--;
                delay = true;
            }
        }

        for (PassengerReleased released : releasedPassengers) {
            if (elevatorPassengers.contains(released.getPassengerRequest())) {
                elevatorPassengers.remove(released.getPassengerRequest());
            }
        }

        int max = ((int)(0.8 * capacity) + 1);
        while (weight < max && !floorPassengers[currentFloor].isEmpty()) {
            PassengerRequest elevatorRequest = floorPassengers[currentFloor].remove(0);
            elevatorPassengers.add(elevatorRequest);
            weight += elevatorRequest.getWeight();
            delay = true;
        }
        


        return releasedPassengers;
    }

    @Override
    public boolean continueOperate () {
        if (nPassengers == 0) {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<PassengerReleased> operate () {
        if (running) return null;
        running = true;

        final ArrayList<PassengerReleased> released = new ArrayList<PassengerReleased>();

        int target_interval = (timeMoveOneFloor * 1000) / speed ;
        int max_elapsed_cycles = 5;
        int next_passenger = 0;
        int door_timer = 0;

        long elevatorTime = currentTime.getTime();
        long startTime;
        long deltaTime;
        int elapsedCycles;
        int sleep;

        System.out.println(elevatorTime);
        System.out.println(afterHours);

        while (running) {

            startTime = System.currentTimeMillis();
            elapsedCycles = 0;

            next_passenger++;
            if (next_passenger > floors / 2) {   
                addFloorPassenger();
                next_passenger = 0;
            }

            ArrayList<PassengerReleased> moved = this.move();
            if (delay) door_timer ++;
            if (door_timer > doorDelta) {
                door_timer = 0;
                delay = false;
            }
            frame.repaint();

            deltaTime = System.currentTimeMillis() - startTime;
            sleep = (int)(target_interval - deltaTime);

            if (sleep > 0) {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            while (elapsedCycles < max_elapsed_cycles && sleep < 0) {
                elapsedCycles++;
                sleep += target_interval;
                moved.addAll(this.move());
            }
            if (!moved.isEmpty())
            released.addAll(moved);
            elevatorTime += (System.currentTimeMillis() - systemTime);
            if (elevatorTime >= afterHours) stopOperation();
        }

        getStats(released);
        return released;
    }

    public void stopOperation () {
        if (!running) return;
        running = false;
    }

    public void getStats(ArrayList<PassengerReleased> stats) {
        //frame.setVisible(false);
        
        JFrame jf = new JFrame("Time Analysis");
        jf.setSize(X_PIXELS, Y_PIXELS);
        
        jf.setLayout(new FlowLayout());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (PassengerReleased time : stats) {
            jf.add(new JLabel(""+((time.getTimeArrived().getTime() - time.getPassengerRequest().getTimePressedButton().getTime())) / 1000));
        }
        //jf.setMinimumSize(new Dimension(X_PIXELS, Y_PIXELS));
        //jf.setMaximumSize(new Dimension(X_PIXELS, Y_PIXELS));
        //jf.pack();
        jf.setIconImage(frame.getIconImage());
        frame.dispose();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }

    @Override
    public void actionPerformed (ActionEvent arg0) {
        String event = arg0.getActionCommand();
        switch (event) {
            case "New Day":
                break;
            case "Statistics":
                running = false;
                break;
            case "Exit":
                System.exit(0);
                break;
            default:
        }
    }

    @SuppressWarnings("deprecation")
    public static void main(String... args) throws InterruptedException {
        int capacity = 2000;
        int timeMoveOneFloor = 20;
        int floors = 25;
        int doorDelta = 5;
        boolean verbose = false;

        BaseElevator elevator = new BaseElevator(capacity, timeMoveOneFloor, floors, doorDelta, verbose);
        Queue<PassengerRequest> requests = new PriorityBlockingQueue<PassengerRequest>();
        elevator.initialize(requests);
        elevator.setSimulationSpeed(5);
        elevator.setHours(elevator.currentTime, new Time(10,00,00));
        System.out.println(requests);
        elevator.operate();
    }
}
