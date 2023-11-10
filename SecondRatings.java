
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
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings input = new FirstRatings();
        myMovies = input.loadMovies("data/"+moviefile);
        myRaters = input.loadRaters("data/"+ratingsfile);
    }    
    
    public int getMovieSize() {
        return myMovies.size();
    }
    
    public int getRaterSize() {
        return myRaters.size();
    }
    
    private double getAverageByID (String id, int minimalRaters) {
        int ratingCount = 0;
        double ratingSum = 0;
        for (Rater r : myRaters) {
            if (r.hasRating(id)) {
                ratingSum = ratingSum + r.getRating(id);
                ratingCount +=1;
            }
        }
        if (ratingCount >= minimalRaters) {
            return ratingSum / ratingCount;
        }
        return 0.0;
    }
    
    public ArrayList<Rating> getAverageRatings (int minimalRaters) {
        ArrayList<Rating> averRatings = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            if (getAverageByID(m.getID(), minimalRaters) != 0.0) {
                Rating curRating = new Rating (m.getID(), getAverageByID(m.getID(), minimalRaters));
                averRatings.add(curRating);
            }
        }
        return averRatings;
    }
    
    public String getTitle(String id) {
        for (Movie m : myMovies) {
            if (m.getID().equals(id)) {
                return m.getTitle();
            }
        }
        return "ID was not found";
    }
    
    public String getID(String title) {
        for (Movie m : myMovies) {
            if (m.getTitle().equals(title)) {
                return m.getID();
            }
        }
        return "NO SUCH TITLE";
    }
}