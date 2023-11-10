
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
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord r : parser) {
            String id = r.get("id");
            String title = r.get("title");
            String year = r.get("year");
            String genres = r.get("genre");
            String director = r.get("director");
            String country = r.get("country");
            String poster = r.get("poster");
            int minutes = Integer.parseInt(r.get("minutes"));
            Movie m = new Movie(id, title, year, genres, director, country, poster, minutes);
            movies.add(m);
        }
        return movies;
    }

    public ArrayList<Rater> loadRaters(String filename) {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        ArrayList<String> ratersList = new ArrayList<String>();
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord r : parser) {
            String rater_id = r.get("rater_id");
            String movie_id = r.get("movie_id");
            double rating = Double.parseDouble(r.get("rating"));
            if (!ratersList.contains(rater_id)) {
                Rater rater = new EfficientRater(rater_id);
                rater.addRating(movie_id, rating);
                raters.add(rater);
                ratersList.add(rater_id);
            } else {
                for (Rater curRater : raters) {
                    if (curRater.getID().equals(rater_id)) {
                        curRater.addRating(movie_id, rating);
                    }
                }
            }
        }
        return raters;
    }    
    
    public void testLoadMovies() {
        ArrayList<Movie> test = loadMovies("data/ratedmoviesfull.csv");
        System.out.println("Number of loaded movies: " + test.size());
        //System.out.println(test);
        int specGenre = 0;
        int specMinutes = 0;
        HashMap<String, Integer> directors = new HashMap<String, Integer>();
        int maxMoviesByDirector = 0;
        ArrayList<String> bestDirectors = new ArrayList<String>();
        for (Movie m : test){
            if (m.getGenres().contains("Comedy")) {
                specGenre +=1;
            }
            if (m.getMinutes() > 150) {
                specMinutes +=1;
            }
            String curDirector = m.getDirector();
            String[] arDirectors = curDirector.split(", ");
            for (String i : arDirectors) {
                if (directors.containsKey(i)) {
                    directors.put(i, directors.get(i)+1);
                } else {
                    directors.put(i, 1);
                }
            }
        }
        for (String bestDirector : directors.keySet()) {
                if (directors.get(bestDirector) > maxMoviesByDirector) {
                    maxMoviesByDirector = directors.get(bestDirector);
                    bestDirectors.clear();
                    bestDirectors.add(bestDirector);
                } else if (directors.get(bestDirector) == maxMoviesByDirector) {
                    bestDirectors.add(bestDirector);
                }
        }
        System.out.println("Movies with genre Comedy: " + specGenre);
        System.out.println("Movies with length more than 150 minutes: " + specMinutes);
        System.out.println("Max number of movied directed by one director: " + maxMoviesByDirector);
        System.out.println("Directors with most movies: " + bestDirectors);
    }
    
    public void testLoadRaters() {
        ArrayList<Rater> test = loadRaters("data/ratings.csv");
        System.out.println("Number of loaded raters: " + test.size());
        /*for (Rater r : test) {
            System.out.println("Rater " + r.getID() + " has " + r.numRatings() + " ratings");
            ArrayList<String> ratedMovies = r.getItemsRated();
            for (String movie : ratedMovies) {
                System.out.println("Movie with ID " + movie + " rated " + r.getRating(movie));
            }
        }*/
        String searchRater = "193";
        int maxRatings = 0;
        ArrayList<String> bestRaters = new ArrayList<String>();
        String searchMovie = "1798709";
        int numOfRatings = 0;
        Set<String> set = new LinkedHashSet<>(new ArrayList<String>());
        for (Rater r : test) {
            if (r.getID().equals(searchRater)) {
                System.out.println("Rater " + searchRater + " has " + r.numRatings() + " ratings");
            }
            if (r.numRatings() > maxRatings) {
                maxRatings = r.numRatings();
                bestRaters.clear();
                bestRaters.add(r.getID());
            } else if (r.numRatings() == maxRatings) {
                bestRaters.add(r.getID());
            }
            if (r.hasRating(searchMovie)) {
                numOfRatings +=1;
            }
            ArrayList<String> curMovies = r.getItemsRated();
            set.addAll(curMovies);
        }
        ArrayList<String> uniqueMovies = new ArrayList<>(set);
        System.out.println("Max ratings " + maxRatings + " has " + bestRaters.size() + " raters." + "Best rater is " + bestRaters);
        System.out.println("Movie with ID " + searchMovie + " has " + numOfRatings + " ratings");
        System.out.println("Num of rated movies: " + uniqueMovies.size());
    }    
}
