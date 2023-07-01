package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepository;
import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

/**
 *
 * @author remi
 */
public abstract class ModelRelation<TModel1 extends Model, TModel2 extends Model> extends Model {

    IRepository<TModel1> repository1;
    IRepository<TModel2> repository2;

    public int IdModel1;
    public int IdModel2;

    public ModelRelation(int idModel1, IRepository<TModel1> repository1,
            int idModel2, IRepository<TModel2> repository2) {

        this.repository1 = repository1;
        this.repository2 = repository2;

        this.IdModel1 = idModel1;
        this.IdModel2 = idModel2;
    }

    public ModelRelation(int id, int idModel1,
            IRepository<TModel1> repository1, int idModel2, IRepository<TModel2> repository2) {

        super(id);

        this.repository1 = repository1;
        this.repository2 = repository2;

        this.IdModel1 = idModel1;
        this.IdModel2 = idModel2;
    }

    public TModel1 getRelatedModel1() {
        return repository1.selectById(IdModel1).get();
    }

    public TModel2 getRelatedModel2() {
        return repository2.selectById(IdModel2).get();
    }
}
