
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 35;
        ArrayList<Rating> ratings = tr.getAverageRatings(min);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByYear() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 10;
        Filter f = new YearAfterFilter(2010);
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, f);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 20;
        Filter f = new GenreFilter("Comedy");
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, f);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 5;
        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, f);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByDirector() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 4;
        Filter f = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, f);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByYearAndGenre() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 8;
        Filter f1 = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters ff = new AllFilters();
        ff.addFilter(f1);
        ff.addFilter(f2);
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, ff);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr = new ThirdRatings("ratings.csv");
        //ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("The number of raters included: " + tr.getRaterSize());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        //MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 3;
        Filter f1 = new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter f2 = new MinutesFilter(90, 180);
        AllFilters ff = new AllFilters();
        ff.addFilter(f1);
        ff.addFilter(f2);
        ArrayList<Rating> ratings = tr.getAverageByFilter(min, ff);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
}
