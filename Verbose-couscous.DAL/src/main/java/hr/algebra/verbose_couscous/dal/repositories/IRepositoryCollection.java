/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.models.Genre;
import hr.algebra.verbose_couscous.dal.models.ModelRelation;
import hr.algebra.verbose_couscous.dal.models.Movie;
import hr.algebra.verbose_couscous.dal.models.PersonModelRelation;
import hr.algebra.verbose_couscous.dal.models.User;

/**
 *
 * @author remi
 */
public interface IRepositoryCollection {

    IRepository<User> getUserRepository();
    IRepository<Movie> getMovieRepository();
    IRepository<Genre> getGenreRepository();
    IRepository<ModelRelation<Movie, Genre>> getMovieGenreRepository();
    IRepository<Actor> getActorRepository();
    IRepository<PersonModelRelation<Movie, Actor>> getMovieActorRepository();
    IRepository<Director> getDirectorRepository();
    IRepository<PersonModelRelation<Movie, Director>> getMovieDirectorRepository();
}
