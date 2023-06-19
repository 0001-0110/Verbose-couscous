package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class MovieActorRelations extends ManyToManyModelRelation<PersonModelRelation<Movie, Actor>, Movie, Actor> {

    public MovieActorRelations(IRepositoryCollection repositoryCollection) {
        super(repositoryCollection.getMovieActorRepository());
    }

    public Collection<Movie> getRelatedMovies(int idActor) {
        return getRelatedModels1(relation -> relation.IdModel2 == idActor);
    }

    public Collection<Actor> getRelatedActors(int idMovie) {
        return getRelatedModels2(relation -> relation.IdModel1 == idMovie);
    }
}
