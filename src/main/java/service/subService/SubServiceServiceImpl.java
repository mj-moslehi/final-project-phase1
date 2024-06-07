package service.subService;

import base.service.BaseServiceImpl;
import entity.Service;
import entity.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.subService.SubServiceRepository;

import java.util.List;


public class SubServiceServiceImpl extends BaseServiceImpl<SubService, Long , SubServiceRepository>
        implements SubServiceService {
    private final SubServiceRepository subServiceRepository;
    private final SessionFactory sessionFactory;

    public SubServiceServiceImpl(SubServiceRepository subServiceRepository , SessionFactory sessionFactory){
        super(subServiceRepository, sessionFactory);
        this.subServiceRepository = subServiceRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<SubService> findByService(Service service) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<SubService> foundEntity = subServiceRepository.findByService(service);
            transaction.commit();
            return foundEntity;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }
}
