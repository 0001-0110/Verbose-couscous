package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.Person;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

/**
 *
 * @author remi
 * This class has been stripped of most of its features by the ModelRelationRepository, but I'm keeping it for simpler syntax
 */
public abstract class MoviePersonRelationRepository<TPerson extends Person> extends ModelRelationRepository<PersonModelRelation<Movie, TPerson>, Movie, TPerson> {

    public MoviePersonRelationRepository(IRepositoryCollection repositoryCollection, DatabaseService databaseService, String modelName, String idField, String idMovieField, String idPersonField) {
        super(repositoryCollection, databaseService, modelName, idField, idMovieField, idPersonField);
    }
}
