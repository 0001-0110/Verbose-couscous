/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.bll.services;

import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.repositories.IRepository;
import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;
import hr.algebra.verbose_couscous.dal.services.DatabaseService;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 *
 * @author remi
 */
public class DataService {

    private final IRepositoryCollection repositoryCollection;

    public DataService(IRepositoryCollection repositoryCollection) {
        this.repositoryCollection = repositoryCollection;
    }

    private <T extends Model> IRepository getRepository(Class<T> type) {
        return repositoryCollection.getRepository(type).get();
    }

    public <T extends Model> List<T> getAll(Class<T> type) {
        return getRepository(type).getAll();
    }

    public <T extends Model> Optional<T> getById(Class<T> type, int id) {
        return getRepository(type).getById(id);
    }

    public <T extends Model> void create(Class<T> type, T model) {
        getRepository(type).create(model);
    }
    
    public <T extends Model> void edit(Class<T> type, int id, Consumer<T> editer) {
        getRepository(type).edit(id, editer);
    }

    public <T extends Model> void delete(Class<T> type, int id) {
        getRepository(type).delete(id);
    }

    public void save() {
        for (Model model : Model.CreatedModels) {
            model.State = Model.ModelState.Unchanged;
            throw new UnsupportedOperationException("Not supported yet.");
        }
        Model.CreatedModels.clear();
        
        for (Model model : Model.EditedModels) {
            model.State = Model.ModelState.Unchanged;
            throw new UnsupportedOperationException("Not supported yet.");
        }
        Model.EditedModels.clear();
        
        for (Model model : Model.DeletedModels) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        Model.DeletedModels.clear();
    }
}
