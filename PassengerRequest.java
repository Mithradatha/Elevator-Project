/**
 * Author: Samuel Kellar, skellar2014@my.fit.edu
 * Course: CSE 2010, Section 02, Fall 2015
 * Project: (project_ID)
 */
package project;

import java.sql.Time;

public class PassengerRequest /*implements Comparable<PassengerRequest>*/{
    
    public Time getTimePressedButton() {
        return time_pressed_button;
    }
    public void setTimePressedButton(Time time_pressed_button) {
        this.time_pressed_button = time_pressed_button;
    }
    public int getFloorFrom() {
        return floor_from;
    }
    public void setFloorFrom(int floor_from) {
        this.floor_from = floor_from;
    }
    public int getFloorTo() {
        return floor_to;
    }
    public void setFloorTo(int floor_to) {
        this.floor_to = floor_to;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    private Time time_pressed_button;  // time when the button was pressed
    private int floor_from; // the floor from which the elevator was called
    private int floor_to; // the floor where the passenger is headed to
    private int weight; // weight, in pounds
    
    public PassengerRequest(Time time_pressed_button, int floor_from, int floor_to, int weight) {

        this.time_pressed_button = time_pressed_button;
        this.floor_from = floor_from;
        this.floor_to = floor_to;
        this.weight = weight;
    }
    
   /* @Override
    public int compareTo (PassengerRequest o) {
        if (this.floor_from < o.floor_from) {
            return -1;
        } else if (this.floor_from > o.floor_from) {
            return 1;
        } else {
            return 0;
        }
    }
    */
    
}

