package hr.algebra.verbose_couscous.dal.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.ResultHandler;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

public class GenreRepository extends Repository<Genre> {

    private static final String ID = "IdGenre";
    private static final String NAME = "GenreName";

    public GenreRepository(DatabaseService databaseService) {
        super(databaseService, Genre.class.getSimpleName(), 1);
    }

    @Override
    public Collection<Genre> selectAll() {
        ResultHandler<Collection<Genre>> resultHandler = resultSet -> {
            Collection<Genre> genres = new ArrayList<Genre>();
            while (resultSet.next())
                genres.add(new Genre(
                        resultSet.getInt(ID),
                        resultSet.getString(NAME)));
            return genres;
        };

        return _selectAll(resultHandler).get();
    }

    @Override
    public Optional<Genre> selectById(int Id) {
        ResultHandler<Genre> resultHandler = resultSet -> {
            return new Genre(
                    resultSet.getInt(ID),
                    resultSet.getString(NAME));
        };

        return _selectById(Id, resultHandler);
    }

    @Override
    public Genre insert(Genre model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setString(NAME, model.Name);
        };

        model.Id = _insert(statementInitializer).get();
        return model;
    }

    @Override
    public void update(Genre model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(ID, model.Id);
            statement.setString(NAME, model.Name);
        };

        _update(statementInitializer);
    }
}
