
/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MovieRunnerSimilarRatings {    
    public void printAverageRatings() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 35;
        ArrayList<Rating> ratings = fr.getAverageRatings(min);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printAverageRatingsByYearAndGenre() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        int min = 8;
        Filter f1 = new YearAfterFilter(1990);
        Filter f2 = new GenreFilter("Drama");
        AllFilters ff = new AllFilters();
        ff.addFilter(f1);
        ff.addFilter(f2);
        ArrayList<Rating> ratings = fr.getAverageByFilter(min, ff);
        Collections.sort(ratings);
        System.out.println("The number of movies rated equals: " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " " + r.getValue());
        }
    }
    
    public void printSimilarRatings() {
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());        
        FourthRatings fr = new FourthRatings();
        String rID = "71";
        int num = 20;
        int min = 5;
        ArrayList<Rating> ratings = fr.getSimilarRatings(rID, num, min);
        System.out.println("The number of recommended movies equals: " +
                            ratings.size());
        for (int i = 0; i < ratings.size(); i++) {
            double r = ratings.get(i).getValue();
            String mID = ratings.get(i).getItem();
            if (i<15) {
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), 
	    		                     MovieDatabase.getTitle(ratings.get(i).getItem()));
	        }
		}
    }
    
    public void printSimilarByGenre() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        String rID = "964";
        int num = 20;
        int min = 5;
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fr.getSimilarByFilter(rID, num, min, f);
        System.out.println("The number of recommended movies equals: "
                            + ratings.size());
        for (int i = 0; i < ratings.size(); i++) {
            if (i<15) {
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), 
	    		                     MovieDatabase.getTitle(ratings.get(i).getItem()));
	        }
		}
    }
    
    public void printSimilarByDirector() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        String rID = "120";
        int num = 10;
        int min = 2;
        Filter f = new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fr.getSimilarByFilter(rID, num, min, f);
        System.out.println("The number of recommended movies equals: "
                            + ratings.size());
        for (int i = 0; i < ratings.size(); i++) {
            double r = ratings.get(i).getValue();
            String mID = ratings.get(i).getItem();
            if (i<15) {
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), 
	    		                     MovieDatabase.getTitle(ratings.get(i).getItem()));
	        }
		}
    }
    
        public void printSimilarByGenreAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        String rID = "168";
        int num = 10;
        int min = 3;
        Filter f = new GenreFilter("Drama");
        Filter ff = new MinutesFilter(80, 160);
        AllFilters af = new AllFilters();
        af.addFilter(f);
        af.addFilter(ff);
        ArrayList<Rating> ratings = fr.getSimilarByFilter(rID, num, min, af);
        System.out.println("The number of recommended movies equals: "
                            + ratings.size());
        for (int i = 0; i < ratings.size(); i++) {
            double r = ratings.get(i).getValue();
            String mID = ratings.get(i).getItem();
            if (i<15) {
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), 
	    		                     MovieDatabase.getTitle(ratings.get(i).getItem()));
	        }
		}
    }
    
    public void printSimilarByYearAndMinutes() {
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("The number of raters included equals: " + RaterDatabase.size());
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("The number of movies rated equals: " + MovieDatabase.size());
        String rID = "314";
        int num = 10;
        int min = 5;
        Filter f = new YearAfterFilter(1975);
        Filter ff = new MinutesFilter(70, 200);
        AllFilters af = new AllFilters();
        af.addFilter(f);
        af.addFilter(ff);
        ArrayList<Rating> ratings = fr.getSimilarByFilter(rID, num, min, af);
        System.out.println("The number of recommended movies equals: "
                            + ratings.size());
        for (int i = 0; i < ratings.size(); i++) {
            double r = ratings.get(i).getValue();
            String mID = ratings.get(i).getItem();
            if (i<15) {
	    		System.out.printf("%d %.2f %s\n", i, ratings.get(i).getValue(), 
	    		                     MovieDatabase.getTitle(ratings.get(i).getItem()));
	        }
		}
    }
}