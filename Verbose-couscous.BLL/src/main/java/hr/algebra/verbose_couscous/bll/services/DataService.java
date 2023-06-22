/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.verbose_couscous.bll.services;

import java.util.Collection;
import java.util.Optional;

import hr.algebra.verbose_couscous.dal.models.Model;
import hr.algebra.verbose_couscous.dal.repositories.IRepository;
import hr.algebra.verbose_couscous.dal.repositories.IRepositoryCollection;

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
        return repositoryCollection.getRepository(type);
    }

    public <T extends Model> Collection<T> selectAll(Class<T> type) {
        return getRepository(type).selectAll();
    }

    public <T extends Model> Optional<T> selectById(Class<T> type, int id) {
        return getRepository(type).selectById(id);
    }

    public <T extends Model> void insert(Class<T> type, T model) {
        getRepository(type).insert(model);
    }

    public <T extends Model> void update(Class<T> type, T model) {
        getRepository(type).update(model);
    }

    public <T extends Model> void delete(Class<T> type, int id) {
        getRepository(type).delete(id);
    }
}
