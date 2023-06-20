package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class MovieGenreRelations extends ManyToManyModelRelation<ModelRelation<Movie, Genre>, Movie, Genre> {

    public MovieGenreRelations() {
        super(repositoryCollection.getMovieGenreRepository());
    }

    public Collection<Movie> getRelatedMovies(int idGenre) {
        return getRelatedModels1(idGenre);
    }

    public Collection<Genre> getRelatedGenres(int idMovie) {
        return getRelatedModels2(idMovie);
    }
}
