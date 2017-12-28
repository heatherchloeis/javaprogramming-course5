
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovies(String filename) {
        ArrayList<Movie> list = new ArrayList<Movie>();
        String file = filename;
        FileResource fr = new FileResource(file);
        for (CSVRecord record : fr.getCSVParser()) {
            Movie curr = new Movie(record.get("id"), 
                                    record.get("title"),
                                    record.get("year"),
                                    record.get("genre"),
                                    record.get("director"),
                                    record.get("country"),
                                    record.get("poster"),
                                    Integer.parseInt(record.get("minutes")));
            list.add(curr);
        }
        return list;
    }
    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> list = new ArrayList<Rater>();
        String file = "data/" + filename;
        FileResource fr = new FileResource(file);
        int idx = 0;
        for (CSVRecord record : fr.getCSVParser()) {
            if (idx == 0) {
                Rater r = new EfficientRater(record.get("rater_id"));
                r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                list.add(idx, r);
                idx++;
            } else if (list.get(idx - 1).getID().equals(record.get("rater_id"))) {
                list.get(idx - 1).addRating(record.get("movie_id"), 
                                            Double.parseDouble(record.get("rating")));
            } else {
                Rater r = new EfficientRater(record.get("rater_id"));
                r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                list.add(idx, r);
                idx++;
            }
        }
        return list;
    }
    public void testLoadMovies() {
        String source = "ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(source);
        System.out.println("The number of movies loaded: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    public void testLoadRaters() {
        String source = "ratings.csv";
        ArrayList<Rater> list = loadRaters(source);
        System.out.println("The number of movies loaded: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        HashSet<String> ids = new HashSet<String>();
        ArrayList<Integer> numRatingsList = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            String raterID = list.get(i).getID();
            if (ids.add(raterID)) {
                numRatingsList.add(list.get(i).numRatings());
            }
        }
        System.out.println("There are " + ids.size() + " raters in this file.");
        int rater = 193;
        int ratings = numRatingsList.get(rater - 1);
        System.out.println("This rater rated " + ratings + " movies.");
        int max = 0;
        for (int i = 0; i < ids.size(); i++) {
            if (list.get(i).numRatings() > max) {
                max = list.get(i).numRatings();
            }
        }
        System.out.println("The max number of ratings by one rater equals: " 
                            + max);
        ArrayList<Integer> maxRatings = new ArrayList<Integer>();
        for (int i = 0; i < ids.size(); i++) {
            if (list.get(i).numRatings() == max) {
                maxRatings.add(i);
            }
        }
        System.out.println("The number of raters who have rated the max number of movies equals ");
        for (int i = 0; i < maxRatings.size(); i++) {
            System.out.println("Rater: " + list.get(maxRatings.get(i)).getID());
        }
        String testMovie = "1798709";
        HashSet<String> movies = new HashSet<String>();
        int counter = 0;
        for (int i = 0; i < list.size(); i++) {
            ArrayList<String> ratedMovies = list.get(i).getItemsRated();
            for (int j = 0; j < ratedMovies.size(); j++) {
                String ratedMovie = ratedMovies.get(j);
                movies.add(ratedMovie);
                if (ratedMovie.equals(testMovie)) {
                    counter++;
                }
            }
        }
        System.out.println("The movie " + testMovie + " was rated " + counter + " times.");
        System.out.println(movies.size() + " movies were rated in total.");
    }
    public void filterMovie() {
        String source = "ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(source);
        System.out.println("The number of movies loaded: " + list.size());
        int count = 0;
        /*for (int i = 0; i < list.size(); i++) {
            Movie curr = list.get(i);
            String genre = curr.getGenres();
            if (genre.indexOf("Comedy") != -1) {
                count++;
            }
        }  */
        for (int i = 0; i < list.size(); i++) {
            Movie curr = list.get(i);
            int time = curr.getMinutes();
            if (time > 150) {
                System.out.println(curr);
                count++;
            }
        }        
        System.out.println(count);
    }
    public void findMaxDirectors() {
        String source = "ratedmoviesfull.csv";
        ArrayList<Movie> list = loadMovies(source);
        System.out.println("The number of movies loaded: " + list.size());
        ArrayList<String> directors = new ArrayList<String>();
        ArrayList<Integer> count = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++) {
            String currDirector = list.get(i).getDirector();
            int idx = directors.indexOf(currDirector);
            if (idx == -1) {
                directors.add(currDirector);
                count.add(1);
            } else {
                count.set(idx, count.get(idx) + 1);
            }
        }
        int max = 0;
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i) > max) {
                max = count.get(i);
            }
        }
        System.out.println("The max number of movies directed by one director equals: " 
                            + max);
        ArrayList<Integer> maxDirected = new ArrayList<Integer>();
        for (int i = 0; i < count.size(); i++) {
            if (count.get(i) == max) {
                maxDirected.add(i);
            }
        }
        System.out.println("Directors that directed the max number of movies are:");
        for (int i = 0; i < maxDirected.size(); i++) {
            String dir = directors.get(maxDirected.get(i));
            System.out.println(dir);
        }
    }
}
