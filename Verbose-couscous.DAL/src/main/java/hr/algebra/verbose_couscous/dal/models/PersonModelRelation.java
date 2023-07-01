package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepository;

public abstract class PersonModelRelation<TModel extends Model, TPerson extends Person>
        extends ModelRelation<TModel, TPerson> {

    public PersonModelRelation(int idModel, IRepository<TModel> repositoryModel, int idPerson,
            IRepository<TPerson> repositoryPerson) {
        super(idModel, repositoryModel, idPerson, repositoryPerson);
    }

    public PersonModelRelation(int id, int idModel, IRepository<TModel> repositoryModel, int idPerson,
            IRepository<TPerson> repositoryPerson) {
        super(id, idModel, repositoryModel, idPerson, repositoryPerson);
    }

    public int getModelId() {
        return IdModel1;
    }

    public TModel getRelatedModel() {
        return getRelatedModel1();
    }

    public int getPersonId() {
        return IdModel2;
    }

    public TPerson getRelatedPerson() {
        return getRelatedModel2();
    }
}
