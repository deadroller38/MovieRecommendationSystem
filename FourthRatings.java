
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class FourthRatings {
         
    
    
    private double getAverageByID (String id, int minimalRaters) {
        int ratingCount = 0;
        double ratingSum = 0;
        for (Rater r : RaterDatabase.getRaters()) {
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

    private double dotProduct(Rater me, Rater r) {
        ArrayList<String> myMovies = me.getItemsRated();
        double dotProduct = 0;
        for (String m : myMovies) {
            if (r.hasRating(m)) {
                double myR = me.getRating(m)-5;
                double rR = r.getRating(m) - 5;
                dotProduct = dotProduct + myR*rR;
            }
        }
        return dotProduct;
    }
    
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> similarities = new ArrayList<Rating>();
        for (Rater r : RaterDatabase.getRaters()) {
            if (!r.getID().equals(id)) {
                if (dotProduct (RaterDatabase.getRater(id), r) >0) {
                    Rating curDotProduct = new Rating (r.getID(), dotProduct (RaterDatabase.getRater(id), r));
                    similarities.add(curDotProduct);
                }
            }
        }
        Collections.sort(similarities);
        Collections.reverse(similarities);
        return similarities;
    }
    
    public ArrayList<Rating> getSimilarRatings (String id, int numSimilarRaters, int minimalRaters) {
        return getSimilarRatingsByFilter(id, numSimilarRaters, minimalRaters, new TrueFilter());
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter (String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> similarities = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        ArrayList<Rating> averRatings = new ArrayList<Rating>();
        int maxSimilarRaters = numSimilarRaters;
        if (maxSimilarRaters > similarities.size()) {
            maxSimilarRaters = similarities.size();
        }
        for (String m : movies) {
            if (filterCriteria.satisfies(m)) {
                int count = 0;
                double wRating = 0;
                for (int i = 0; i<maxSimilarRaters; i++) {
                    Rater bestRater = RaterDatabase.getRater(similarities.get(i).getItem());
                    if (bestRater.hasRating(m)) {
                        count +=1;
                        double weight = similarities.get(i).getValue();
                        wRating = wRating + bestRater.getRating(m)*weight;
                    }
                }
                if (count >= minimalRaters) {
                    averRatings.add(new Rating(m, wRating/count));
                }
                }
            }
        Collections.sort(averRatings);
        Collections.reverse(averRatings);
        return averRatings;
    }    
}