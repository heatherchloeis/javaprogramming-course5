
/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter {
    private String[] dir;
    
    public DirectorFilter(String director) {
        dir = director.split(",");
    }
    
    @Override public boolean satisfies(String id) {
        for (String s : dir) {
            if (MovieDatabase.getDirector(id).contains(s)) {
                return true;
            }
        }
        return false;
    }
}
