/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package hr.algebra.verbose_couscous.bll.services;

import hr.algebra.verbose_couscous.bll.models.Credentials;
import hr.algebra.verbose_couscous.dal.models.User;

/**
 *
 * @author remi
 */
public interface IAuthenticationService {

    boolean isAuthenticated();

    boolean tryAuthenticate(Credentials credentials);

    boolean tryRegister(Credentials credentials);

    User getUser();

}
