
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {    
    private double getAverageByID(String id, int minimalRaters) {
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
    
    private double dotProduct(Rater me, Rater r) {
        double dotProduct = 0;
        ArrayList<String> myMovies = me.getItemsRated();
        for (String id : myMovies) {
            if (r.hasRating(id)) {
                double myRating = me.getRating(id);
                double rRating = r.getRating(id);
                myRating -= 5;
                rRating -= 5;
                dotProduct += myRating * rRating;
            }
        }
        return dotProduct;
    }
    
    public ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.equals(me)) {
                double dp = dotProduct(me, r);
                if (dp > 0) {
                    list.add(new Rating(r.getID(), dp));
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minRaters) {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> rec = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double wAVG = 0;
            double sum = 0;
            int count = 0;
            for (int i = 0; i < numSimilarRaters; i++) {
                Rating r = list.get(i);
                double weight = r.getValue();
                String raterID = r.getItem();
                Rater myRater = RaterDatabase.getRater(raterID);
                if (myRater.hasRating(movieID)) {
                    count++;
                    sum += weight * myRater.getRating(movieID);
                }
            }
            if (count >= minRaters) {
                wAVG = sum / count;
                rec.add(new Rating(movieID, wAVG));
            }
        }
        Collections.sort(rec, Collections.reverseOrder());
        return rec;
    }
    
    public ArrayList<Rating> getSimilarByFilter(String id, int num, int min, Filter f) {
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<Rating> rec = new ArrayList<Rating>();
        for (String mID : MovieDatabase.filterBy(f)) {
            double wAVG = 0;
            double sum = 0;
            int count = 0;
            for (int i = 0; i < num; i++) {
                Rating r = list.get(i);
                double weight = r.getValue();
                String rID = r.getItem();
                Rater myRater = RaterDatabase.getRater(rID);
                if (myRater.hasRating(mID)) {
                    count++;
                    sum += weight * myRater.getRating(mID);
                }
            }
            if (count >= min) {
                wAVG = sum / count;
                rec.add(new Rating(mID, wAVG));
            }
        }
        Collections.sort(rec, Collections.reverseOrder());
        return rec;
    }
}