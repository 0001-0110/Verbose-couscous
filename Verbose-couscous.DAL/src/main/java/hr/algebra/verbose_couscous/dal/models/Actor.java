package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class Actor extends Person {

    private final MovieActorRelations movieRelations;

    public Collection<Movie> getMovies() {
        return movieRelations.getRelatedMovies(Id);
    }

    public Actor(String name) {
        super(name);
        movieRelations = new MovieActorRelations();
    }

    public Actor(int id, String name) {
        super(id, name);
        movieRelations = new MovieActorRelations();
    }
}
