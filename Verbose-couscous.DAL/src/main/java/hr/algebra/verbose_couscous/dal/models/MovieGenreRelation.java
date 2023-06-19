package hr.algebra.verbose_couscous.dal.models;

import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

public class MovieGenreRelation extends ModelRelation<Movie, Genre> {

    public MovieGenreRelation(IRepositoryCollection repositoryCollection, int id, int idMovie, int idGenre) {
        super(repositoryCollection, id, idMovie, repositoryCollection.getMovieRepository(), idGenre, repositoryCollection.getGenreRepository());
    }

    public Movie getMovie() {
        return getRelatedModel1();
    }

    public Genre getGenre() {
        return getRelatedModel2();
    }
}
