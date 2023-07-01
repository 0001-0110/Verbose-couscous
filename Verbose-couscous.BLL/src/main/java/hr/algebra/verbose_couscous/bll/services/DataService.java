package hr.algebra.verbose_couscous.bll.services;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Predicate;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.MovieActorRelation;
import hr.algebra.verbose_couscous.dal.models.MovieDirectorRelation;
import hr.algebra.verbose_couscous.dal.models.MovieGenreRelation;
import hr.algebra.verbose_couscous.dal.repositories.IRepository;
import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public class DataService implements IDataService {

    private final IRepositoryCollection repositoryCollection;

    public DataService(IRepositoryCollection repositoryCollection) {
        this.repositoryCollection = repositoryCollection;
    }

    private <T extends Model> IRepository<T> getRepository(Class<T> type) {
        return repositoryCollection.getRepository(type);
    }

    @Override
    public <T extends Model> Collection<T> selectAll(Class<T> type) {
        return getRepository(type).selectAll();
    }

    @Override
    public <T extends Model> Collection<T> selectWhere(Class<T> type, Predicate<T> predicate) {
        return selectAll(type).stream().filter(predicate).toList();
    }

    @Override
    public <T extends Model> Optional<T> selectById(Class<T> type, int id) {
        return getRepository(type).selectById(id);
    }

    @Override
    public <T extends Model> void insert(Class<T> type, T model) {
        getRepository(type).insert(model);
    }

    @Override
    public void insertMovieGenreRelation(Movie movie, Genre genre) {
        repositoryCollection.getMovieGenreRepository()
                .insert(new MovieGenreRelation(repositoryCollection, movie.Id, genre.Id));
    }

    @Override
    public void insertMovieActorRelation(Movie movie, Actor actor) {
        repositoryCollection.getMovieActorRepository()
                .insert(new MovieActorRelation(repositoryCollection, movie.Id, actor.Id));
    }

    @Override
    public void insertMovieDirectorRelation(Movie movie, Director director) {
        repositoryCollection.getMovieDirectorRepository()
                .insert(new MovieDirectorRelation(repositoryCollection, movie.Id, director.Id));
    }

    @Override
    public <T extends Model> void update(Class<T> type, T model) {
        getRepository(type).update(model);
    }

    @Override
    public <T extends Model> void delete(Class<T> type, int id) {
        getRepository(type).delete(id);
    }

    @Override
    public <T extends Model> void clear(Class<T> type) {
        getRepository(type).clear();
    }

    @Override
    public void clearAll() {
        clear(MovieGenreRelation.class);
        clear(MovieActorRelation.class);
        clear(MovieDirectorRelation.class);
        clear(Movie.class);
        clear(Genre.class);
        clear(Actor.class);
        clear(Director.class);
    }
}
