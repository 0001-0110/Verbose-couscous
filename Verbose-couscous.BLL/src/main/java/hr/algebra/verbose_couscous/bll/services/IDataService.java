package hr.algebra.verbose_couscous.bll.services;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.models.Movie;

/**
 *
 * @author remi
 */
public interface IDataService {

    <T extends Model> Collection<T> selectAll(Class<T> type);

    <T extends Model> Optional<T> selectById(Class<T> type, int id);

    <T extends Model> Collection<T> selectWhere(Class<T> type, Predicate<T> predicate);

    <T extends Model> void insert(Class<T> type, T model);

    void insertMovieGenreRelation(Movie movie, Genre genre);

    void insertMovieActorRelation(Movie movie, Actor actor);

    void insertMovieDirectorRelation(Movie movie, Director director);

    <T extends Model> void update(Class<T> type, T model);

    <T extends Model> void delete(Class<T> type, int id);

    <T extends Model> void clear(Class<T> type);

    void clearAll();
}
