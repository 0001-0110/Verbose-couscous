package hr.algebra.verbose_couscous.bll.services;

import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.bll.models.Credentials;
import hr.algebra.verbose_couscous.dal.models.User;

/**
 *
 * @author remi
 */
public class AuthenticationService implements IAuthenticationService {

    private final IDataService dataService;

    private Optional<User> user;

    @Override
    public User getUser() {
        return user.get();
    }

    public AuthenticationService(IDataService dataService) {
        this.dataService = dataService;
    }

    private String getPasswordHash(String password) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isAuthenticated() {
        return user.isPresent();
    }

    @Override
    public boolean tryAuthenticate(Credentials credentials) {
        Collection<User> users = dataService.selectWhere(User.class, user -> user.Username == credentials.Username
                && user.PasswordHash == getPasswordHash(credentials.Password));
        if (users.stream().count() != 1)
            // If there is no user corresponding, you can't connect
            // If there is multiple correcponding users, we have a problem
            return false;

        user = users.stream().findFirst();
        return true;
    }

    @Override
    public boolean tryRegister(Credentials credentials) {
        throw new UnsupportedOperationException();
    }
}
