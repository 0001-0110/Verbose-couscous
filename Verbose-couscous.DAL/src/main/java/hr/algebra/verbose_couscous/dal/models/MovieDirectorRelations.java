package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieDirectorRelations extends ManyToManyModelRelation<PersonModelRelation<Movie, Director>, Movie, Director> {

    public MovieDirectorRelations(IRepositoryCollection repositoryCollection) {
        super(repositoryCollection.getMovieDirectorRepository());
    }

    public Collection<Movie> getRelatedMovies(int idDirector) {
        return getRelatedModels1(relation -> relation.IdModel2 == idDirector);
    }

    public Collection<Director> getRelatedDirectors(int idMovie) {
        return getRelatedModels2(relation -> relation.IdModel1 == idMovie);
    }
}
