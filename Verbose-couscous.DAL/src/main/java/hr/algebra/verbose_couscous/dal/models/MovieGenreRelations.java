package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieGenreRelations extends ManyToManyModelRelation<ModelRelation<Movie, Genre>, Movie, Genre> {

    public MovieGenreRelations(IRepositoryCollection repositoryCollection) {
        super(repositoryCollection.getMovieGenreRepository());
    }

    public Collection<Movie> getRelatedMovies(int idGenre) {
        return getRelatedModels1(relation -> relation.IdModel2 == idGenre);
    }

    public Collection<Genre> getRelatedGenres(int idMovie) {
        return getRelatedModels2(relation -> relation.IdModel1 == idMovie);
    }
}
