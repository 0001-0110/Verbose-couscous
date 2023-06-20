package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieActorRelation extends PersonModelRelation<Movie, Actor> {

    public MovieActorRelation(IRepositoryCollection repositoryCollection, int id, int idMovie, int idActor) {
        super(repositoryCollection, id, idMovie, repositoryCollection.getRepository(Movie.class), idActor, repositoryCollection.getRepository(Actor.class));
    }

    public Movie getMovie() {
        return getRelatedModel();
    }

    public Actor getActor() {
        return getRelatedPerson();
    }
}
