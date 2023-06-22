/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.bll.services;

import hr.algebra.verbose_couscous.dal.models.User;
import java.util.Collection;

/**
 *
 * @author remi
 */
public class AuthenticationService {

    public static final AuthenticationService Instance = new AuthenticationService(DataService.Instance);

    private final DataService dataService;

    public User User;

    private AuthenticationService(DataService dataService) {
        this.dataService = dataService;
    }

    private String getPasswordHash(String password) {
        throw new UnsupportedOperationException();
    }

    public boolean TryAuthenticate(String username, String password) {
        Collection<User> users = dataService.selectWhere(User.class, user -> 
                user.Username == username 
                && user.PasswordHash == getPasswordHash(password));
        if (users.stream().count() != 1)
            // If there is no user corresponding, you can't connect
            // If there is multiple correcponding users, we have a problem
            return false;

        User = users.stream().findFirst().get();
        return true;
    }
}
