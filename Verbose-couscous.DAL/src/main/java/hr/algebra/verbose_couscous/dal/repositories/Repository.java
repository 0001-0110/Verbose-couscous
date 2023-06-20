package hr.algebra.verbose_couscous.dal.repositories;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.ResultHandler;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

/**
 *
 * @author remi
 */
public abstract class Repository<T extends Model> implements IRepository<T> {

    private final String selectByIdProcedure;
    private final String selectAllProcedure;
    private final String insertProcedure;
    private final String updateProcedure;
    private final String deleteProcedure;
    private final String clearProcedure;

    private DatabaseService databaseService;

    // Keep track of all the models already loaded to avoid loading twice the same
    // one
    // Help keep data consistent event when used across the whole app
    protected Map<Integer, T> models;

    public Repository(DatabaseService databaseService, String modelName, int propertyCount) {
        // We only need the id when selecting a model
        selectByIdProcedure = getProcedure("select" + modelName, 1);
        // No argument is needed when selecting everything
        selectAllProcedure = getProcedure("select" + modelName + "s", 0);
        // When creating or updating, every property, plus the id, are needed
        insertProcedure = getProcedure("create" + modelName, propertyCount + 1);
        updateProcedure = getProcedure("update" + modelName, propertyCount + 1);
        // We only need an id to delete a model
        deleteProcedure = getProcedure("delete" + modelName, 1);
        // No argument needed to clear everything
        clearProcedure = getProcedure("clear" + modelName + "s", 0);

        this.databaseService = databaseService;

        models = new HashMap<Integer, T>();
    }

    // Create the prepared statements to call the procedures
    private String getProcedure(String procedureName, int argumentCount) {
        if (argumentCount <= 0)
            return String.format("", procedureName);
        return String.format("{ CALL %s }", procedureName, "(" + String.join(",", "?".repeat(argumentCount)) + ")");
    }

    private Optional<Collection<T>> replaceDuplicates(Optional<Collection<T>> models) {
        if (models.isEmpty())
            return Optional.empty();
        return Optional.of(replaceDuplicates(models.get()));
    }

    private Collection<T> replaceDuplicates(Collection<T> models) {
        return models.stream().map(this::replaceDuplicate).toList();
    }

    private Optional<T> replaceDuplicate(Optional<T> model) {
        if (model.isEmpty())
            return Optional.empty();
        return Optional.of(replaceDuplicate(model.get()));
    }

    private T replaceDuplicate(T model) {
        if (models.containsKey(model.Id))
            // If the model already exists, get the one already in use
            return models.get(model.Id);
        // If not, put the new one aas the model responsible for this id and return this one
        models.put(model.Id, model);
        return model;
    }

    protected Optional<Collection<T>> _selectAll(DatabaseService.ResultHandler<Collection<T>> resultHandler) {
        return replaceDuplicates(databaseService.sendQuery(selectAllProcedure, statement -> { }, resultHandler));
    }

    protected Optional<T> _selectById(int id, ResultHandler<T> resultHandler) {
        if (models.containsKey(id))
            return Optional.of(models.get(id));
        return replaceDuplicate(databaseService.sendQuery(selectByIdProcedure, statement -> statement.setInt(1, id), resultHandler));
    }

    // This is not the best solution, and it would be so much better to use a SQL
    // WHERE clause,
    // but I just don't know how to do that
    @Override
    public Collection<T> selectWhere(Predicate<T> predicate) {
        return selectAll().stream().filter(predicate).toList();
    }

    // Return the id of the new model
    protected Optional<Integer> _insert(StatementInitializer statementInitializer) {
        return databaseService.sendQuery(insertProcedure, statementInitializer, resultSet -> resultSet.getInt(1));
    }

    protected void _update(StatementInitializer statementInitializer) {
        databaseService.sendQuery(updateProcedure, statementInitializer);
    }

    @Override
    public void delete(int id) {
        databaseService.sendQuery(deleteProcedure, statement -> statement.setInt(1, id));
    }

    @Override
    public void clear() {
        databaseService.sendQuery(clearProcedure);
    }
}
