package hr.algebra.verbose_couscous.dal.repositories;

import java.util.HashMap;
import java.util.Map;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.MovieActorRelation;
import hr.algebra.verbose_couscous.dal.models.MovieDirectorRelation;
import hr.algebra.verbose_couscous.dal.models.MovieGenreRelation;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;
import hr.algebra.verbose_couscous.dal.models.User;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

/**
 *
 * @author remi
 */
public class RepositoryCollection implements IRepositoryCollection {

    public static final RepositoryCollection Instance = new RepositoryCollection();

    // This repository collection is absolutely awful.
    // It is hard to maintain and without any modularity
    // I tried using types to pick the right repository, but type erasure was a
    // serious problem when the class itself was generic
    // I then tried with strings, but it was just as hideous.
    // So here we are, I ended up with this weird combination of hard code 
    // that is needed for the generics that can't be easily retrieved by type
    // and a generic method because my generic dataService can't work without it.

    private final IRepository<User> userRepository;
    private final IRepository<Movie> movieRepository;
    private final IRepository<Genre> genreRepository;
    private final IRepository<ModelRelation<Movie, Genre>> movieGenreRepository;
    private final IRepository<Actor> actorRepository;
    // Types inside generics cannot be casted, so this NEEDS to be a PersonModelRelation, and not just a ModelRelation
    private final IRepository<PersonModelRelation<Movie, Actor>> movieActorRepository;
    private final IRepository<Director> directorRepository;
    // Same here
    private final IRepository<PersonModelRelation<Movie, Director>> movieDirectorRepository;

    private final Map<Class<? extends Model>, IRepository<? extends Model>> repositories;

    private RepositoryCollection() {
        DatabaseService databaseService = new DatabaseService();

        userRepository = new UserRepository(databaseService);
        movieRepository = new MovieRepository(databaseService);
        genreRepository = new GenreRepository(databaseService);
        movieGenreRepository = new MovieGenreRepository(this, databaseService);
        actorRepository = new ActorRepository(databaseService);
        movieActorRepository = new MovieActorRelationRepository(this, databaseService);
        directorRepository = new DirectorRepository(databaseService);
        movieDirectorRepository = new MovieDirectorRelationRepository(this, databaseService);

        repositories = new HashMap<Class<? extends Model>, IRepository<? extends Model>>(8);
        repositories.put(User.class, userRepository);
        repositories.put(Movie.class, movieRepository);
        repositories.put(Genre.class, genreRepository);
        repositories.put(MovieGenreRelation.class, movieGenreRepository);
        repositories.put(Actor.class, actorRepository);
        repositories.put(MovieActorRelation.class, movieActorRepository);
        repositories.put(Director.class, directorRepository);
        repositories.put(MovieDirectorRelation.class, movieDirectorRepository);
    }

    @Override
    public IRepository<User> getUserRepository() {
        return userRepository;
    }

    @Override
    public IRepository<Movie> getMovieRepository() {
        return movieRepository;
    }

    @Override
    public IRepository<Genre> getGenreRepository() {
        return genreRepository;
    }

    @Override
    public IRepository<ModelRelation<Movie, Genre>> getMovieGenreRepository() {
        return movieGenreRepository;
    }

    @Override
    public IRepository<Actor> getActorRepository() {
        return actorRepository;
    }

    @Override
    public IRepository<PersonModelRelation<Movie, Actor>> getMovieActorRepository() {
        return movieActorRepository;
    }

    @Override
    public IRepository<Director> getDirectorRepository() {
        return directorRepository;
    }

    @Override
    public IRepository<PersonModelRelation<Movie, Director>> getMovieDirectorRepository() {
        return movieDirectorRepository;
    }

    @Override
    public <T extends Model> IRepository<T> getRepository(Class<T> type) {
        return (IRepository<T>)repositories.get(type);
    }
}
