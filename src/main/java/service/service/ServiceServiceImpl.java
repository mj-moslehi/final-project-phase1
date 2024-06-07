package service.service;

import base.service.BaseServiceImpl;
import entity.Service;
import org.hibernate.SessionFactory;
import repository.service.ServiceRepository;

public class ServiceServiceImpl extends BaseServiceImpl<Service , Long , ServiceRepository> implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final SessionFactory sessionFactory;

    public ServiceServiceImpl(ServiceRepository serviceRepository , SessionFactory sessionFactory){
        super(serviceRepository , sessionFactory);
        this.serviceRepository = serviceRepository;
        this.sessionFactory = sessionFactory;
    }
}
