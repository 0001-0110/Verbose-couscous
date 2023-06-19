package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.MovieActorRelation;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;

public class MovieActorRelationRepository extends MoviePersonRelationRepository<Actor> {

    private static final String ID = "Id";
    private static final String IDMOVIE = "IdMovie";
    private static final String IDDIRECTOR = "IdActor";

    public MovieActorRelationRepository(IRepositoryCollection repositoryCollection, DatabaseService databaseService) {
        super(repositoryCollection, databaseService, "MovieDirector", ID, IDMOVIE, IDDIRECTOR);
    }

    protected PersonModelRelation<Movie, Actor> newInstance(int id, int idMovie, int idDirector) {
        return new MovieActorRelation(repositoryCollection, id, idMovie, idDirector);
    }
}
