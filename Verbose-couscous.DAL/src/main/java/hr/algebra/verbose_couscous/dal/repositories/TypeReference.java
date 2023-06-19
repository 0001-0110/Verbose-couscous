package hr.algebra.verbose_couscous.dal.repositories;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author remi
 * Tree structure to store generic classes type
 */
@Deprecated
public class TypeReference<T> {

    public final Class<T> Type;
    public final TypeReference<?>[] GenericTypes;

    public TypeReference(Class<T> type, TypeReference<?>... types) {
        Type = type;
        GenericTypes = types;
    }

    @Override
    public int hashCode() {
        return 31 * Objects.hash(Type) + Arrays.hashCode(GenericTypes);
    }

    @Override
    public boolean equals(Object object) {
        TypeReference<?> other = (TypeReference<?>)object;
        // Check equality for the root
        // If the other is null, return false
        boolean equals = other != null && 
                // If both objects are the same instance, return true
                (this == other || 
                // If not, check that both objects have the same type and the same number of children
                Objects.equals(Type, other.Type) && GenericTypes.length == other.GenericTypes.length);
        // Check equality for each child
        int i;
        for (i = 0; i < GenericTypes.length && equals; i++)
            equals &= GenericTypes[i].equals(other.GenericTypes[i]);
        return equals;
    }
}
