/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.dal.models;

/**
 *
 * @author remi
 */
public class Genre extends Model {

    public String Name;

    public ManyToManyModelRelation<ModelRelation<Movie, Genre>, Movie, Genre> Movies;

    public Genre(int id, String name) {
        super(id);
        Name = name;
    }
}
