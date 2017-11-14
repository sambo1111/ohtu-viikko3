/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author hasasami
 */
public class CourseInfo {
    
    private String name;
    private String term;
    private List<Integer> exercises;
    List<Submission> subs;

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        String ret = name + ", " + term + "\n\n" + "Max exercises: \n";
        for (int i = 0; i < exercises.size(); i++) {
            if (i == exercises.size() - 1) {
                ret = ret + "Week " + i + ": " + exercises.get(i);
            } else {
                ret = ret + "Week " + i + ": " + exercises.get(i) + "\n";
            }
        }
        return ret;
    }
}
