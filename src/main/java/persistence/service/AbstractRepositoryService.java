package persistence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.AbstractDao;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Created by Guilherme on 15/08/2015.
 */
@Service
@Transactional(rollbackOn = Exception.class)
public abstract class AbstractRepositoryService<T extends AbstractDao> {

    @Autowired
    T concreteDao;

    public Long countEntities() throws Exception {
        return concreteDao.count();
    }

    public <TE> void saveEntity(TE entity) throws Exception {
        concreteDao.save(entity);
    }

    public <TE> void updateEntity(TE entity) throws Exception {
        concreteDao.update(entity);
    }

    public <TE> void deleteEntity(TE entity) throws Exception {
        concreteDao.delete(entity);
    }

    public <TE, ID extends Serializable> Optional<TE> findEntityById(ID primaryKey) throws Exception {
        return concreteDao.findOne(primaryKey);
    }

    public <TE> List<TE> findAllEntities() throws Exception {
        return concreteDao.findAll();
    }

    public <ID extends Serializable> Boolean hasEntity(ID primaryKey) throws Exception {
        return concreteDao.exists(primaryKey);
    }
}
