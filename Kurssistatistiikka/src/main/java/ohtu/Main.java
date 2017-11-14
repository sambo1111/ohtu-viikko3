package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // vaihda oma opiskelijanumerosi seuraavaan, ÄLÄ kuitenkaan laita githubiin omaa opiskelijanumeroasi
        String studentNr = "011120775";
        if ( args.length>0) {
            studentNr = args[0];
        }
        
        // Submissions
        String url = "https://studies.cs.helsinki.fi/ohtustats/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();

        System.out.println("submission json:");
        System.out.println( bodyText );

        Gson mapper = new Gson();
        
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        // /Submissions
        
        // CourseInfo
        String url2 = "https://studies.cs.helsinki.fi/ohtustats/courseinfo";
        String bodyText2 = Request.Get(url2).execute().returnContent().asString();
        System.out.println("courseinfo json");
        System.out.println(bodyText2);
        
        System.out.println("******************************************************");
        System.out.println("");
        
        CourseInfo info = mapper.fromJson(bodyText2, CourseInfo.class);
        // /CourseInfo
        
        //Stats
        String url3 = "https://studies.cs.helsinki.fi/ohtustats/stats";
        String statsResponse = Request.Get(url3).execute().returnContent().asString();
        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(statsResponse).getAsJsonObject();
        
        TotalStats stats = new TotalStats(parsedData, mapper);
        
        String totalStats = stats.extractStats();
        
        // /Stats
        
        
        System.out.println(info+"\n");
        
        System.out.println("Student ID: "+studentNr+"\n");
        
        for (Submission submission : subs) {
            System.out.println(submission);
        }
        
        System.out.println(""); 
        System.out.println(totalStats);
    }
}