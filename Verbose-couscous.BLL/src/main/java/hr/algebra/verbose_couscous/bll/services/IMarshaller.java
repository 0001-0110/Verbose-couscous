package hr.algebra.verbose_couscous.bll.services;

import java.util.Optional;

/**
 *
 * @author remi
 */
public interface IMarshaller {

    public <T> Optional<T> importFrom(String filename, Class<T> type);

    public <T> boolean exportTo(String filename, T model);
}
