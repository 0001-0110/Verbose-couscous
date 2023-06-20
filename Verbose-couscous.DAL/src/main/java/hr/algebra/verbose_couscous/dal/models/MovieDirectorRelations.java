package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class MovieDirectorRelations extends ManyToManyModelRelation<PersonModelRelation<Movie, Director>, Movie, Director> {

    public MovieDirectorRelations() {
        super(repositoryCollection.getMovieDirectorRepository());
    }

    public Collection<Movie> getRelatedMovies(int idDirector) {
        return getRelatedModels1(idDirector);
    }

    public Collection<Director> getRelatedDirectors(int idMovie) {
        return getRelatedModels2(idMovie);
    }
}
