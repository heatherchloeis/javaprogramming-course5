
/**
 * Write a description of ThirdRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*; 

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    
    public ThirdRatings(String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getRaterSize() {
        int raterSize = myRaters.size();
        return raterSize;
    }
    
    private double getAverageByID(String id, int minimalRaters) {
        double avg = 0;
        double sum = 0;
        int count = 0;
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                sum += r.getRating(id);
                count++;
            }
        }
        if (count >= minimalRaters) {
            avg = sum / count;
        } else {
            avg = 0.0;
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String s : movies) {
            double avg = getAverageByID(s, minimalRaters);
            if (avg != 0) {
                Rating r = new Rating(s, avg);
                myRatings.add(r);
            }
        }
        return myRatings;
    }
    
    public ArrayList<Rating> getAverageByFilter(int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String s : movies) {
            double avg = getAverageByID(s, minimalRaters);
            if (avg != 0) {
                Rating r = new Rating(s, avg);
                ratings.add(r);
            }
        }
        return ratings;
    }
}
