package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class MovieActorRelations extends ManyToManyModelRelation<PersonModelRelation<Movie, Actor>, Movie, Actor> {

    public MovieActorRelations() {
        super(repositoryCollection.getMovieActorRepository());
    }

    public Collection<Movie> getRelatedMovies(int idActor) {
        return getRelatedModels1(idActor);
    }

    public Collection<Actor> getRelatedActors(int idMovie) {
        return getRelatedModels2(idMovie);
    }
}
