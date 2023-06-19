package hr.algebra.verbose_couscous.dal.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.ResultHandler;
import hr.algebra.verbose_couscous.dal.services.DatabaseService.StatementInitializer;

/**
 *
 * @author remi
 */
public abstract class ModelRelationRepository<TRelation extends ModelRelation<TModel1, TModel2>, TModel1 extends Model, TModel2 extends Model> extends Repository<TRelation> {

    protected IRepositoryCollection repositoryCollection;

    private final String ID;
    private final String IDMODEL1;
    private final String IDMODEL2;

    public ModelRelationRepository(IRepositoryCollection repositoryCollection, DatabaseService databaseService, String modelName, String idField, String idMovieField, String idPersonField) {
        super(databaseService, modelName, 2);
        this.repositoryCollection = repositoryCollection;
        ID = idField;
        IDMODEL1 = idMovieField;
        IDMODEL2 = idPersonField;
    }

    protected abstract TRelation newInstance(int id, int idMovie, int idPerson);

    @Override
    public Collection<TRelation> selectAll() {
        ResultHandler<Collection<TRelation>> resultHandler = resultSet -> {
            Collection<TRelation> relations = new ArrayList<TRelation>();
            while (resultSet.next()) {
                relations.add(newInstance(
                    resultSet.getInt(ID),
                    resultSet.getInt(IDMODEL1),
                    resultSet.getInt(IDMODEL2)
                ));
            }
            return relations;
        };

        return _selectAll(resultHandler).get();
    }

    @Override
    public Optional<TRelation> selectById(int Id) {
        ResultHandler<TRelation> resultHandler = resultSet -> {
            return newInstance(
                resultSet.getInt(ID),
                resultSet.getInt(IDMODEL1),
                resultSet.getInt(IDMODEL2));
        };

        return _selectById(Id, resultHandler);
    }

    @Override
    public TRelation insert(TRelation model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(IDMODEL1, model.IdModel1);
            statement.setInt(IDMODEL2, model.IdModel2);
        };

        model.Id = _insert(statementInitializer).get();
        return model;
    }

    @Override
    public void update(TRelation model) {
        StatementInitializer statementInitializer = statement -> {
            statement.setInt(ID, model.Id);
            statement.setInt(IDMODEL1, model.IdModel1);
            statement.setInt(IDMODEL2, model.IdModel2);
        };

        _update(statementInitializer);
    }
}
