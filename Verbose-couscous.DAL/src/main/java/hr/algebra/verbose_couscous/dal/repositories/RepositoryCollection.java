package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;
import hr.algebra.verbose_couscous.dal.models.User;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

/**
 *
 * @author remi
 */
public class RepositoryCollection implements IRepositoryCollection {

    // This repository collection is absolutely awful
    // It is hard to maintain and without any modularity
    // I tried using types to pick the right repository, but type erasure was a
    // serious problem when the class itself was generic
    // I then tried with strings, but it was just as hideous
    // So here we are, every repository has to be hard coded

    private final IRepository<User> userRepository;
    private final IRepository<Movie> movieRepository;
    private final IRepository<Genre> genreRepository;
    private final IRepository<ModelRelation<Movie, Genre>> movieGenreRepository;
    private final IRepository<Actor> actorRepository;
    // PersonModelRelation must be used here. ModelRelation will not work and I don't know why
    private final IRepository<PersonModelRelation<Movie, Actor>> movieActorRepository;
    private final IRepository<Director> directorRepository;
    // Same here
    private final IRepository<PersonModelRelation<Movie, Director>> movieDirectorRepository;

    public RepositoryCollection() {
        DatabaseService databaseService = new DatabaseService();
        userRepository = new UserRepository(databaseService);
        movieRepository = new MovieRepository(databaseService);
        genreRepository = new GenreRepository(databaseService);
        movieGenreRepository = new MovieGenreRepository(this, databaseService);
        actorRepository = new ActorRepository(databaseService);
        movieActorRepository = new MovieActorRelationRepository(this, databaseService);
        directorRepository = new DirectorRepository(databaseService);
        movieDirectorRepository = new MovieDirectorRelationRepository(this, databaseService);
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
}
