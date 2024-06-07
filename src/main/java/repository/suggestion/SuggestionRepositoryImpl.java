package repository.suggestion;

import base.repository.BaseRepositoryImpl;
import entity.Expert;
import entity.Orders;
import entity.Suggestion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class SuggestionRepositoryImpl extends BaseRepositoryImpl<Suggestion,Long>
        implements SuggestionRepository{
    public SuggestionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Class<Suggestion> getEntityClass() {
        return Suggestion.class;
    }

    @Override
    public String getEntity() {
        return "Suggestion";
    }

    @Override
    public List<Suggestion> findByOrders(Orders orders) {
        Session session = sessionFactory.openSession();
        Query<Suggestion> query =
                session.createQuery("from entity.Suggestion s where s.orders=:orders", Suggestion.class);
        query.setParameter("orders",orders);
        return query.getResultList();
    }

    @Override
    public List<Suggestion> findByOrdersAndExpert(Orders orders, Expert expert) {
        Session session = sessionFactory.openSession();
        Query<Suggestion> query =
                session.createQuery("from entity.Suggestion s " +
                        "where s.orders=:orders and s.expert=:expert", Suggestion.class);
        query.setParameter("orders",orders);
        query.setParameter("expert",expert);
        return query.getResultList();
    }
}
