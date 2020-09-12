package pl.sda.racing.repository;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.racing.Pigeon;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

@RequiredArgsConstructor
public abstract class AbstractRepository<T> implements Repository<T> {
    @Getter(AccessLevel.PROTECTED)
    private final SessionFactory sessionFactory;

    @Override
    public abstract T save(T entity);

    protected Serializable create(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Serializable id = session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }

}
