import java.util.*;

/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter {
    private ArrayList<String> myDirectors;
    
    public DirectorFilter(String directors) {
        myDirectors = new ArrayList<String>(Arrays.asList(directors.split(",")));
    }
    
    public boolean satisfies(String id) {
        String idDirectors = MovieDatabase.getDirector(id);
        for (String d : myDirectors) {
            if (idDirectors.contains(d)) {
                return true;
            }
        }
        return false;
    }
}
