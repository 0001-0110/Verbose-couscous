package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.MovieDirectorRelation;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;

public class MovieDirectorRelationRepository extends MoviePersonRelationRepository<Director> {

    private static final String ID = "Id";
    private static final String IDMOVIE = "IdMovie";
    private static final String IDDIRECTOR = "IdDirector";

    public MovieDirectorRelationRepository(IRepositoryCollection repositoryCollection, DatabaseService databaseService) {
        super(repositoryCollection, databaseService, "MovieDirector", ID, IDMOVIE, IDDIRECTOR);
    }

    protected PersonModelRelation<Movie, Director> newInstance(int id, int idMovie, int idDirector) {
        return new MovieDirectorRelation(repositoryCollection, id, idMovie, idDirector);
    }
}
