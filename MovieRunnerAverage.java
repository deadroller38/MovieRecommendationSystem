import java.util.*;

/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {

    public void printAverageRatings() {
        SecondRatings input = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        System.out.println("Loaded " + input.getMovieSize() + " movies and " + input.getRaterSize() + " raters");
        int minimalRaters = 12;
        ArrayList<Rating> ratings = input.getAverageRatings(minimalRaters);
        Collections.sort(ratings);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + input.getTitle(r.getItem()));
        }
    }
    
    public void getAverageRatingOneMovie() {
        SecondRatings input = new SecondRatings("ratedmoviesfull.csv", "ratings.csv");
        String title = "Vacation";
        ArrayList<Rating> ratings = input.getAverageRatings(1);
        for (Rating r : ratings) {
            String id = r.getItem();
            if(input.getTitle(id).equals(title)) {
                System.out.println(title + " has average rating " + r.getValue());
                break;
            }
            
        }
        
    }
}
