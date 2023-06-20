package hr.algebra.verbose_couscous.dal.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

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

    private final MovieGenreRelations genreRelations;

    public Collection<Genre> getGenres() {
        return genreRelations.getRelatedGenres(Id);
    }

    private final MovieActorRelations actorRelations;

    public Collection<Actor> getActors() {
        return actorRelations.getRelatedActors(Id);
    }

    private final MovieDirectorRelations directorRelations;

    public Collection<Director> getDirectors() {
        return directorRelations.getRelatedDirectors(Id);
    }

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
        genreRelations = new MovieGenreRelations();
        actorRelations = new MovieActorRelations();
        directorRelations = new MovieDirectorRelations();
    }

    public String getPublishedDate() {
        return PublishedDateTime.format(dateFormatter);
    }
}
