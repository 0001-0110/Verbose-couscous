package hr.algebra.verbose_couscous.dal.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.ResultHandler;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

public class MovieRepository extends Repository<Movie> {

    private static final String ID = "IdMovie";
    private static final String NAME = "Title";
    private static final String DESCRIPTION = "MovieDescription";
    private static final String PUBLISHED = "ReleaseDate";
    private static final String DURATION = "Duration";

    public MovieRepository(DatabaseService databaseService) {
        super(databaseService, Movie.class.getSimpleName(), 5);
    }

    @Override
    public Collection<Movie> selectAll() {
        ResultHandler<Collection<Movie>> resultHandler = resultSet -> {
            Collection<Movie> movies = new ArrayList<Movie>();
            while (resultSet.next())
                movies.add(new Movie(
                        resultSet.getInt(ID),
                        resultSet.getString(NAME),
                        resultSet.getString(DESCRIPTION),
                        resultSet.getString(PUBLISHED),
                        resultSet.getInt(DURATION)));
            return movies;
        };

        return _selectAll(resultHandler).get();
    }

    @Override
    public Optional<Movie> selectById(int Id) {
        ResultHandler<Movie> resultHandler = resultSet -> {
            return new Movie(
                    resultSet.getInt(ID),
                    resultSet.getString(NAME),
                    resultSet.getString(DESCRIPTION),
                    resultSet.getString(PUBLISHED),
                    resultSet.getInt(DURATION));
        };

        return _selectById(Id, resultHandler);
    }

    @Override
    public Movie insert(Movie model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setString(NAME, model.Name);
            statement.setString(DESCRIPTION, model.Description);
            statement.setString(PUBLISHED, model.getPublishedDate());
            statement.setInt(DURATION, model.Duration);
        };

        model.Id = _insert(statementInitializer).get();
        return model;
    }

    @Override
    public void update(Movie model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(ID, model.Id);
            statement.setString(NAME, model.Name);
            statement.setString(DESCRIPTION, model.Description);
            statement.setString(PUBLISHED, model.getPublishedDate());
            statement.setInt(DURATION, model.Duration);
        };

        _update(statementInitializer);
    }
}
