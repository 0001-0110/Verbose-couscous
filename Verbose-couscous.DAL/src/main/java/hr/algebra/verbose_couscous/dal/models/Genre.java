/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.dal.models;

import java.util.Collection;

/**
 *
 * @author remi
 */
public class Genre extends Model {

    public String Name;

    private final ManyToManyModelRelation<ModelRelation<Movie, Genre>, Movie, Genre> movieRelations;
    public Collection<Movie> getMovies() {
        return movieRelations.getRelatedModels1(Id);
    }

    public Genre(String name) {
        Name = name;
        movieRelations = new MovieGenreRelations();
    }

    public Genre(int id, String name) {
        super(id);
        Name = name;
        movieRelations = new MovieGenreRelations();
    }
}
