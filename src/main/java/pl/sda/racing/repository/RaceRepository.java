package pl.sda.racing.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import pl.sda.racing.Race;
import pl.sda.racing.Result;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RaceRepository extends AbstractRepository<Race> {

    public RaceRepository(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Race save(Race entity) {
        return entity.withId((Long) create(entity));
    }

    public List<Race> getAll() {
        Session session = getSessionFactory().openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Race> cr = criteriaBuilder.createQuery(Race.class);
        Root<Race> root = cr.from(Race.class);
        cr.select(root);
        Query<Race> query = session.createQuery(cr);
        return query.getResultList();
    }
}
