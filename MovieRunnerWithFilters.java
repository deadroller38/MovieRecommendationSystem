import java.util.*;
import java.io.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {

    public void printAverageRatings() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 35;
        ArrayList<Rating> ratings = input.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printAverageRatingsByYearAfter() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 20;
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }    
    
    public void printAverageRatingsByGenre() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 20;
        Filter f = new GenreFilter("Comedy");
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }      
    
    public void printAverageRatingsByMinutes() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 5;
        Filter f = new MinutesFilter(105, 135);
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }  
    
    public void printAverageRatingsByDirector() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 4;
        Filter f = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }      
    
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 8;
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1990));
        f.addFilter(new GenreFilter("Drama"));
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }  
    
    public void printAverageRatingsByMinutesAndDirector() {
        ThirdRatings input = new ThirdRatings("ratings.csv");
        System.out.println("Loaded " + input.getRaterSize() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        int minimalRaters = 3;
        AllFilters f = new AllFilters();
        f.addFilter(new MinutesFilter(90, 180));
        f.addFilter(new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack"));
        ArrayList<Rating> ratings = input.getAverageRatingsByFilter(minimalRaters, f);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem())+ " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }      
}
