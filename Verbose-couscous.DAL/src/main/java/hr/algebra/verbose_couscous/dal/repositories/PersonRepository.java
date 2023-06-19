package hr.algebra.verbose_couscous.dal.repositories;

import java.util.Collection;
import java.util.Optional;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import hr.algebra.verbose_couscous.dal.models.Person;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.ResultHandler;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

public abstract class PersonRepository<T extends Person> extends Repository<T> {

    protected String ID;
    protected String NAME;

    private Class<T> modelType;

    public PersonRepository(Class<T> type, DatabaseService databaseService) {
        super(databaseService, type.getSimpleName(), 1);
        modelType = type;
    }

    private T newInstance(int id, String name) {
        try {
            return modelType.getConstructor(int.class, String.class).newInstance(id, name);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    @Override
    public Collection<T> selectAll() {
        ResultHandler<Collection<T>> resultHandler = resultSet -> {
            Collection<T> directors = new ArrayList<T>();
            while (resultSet.next()) {
                directors.add(newInstance(
                    resultSet.getInt(ID),
                    resultSet.getString(NAME)));
            }
            return directors;
        };

        return _selectAll(resultHandler).get();
    }

    @Override
    public Optional<T> selectById(int Id) {
        ResultHandler<T> resultHandler = resultSet -> {
            return newInstance(
                    resultSet.getInt(ID),
                    resultSet.getString(NAME));
        };

        return _selectById(Id, resultHandler);
    }

    @Override
    public T insert(T model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setString(NAME, model.Name);
        };

        model.Id = _insert(statementInitializer).get();
        return model;
    }

    @Override
    public void update(T model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(ID, model.Id);
            statement.setString(NAME, model.Name);
        };

        _update(statementInitializer);
    }
}
