package hr.algebra.verbose_couscous.dal.repositories;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.dal.models.User;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

/**
 *
 * @author remi
 */
public class UserRepository extends Repository<User> {

    private static final String ID = "IdUser";
    private static final String USERNAME = "Username";
    private static final String PASSWORDHASH = "PasswordHash";
    private static final String PERMISSION = "Permission";

    public UserRepository(DatabaseService databaseService) {
        super(databaseService, User.class.getSimpleName(), 3);
    }

    @Override
    public Collection<User> selectAll() {
        DatabaseService.ResultHandler<Collection<User>> resultHandler = resultSet -> {
            Collection<User> users = new ArrayList<User>();
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt(ID),
                    resultSet.getString(USERNAME),
                    resultSet.getString(PASSWORDHASH),
                    resultSet.getInt(PERMISSION))
                );
            }
            return users;
        };

        return _selectAll(resultHandler).get();
    }

    @Override
    public Optional<User> selectById(int Id) {
        DatabaseService.ResultHandler<User> resultHandler = resultSet -> {
            return new User(
                resultSet.getInt(ID),
                resultSet.getString(USERNAME),
                resultSet.getString(PASSWORDHASH),
                resultSet.getInt(PERMISSION)
            );
        };

        return _selectById(Id, resultHandler);
    }

    @Override
    public User insert(User model) {
        StatementInitializer statementInitializer = statement -> {
            statement.registerOutParameter(ID, Types.INTEGER);
            statement.setString(USERNAME, model.Username);
            statement.setString(PASSWORDHASH, model.PasswordHash);
            statement.setInt(PERMISSION, model.Permission.ToInt());
        };

        Optional<Integer> id = _insert(statementInitializer);
        if (id.isEmpty())
            throw new UnsupportedOperationException("What now ?");

        model.Id = id.get();
        return model;
    }

    @Override
    public void update(User model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(ID, model.Id);
            statement.setString(USERNAME, model.Username);
            statement.setString(PASSWORDHASH, model.PasswordHash);
            statement.setInt(PERMISSION, model.Permission.ToInt());
        };

        _update(statementInitializer);
    }
}
