package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.MovieGenreRelation;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

public class MovieGenreRepository extends ModelRelationRepository<ModelRelation<Movie, Genre>, Movie, Genre> {

    private static final String ID = "";
    private static final String IDMOVIE = "";
    private static final String IDGENRE = "";

    public MovieGenreRepository(IRepositoryCollection repositoryCollection, DatabaseService databaseService) {
        super(repositoryCollection, databaseService, "MovieGenre", ID, IDMOVIE, IDGENRE);
    }

    protected ModelRelation<Movie, Genre> newInstance(int id, int idMovie, int idPerson) {
        return new MovieGenreRelation(repositoryCollection, id, idMovie, idPerson);
    }
}
