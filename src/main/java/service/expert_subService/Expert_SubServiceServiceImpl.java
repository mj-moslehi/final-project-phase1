package service.expert_subService;

import base.exception.NotFoundException;
import base.service.BaseServiceImpl;
import entity.Expert;
import entity.Expert_SubService;
import entity.SubService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.expert_subService.Expert_SubServiceRepository;

import java.util.List;

public class Expert_SubServiceServiceImpl extends BaseServiceImpl<Expert_SubService, Long, Expert_SubServiceRepository>
        implements Expert_SubServiceService {

    private final Expert_SubServiceRepository expert_subServiceRepository;
    private final SessionFactory sessionFactory;

    public Expert_SubServiceServiceImpl(Expert_SubServiceRepository expert_subServiceRepository , SessionFactory sessionFactory){
        super(expert_subServiceRepository , sessionFactory);
        this.expert_subServiceRepository = expert_subServiceRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<SubService> findSubServiceByExpert(Expert expert) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<SubService> foundEntity = expert_subServiceRepository.findSubServiceByExpert(expert);
            session.getTransaction().commit();
            return foundEntity;
        } catch (Exception e) {
            throw new NotFoundException(String.format("entity with %s not found", expert));
        }
    }

    @Override
    public List<Expert_SubService> findByExpertAndSubService(Expert expert, SubService subService) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            List<Expert_SubService> foundEntity = expert_subServiceRepository.findByExpertAndSubService(expert,subService);
            transaction.commit();
            return foundEntity;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

}
