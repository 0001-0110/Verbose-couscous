package hr.algebra.verbose_couscous.dal.repositories;

import hr.algebra.verbose_couscous.dal.models.Actor;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;

public class ActorRepository extends PersonRepository<Actor> {

    public ActorRepository(DatabaseService databaseService) {
        super(Actor.class, databaseService);
        ID = "IdActor";
        NAME = "ActorName";
    }
}
