
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GenreFilter implements Filter {
    private String gf;
    
    public GenreFilter(String genre) {
        gf = genre;
    }
    
    @Override public boolean satisfies(String id) {
        return MovieDatabase.getGenres(id).contains(gf);
    }
}
