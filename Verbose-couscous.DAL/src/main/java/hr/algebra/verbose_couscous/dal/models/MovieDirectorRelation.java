package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieDirectorRelation extends PersonModelRelation<Movie, Director> {

    public MovieDirectorRelation(IRepositoryCollection repositoryCollection, int id, int idMovie, int idDirector) {
        super(repositoryCollection, id, idMovie, repositoryCollection.getMovieRepository(), idDirector, repositoryCollection.getDirectorRepository());
    }

    public Movie getMovie() {
        return getRelatedModel();
    }

    public Director getActor() {
        return getRelatedPerson();
    }
}
