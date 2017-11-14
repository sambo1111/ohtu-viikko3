/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;

/**
 *
 * @author hasasami
 */
public class TotalStats {
    private JsonObject parsedData;
    private Gson mapper;
    private ArrayList<WeekStats> weekStats = new ArrayList();
    
    public TotalStats (JsonObject data, Gson mapper) {
        parsedData = data;
        this.mapper = mapper;
    }
    
    private void parseStats () {
        
        for (String key : parsedData.keySet()) {
            JsonElement weekStatsJson = parsedData.get(key);
            weekStats.add(mapper.fromJson(weekStatsJson, WeekStats.class));
        }
    }
    
    public String extractStats () {
        parseStats();
        int submissionsTotal = 0;
        int exercisesTotal = 0;
        
        for (WeekStats weekStat : weekStats) {
            exercisesTotal += weekStat.getExercises_total();
            submissionsTotal += weekStat.getStudents();
        }
        
        return "Submissions Total: " + submissionsTotal + ", Exercises Submitted Total: " + exercisesTotal;
    }
}
