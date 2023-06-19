package hr.algebra.verbose_couscous.dal.models;

/**
 *
 * @author remi
 */
public class User extends Model {

    public enum UserPermission {
        User(0),
        Administrator(10);

        private int value;

        private UserPermission(int value) {
            this.value = value;
        }

        public static UserPermission FromInt(int value) {
            for (UserPermission permission : values())
                if (permission.ToInt() == value)
                    return permission;
            return null;
        }

        public int ToInt() {
            return value;
        }
    }

    public String Username;
    public String PasswordHash;
    public UserPermission Permission;

    public User(int id, String username, String passwordHash, UserPermission permission) {
        super(id);
        Username = username;
        PasswordHash = passwordHash;
        Permission = permission;
    }

    public User(int id, String username, String passwordHash, int permission) {
        super(id);
        Username = username;
        PasswordHash = passwordHash;
        Permission = UserPermission.FromInt(permission);
    }
}
