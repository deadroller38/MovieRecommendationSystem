import java.util.*;

/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {

    Random myRandom;
    
    public RecommendationRunner() {
        myRandom = new Random();
    }
    
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> rateMovies = new ArrayList<String>();
        ArrayList<String> loadedMovies = MovieDatabase.filterBy(new YearAfterFilter(2000));
        for (int i = 0; i<20; i++) {
            String m = loadedMovies.get(myRandom.nextInt(loadedMovies.size()));
            rateMovies.add(m);
        }
        return rateMovies;
    }
    
    public void printRecommendationsFor(String webRaterID) {
        FourthRatings input = new FourthRatings();
        int topRaters = 20;
        int minimalRaters = 3;
        ArrayList<Rating> ratings = input.getSimilarRatings(webRaterID, topRaters, minimalRaters);
        ArrayList<Rating> recommendations = new ArrayList<Rating>();
        for (Rating r : ratings) {
            if (!RaterDatabase.getRater(webRaterID).hasRating(r.getItem())) {
                recommendations.add(r);
            }
            if (recommendations.size() == 10) {
                break;
            }
        }
        if (recommendations.size() == 0) {
            System.out.println("No movies found");
        } else {
            System.out.println("<h2>We recommend you to watch the following movies</h2>");
            System.out.println("<style> table, th, td {border: 1px solid black; border-collapse: collapse; margin-left:auto; margin-right: auto; text-align: center;} </style>");
            System.out.println("<table>");
            for (int i = 0; i<recommendations.size(); i++) {
                System.out.println("<tr>");
                System.out.println("<td><img width=\"200px\" src=\""+MovieDatabase.getPoster(recommendations.get(i).getItem())+"\" </td>");
                System.out.println("<td>"+MovieDatabase.getTitle(recommendations.get(i).getItem())+"</td>");
                System.out.println("<td>"+MovieDatabase.getGenres(recommendations.get(i).getItem())+"</td>");                
                System.out.println("<td>"+MovieDatabase.getCountry(recommendations.get(i).getItem())+"</td>");
                System.out.println("<td>"+MovieDatabase.getYear(recommendations.get(i).getItem())+"</td>");
                System.out.println("<td>"+MovieDatabase.getMinutes(recommendations.get(i).getItem())+" minutes"+"</td>");
                System.out.println("</tr>");                
            }
            System.out.println("/<table>");    
        }
    }
    
}
