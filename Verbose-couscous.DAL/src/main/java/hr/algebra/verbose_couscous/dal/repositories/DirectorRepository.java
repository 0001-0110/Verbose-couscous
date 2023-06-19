package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Director;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

public class DirectorRepository extends PersonRepository<Director> {

    public DirectorRepository(DatabaseService databaseService) {
        super(Director.class, databaseService);
        ID = "IdDirector";
        NAME = "DirectorName";
    }
}
