package hr.algebra.verbose_couscous.dal.models;

import java.awt.datatransfer.Transferable;

/**
 *
 * @author remi
 */
public abstract class Model /*implements Transferable*/ {

    public int Id;

    protected Model(int id) {
        Id = id;
    }
}
