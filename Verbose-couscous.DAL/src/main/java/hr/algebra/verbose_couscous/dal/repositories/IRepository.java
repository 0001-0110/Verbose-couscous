package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Model;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.Collection;

/**
 *
 * @author remi
 */
public interface IRepository<T extends Model> {

    Collection<T> selectAll();

    Optional<T> selectById(int Id);

    Collection<T> selectWhere(Predicate<T> predicate);

    T insert(T model);

    void update(T model);

    void delete(int id);

    void clear();
}
