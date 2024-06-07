package service.admin;

import base.service.BaseServiceImpl;
import entity.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import repository.admin.AdminRepository;

import java.util.Optional;

public class AdminServiceImpl extends BaseServiceImpl<Admin,Long, AdminRepository> implements AdminService {
    private final AdminRepository adminRepository;
    private final SessionFactory sessionFactory;

    public AdminServiceImpl(AdminRepository adminRepository, SessionFactory sessionFactory) {
        super(adminRepository, sessionFactory);
        this.adminRepository = adminRepository;
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<Admin> signIn(String email, String password) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            Optional<Admin> result = adminRepository.signIn(email,password);
            transaction.commit();
            return result;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            return Optional.empty();
        }
    }
}
