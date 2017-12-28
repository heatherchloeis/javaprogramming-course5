
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        //this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingsfile);
    }
    
    public int getMovieSize() {
        int movieSize = myMovies.size();
        return movieSize;
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
        if (count > minimalRaters) {
            avg = sum / count;
        } else {
            avg = 0.0;
        }
        return avg;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> myRatings = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            double avg = getAverageByID(m.getID(), minimalRaters);
            if (avg != 0) {
                Rating r = new Rating(m.getID(), avg);
                myRatings.add(r);
            }
        }
        return myRatings;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "Movie title not found.";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE.";
    }
}
