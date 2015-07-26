package persistence;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

/**
 * Created by Guilherme on 25/07/2015.
 */
@Repository
public abstract class AbstractDao<T,ID extends Serializable> implements DaoInterface<T, ID> {

    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    private Session session;
    private Criteria criteria;
    private Class givenClass;

    public AbstractDao() {
        this.givenClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public Long count() throws Exception {
        session = sessionFactory.getObject().openSession();
        criteria = session.createCriteria(givenClass).setProjection(Projections.rowCount());
        Long resultCount = (Long) criteria.uniqueResult();
        session.close();
        return resultCount;
    }

    @Override
    public void save(T entity) throws Exception {
        session = sessionFactory.getObject().openSession();
        session.getTransaction().begin();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(T entity) throws Exception {
        session = sessionFactory.getObject().openSession();
        session.getTransaction().begin();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(T entity) throws Exception {
        session = sessionFactory.getObject().openSession();
        session.getTransaction().begin();
        session.delete(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public Optional<T> findOne(ID primaryKey) throws Exception {
        session = sessionFactory.getObject().openSession();
        criteria = session.createCriteria(givenClass).add(Restrictions.idEq(primaryKey));
        T object = (T) criteria.uniqueResult();
        session.close();
        return Optional.ofNullable(object);
    }

    @Override
    public List<T> findAll() throws Exception {
        session = sessionFactory.getObject().openSession();
        criteria = session.createCriteria(givenClass).setFetchMode("select", FetchMode.SELECT);
        List<T> objectList = criteria.list();
        session.close();
        return objectList;
    }

    @Override
    public boolean exists(ID primaryKey) throws Exception {
        return (findOne(primaryKey).isPresent())?Boolean.TRUE:Boolean.FALSE;
    }
}
