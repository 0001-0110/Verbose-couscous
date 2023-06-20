package hr.algebra.verbose_couscous.dal.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import hr.algebra.verbose_couscous.dal.repositories.IRepository;

/**
 *
 * @author remi
 */
public abstract class ManyToManyModelRelation<TRelation extends ModelRelation<TModel1, TModel2>, TModel1 extends Model, TModel2 extends Model> {

    private IRepository<TRelation> repository;

    public ManyToManyModelRelation(IRepository<TRelation> repository) {
        this.repository = repository;
    }

    private <T extends Model> Collection<T> getRelatedModels(Predicate<TRelation> predicate, Function<TRelation, T> getModel) {
        Collection<TRelation> relations = repository.selectWhere(predicate);
        List<T> result = new ArrayList<T>();
        for (TRelation relation : relations)
            result.add(getModel.apply(relation));
        return result;
    }

    protected Collection<TModel1> getRelatedModels1(Predicate<TRelation> predicate) {
        return getRelatedModels(predicate, TRelation::getRelatedModel1);
    }

    protected Collection<TModel2> getRelatedModels2(Predicate<TRelation> predicate) {
        return getRelatedModels(predicate, TRelation::getRelatedModel2);
    }
}
