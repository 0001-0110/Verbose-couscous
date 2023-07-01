package hr.algebra.verbose_couscous.dal.models;

/**
 *
 * @author remi
 */
public abstract class Model {

    public int Id;

    protected Model() { }

    protected Model(int id) {
        Id = id;
    }
}
