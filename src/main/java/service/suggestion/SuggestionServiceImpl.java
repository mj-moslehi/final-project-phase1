package service.suggestion;

import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import entity.Expert;
import entity.Orders;
import entity.Suggestion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.suggestion.SuggestionRepository;

import java.util.List;

public class SuggestionServiceImpl extends BaseServiceImpl<Suggestion, Long, SuggestionRepository>
        implements SuggestionService {
    private final SuggestionRepository suggestionRepository;
    private final SessionFactory sessionFactory;

    public SuggestionServiceImpl(SuggestionRepository suggestionRepository , SessionFactory sessionFactory){
        super(suggestionRepository , sessionFactory);
        this.suggestionRepository = suggestionRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Suggestion> findByOrders(Orders orders) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<Suggestion> foundEntity = suggestionRepository.findByOrders(orders);
            session.getTransaction().commit();
            return foundEntity;
        } catch (Exception e) {
            throw new NotFoundException(String.format("entity with %s not found", orders));
        }
    }


    @Override
    public List<Suggestion> findByOrdersAndExpert(Orders orders, Expert expert) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<Suggestion> foundEntity = suggestionRepository.findByOrdersAndExpert(orders,expert);
            transaction.commit();
            return foundEntity;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }
}
