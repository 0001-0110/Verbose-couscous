package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

public class MovieGenreRelation extends ModelRelation<Movie, Genre> {

    public MovieGenreRelation(IRepositoryCollection repositoryCollection, int idMovie, int idGenre) {
        super(idMovie, repositoryCollection.getRepository(Movie.class), idGenre,
                repositoryCollection.getRepository(Genre.class));
    }

    public MovieGenreRelation(IRepositoryCollection repositoryCollection, int id, int idMovie, int idGenre) {
        super(id, idMovie, repositoryCollection.getRepository(Movie.class), idGenre,
                repositoryCollection.getRepository(Genre.class));
    }

    public Movie getMovie() {
        return getRelatedModel1();
    }

    public Genre getGenre() {
        return getRelatedModel2();
    }
}
