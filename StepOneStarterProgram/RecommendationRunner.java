
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class RecommendationRunner implements Recommender {     
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> rec = new ArrayList<String>();                
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        int min = 10;
        Filter f = new YearAfterFilter(2010);
        ArrayList<Rating> ratings = fr.getAverageByFilter(min, f);
        for (int i = 0; i < ratings.size(); i++) {
            String id = ratings.get(i).getItem();
            if (i <= 25) {
                if (!rec.contains(id)) {
                    rec.add(id);
                }
            }
        }
        return rec;
    }
    
    public void printRecommendationsFor(String webRaterID) {           
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatings fr = new FourthRatings();
        int num = 15;
        int min = 5;
        ArrayList<Rating> recommendations = fr.getSimilarRatings(webRaterID, num, min);
        System.out.println("<table>");
        System.out.println("<tr><td> Title </td><td> Year </td><td> Director </td><td> Genre </td></tr>");
        for (int i = 0; i < recommendations.size(); i++) {
            System.out.print("<tr>");
            System.out.print("<td>" + MovieDatabase.getTitle(recommendations.get(i).getItem()) + "</td>");
            System.out.print("<td>" + MovieDatabase.getYear(recommendations.get(i).getItem()) + "</td>");
            System.out.print("<td>" + MovieDatabase.getDirector(recommendations.get(i).getItem()) + "</td>");
            System.out.print("<td>" + MovieDatabase.getGenres(recommendations.get(i).getItem()) + "</td>");
            System.out.println("</tr>");
        }
        System.out.println("<table>");
    }
}