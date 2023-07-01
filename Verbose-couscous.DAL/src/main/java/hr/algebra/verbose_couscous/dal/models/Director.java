package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class Director extends Person {

    private final MovieDirectorRelations movieRelations;

    public Collection<Movie> getMovies() {
        return movieRelations.getRelatedMovies(Id);
    }

    public Director(String name) {
        super(name);
        movieRelations = new MovieDirectorRelations();
    }

    public Director(int id, String name) {
        super(id, name);
        movieRelations = new MovieDirectorRelations();
    }
}
