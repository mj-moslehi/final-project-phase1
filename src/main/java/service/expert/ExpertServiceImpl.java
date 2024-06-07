package service.expert;

import base.service.BaseServiceImpl;
import entity.Expert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.expert.ExpertRepository;

import java.util.Optional;

public class ExpertServiceImpl extends BaseServiceImpl<Expert, Long, ExpertRepository> implements ExpertService {
    private final ExpertRepository expertRepository;
    private final SessionFactory sessionFactory;

    public ExpertServiceImpl(ExpertRepository expertRepository, SessionFactory sessionFactory) {
        super(expertRepository, sessionFactory);
        this.expertRepository = expertRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Expert> signIn(String email, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Optional<Expert> result = expertRepository.signIn(email,password);
            transaction.commit();
            return result;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return Optional.empty();
        }
    }
}
