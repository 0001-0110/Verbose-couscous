package hr.algebra.verbose_couscous.dal.models;

/**
 *
 * @author remi
 */
public abstract class Person extends Model {

    public String Name;

    public Person(String name) {
        Name = name;
    }

    public Person(int id, String name) {
        super(id);
        Name = name;
    }
}
