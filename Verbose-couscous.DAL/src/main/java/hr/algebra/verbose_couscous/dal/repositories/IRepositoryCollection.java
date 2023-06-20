package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;
import hr.algebra.verbose_couscous.dal.models.User;
import hr.algebra.verbose_couscous.dal.models.Model;

/**
 *
 * @author remi
 */
public interface IRepositoryCollection {

    IRepository<User> getUserRepository();

    IRepository<Movie> getMovieRepository();

    IRepository<Genre> getGenreRepository();

    IRepository<ModelRelation<Movie, Genre>> getMovieGenreRepository();

    IRepository<Actor> getActorRepository();

    IRepository<PersonModelRelation<Movie, Actor>> getMovieActorRepository();

    IRepository<Director> getDirectorRepository();

    IRepository<PersonModelRelation<Movie, Director>> getMovieDirectorRepository();

    <T extends Model> IRepository<T> getRepository(Class<T> type);
}
