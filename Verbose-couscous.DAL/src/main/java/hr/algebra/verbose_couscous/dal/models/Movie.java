package hr.algebra.verbose_couscous.dal.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author remi
 */
public class Movie extends Model {

    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public String Name;

    public String Description;

    public LocalDateTime PublishedDateTime;

    // In minutes
    public int Duration;

    public int Year;

    public ManyToManyModelRelation<ModelRelation<Movie, Genre>, Movie, Genre> Genres;

    public ManyToManyModelRelation<ModelRelation<Movie, Actor>, Movie, Actor> Actors;

    public ManyToManyModelRelation<ModelRelation<Movie, Director>, Movie, Director> Directors;

    public Movie(int id, String name, String description, String published, int duration) {
        this(id, name, description, LocalDateTime.parse(published, dateFormatter), duration);
    }

    public Movie(int id, String name, String description, LocalDateTime published, int duration) {
        super(id);
        Name = name;
        Description = description;
        PublishedDateTime = published;
        Duration = duration;
        Year = PublishedDateTime.getYear(); 
    }

    public String getPublishedDate() {
        return PublishedDateTime.format(dateFormatter);
    }
}
