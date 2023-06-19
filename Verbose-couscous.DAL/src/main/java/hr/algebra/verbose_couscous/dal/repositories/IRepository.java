package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Model;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.Collection;

/**
 *
 * @author remi
 */
public interface IRepository<T extends Model>
{
    public Collection<T> selectAll();

    public Optional<T> selectById(int Id);

    public Collection<T> selectWhere(Predicate<T> predicate);

    public T insert(T model);

    public void update(T model);

    public void delete(int id);

    public void clear();
}
