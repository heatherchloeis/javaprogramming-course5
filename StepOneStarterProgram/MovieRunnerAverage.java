
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerAverage {
    public void printAverageRatings() {
        SecondRatings second = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        //SecondRatings second = new SecondRatings("ratedmovies_short.csv", "ratings_short.csv");
        int movieSize = second.getMovieSize();
        int raterSize = second.getRaterSize();
        System.out.println("The number of movies rated equals: " + movieSize);
        System.out.println("The number of movies rated equals: " + raterSize);
        int min = 11;
        ArrayList<Rating> myRatings = second.getAverageRatings(min);
        Collections.sort(myRatings);
        for (Rating r : myRatings) {
            System.out.println(second.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void getAverageRating() {
        SecondRatings sr = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String title = "Vacation";
        String id = sr.getID(title);
        ArrayList<Rating> myR = sr.getAverageRatings(0);
        for (Rating r : myR) {
            if (r.getItem().equals(id)) {
                System.out.println("The average rating for " + title + " is " 
                                    + r.getValue());
            }
        }
    }
}
