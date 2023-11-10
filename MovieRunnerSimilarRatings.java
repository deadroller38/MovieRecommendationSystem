import java.util.*;

/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {

    public void printAverageRatings() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
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
    
    public void printAverageRatingsByYearAfterAndGenre() {
       FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
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
    
    public void printSimilarRatings() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        String raterID = "71";
        int topRaters = 20;
        int minimalRaters = 5;
        ArrayList<Rating> ratings = input.getSimilarRatings(raterID, topRaters, minimalRaters);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenre() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        String raterID = "964";
        int topRaters = 20;
        int minimalRaters = 5;
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = input.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, f);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByDirector() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        String raterID = "120";
        int topRaters = 10;
        int minimalRaters = 2;
        Filter f = new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = input.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, f);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getDirector(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        String raterID = "168";
        int topRaters = 10;
        int minimalRaters = 3;
        AllFilters f = new AllFilters();
        f.addFilter(new GenreFilter("Drama"));
        f.addFilter(new MinutesFilter(80,160));
        ArrayList<Rating> ratings = input.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, f);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()) + "\r\n\t" + MovieDatabase.getGenres(r.getItem()));
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings input = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        System.out.println("Loaded " + RaterDatabase.size() + " raters");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("Loaded " + MovieDatabase.size() + " movies");
        String raterID = "314";
        int topRaters = 10;
        int minimalRaters = 5;
        AllFilters f = new AllFilters();
        f.addFilter(new YearAfterFilter(1975));
        f.addFilter(new MinutesFilter(70,200));
        ArrayList<Rating> ratings = input.getSimilarRatingsByFilter(raterID, topRaters, minimalRaters, f);
        System.out.println("Total number of movies with at least " + minimalRaters + " ratings is " + ratings.size());
        for (Rating r : ratings) {
            System.out.println(r.getValue() + " " + MovieDatabase.getYear(r.getItem()) + " Time: " + MovieDatabase.getMinutes(r.getItem()) + " " + MovieDatabase.getTitle(r.getItem()));
        }
    }
}
