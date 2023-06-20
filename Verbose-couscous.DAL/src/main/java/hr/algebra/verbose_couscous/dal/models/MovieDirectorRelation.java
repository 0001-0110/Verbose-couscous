package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieDirectorRelation extends PersonModelRelation<Movie, Director> {

    public MovieDirectorRelation(IRepositoryCollection repositoryCollection, int id, int idMovie, int idDirector) {
        super(repositoryCollection, id, idMovie, repositoryCollection.getRepository(Movie.class), idDirector, repositoryCollection.getRepository(Director.class));
    }

    public Movie getMovie() {
        return getRelatedModel();
    }

    public Director getActor() {
        return getRelatedPerson();
    }
}
