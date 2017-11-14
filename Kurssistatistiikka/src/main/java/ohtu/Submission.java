package ohtu;

import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private List<Integer> exercises;
    

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> excercises) {
        this.exercises = excercises;
    }
    
    
    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        String ret =  "Week: "+week + ", Completed excercises total: "+ exercises.size() + ", Time spent: "+hours + ", Completed Excercises: ";
        for (int i = 0; i < exercises.size(); i++) {
            if (exercises.get(i) != null) {
                ret = ret + " " + Integer.toString(exercises.get(i));
            }
        }
        return ret;
    }
    
}