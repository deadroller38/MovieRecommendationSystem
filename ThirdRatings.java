
/**
 * Write a description of SecondRatings here.
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
        FirstRatings input = new FirstRatings();
        myRaters = input.loadRaters("data/"+ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averRatings = new ArrayList<Rating>();
        for (String id : movies) {
            if (getAverageByID(id, minimalRaters) != 0.0) {
                Rating curRating = new Rating (id, getAverageByID(id, minimalRaters));
                averRatings.add(curRating);
            }
        }
        return averRatings;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averRatings = new ArrayList<Rating>();
        for (String id : movies) {
            if (getAverageByID(id, minimalRaters) != 0.0 && filterCriteria.satisfies(id)) {
                Rating curRating = new Rating (id, getAverageByID(id, minimalRaters));
                averRatings.add(curRating);
            }
        }
        return averRatings;
    }    

}