package utility;

import connection.SessionFactorySingleton;
import org.hibernate.SessionFactory;
import repository.admin.AdminRepository;
import repository.admin.AdminRepositoryImpl;
import repository.comment.CommentRepository;
import repository.comment.CommentRepositoryImpl;
import repository.customer.CustomerRepository;
import repository.customer.CustomerRepositoryImpl;
import repository.expert.ExpertRepository;
import repository.expert.ExpertRepositoryImpl;
import repository.expert_subService.Expert_SubServiceRepository;
import repository.expert_subService.Expert_SubServiceRepositoryImpl;
import repository.orders.OrdersRepository;
import repository.orders.OrdersRepositoryImpl;
import repository.service.ServiceRepository;
import repository.service.ServiceRepositoryImpl;
import repository.subService.SubServiceRepository;
import repository.subService.SubServiceRepositoryImpl;
import repository.suggestion.SuggestionRepository;
import repository.suggestion.SuggestionRepositoryImpl;
import service.admin.AdminService;
import service.admin.AdminServiceImpl;
import service.comment.CommentService;
import service.comment.CommentServiceImpl;
import service.customer.CustomerService;
import service.customer.CustomerServiceImpl;
import service.expert.ExpertService;
import service.expert.ExpertServiceImpl;
import service.expert_subService.Expert_SubServiceService;
import service.expert_subService.Expert_SubServiceServiceImpl;
import service.orders.OrdersService;
import service.orders.OrdersServiceImpl;
import service.service.ServiceService;
import service.service.ServiceServiceImpl;
import service.subService.SubServiceService;
import service.subService.SubServiceServiceImpl;
import service.suggestion.SuggestionService;
import service.suggestion.SuggestionServiceImpl;

public class ApplicationContext {

    static final SessionFactory SESSION_FACTORY;

    private static final AdminRepository ADMIN_REPOSITORY;
    private static final CommentRepository COMMENT_REPOSITORY;
    private static final CustomerRepository CUSTOMER_REPOSITORY;
    private static final ExpertRepository EXPERT_REPOSITORY;
    private static final OrdersRepository ORDERS_REPOSITORY;
    private static final ServiceRepository SERVICE_REPOSITORY;
    private static final SubServiceRepository SUB_SERVICE_REPOSITORY;
    private static final SuggestionRepository SUGGESTION_REPOSITORY;
    private static final Expert_SubServiceRepository EXPERT_SUB_SERVICE_REPOSITORY;

    private static final AdminService ADMIN_SERVICE;
    private static final CommentService COMMENT_SERVICE;
    private static final CustomerService CUSTOMER_SERVICE;
    private static final ExpertService EXPERT_SERVICE;
    private static final OrdersService ORDERS_SERVICE;
    private static final ServiceService SERVICE_SERVICE;
    private static final SubServiceService SUB_SERVICE_SERVICE;
    private static final SuggestionService SUGGESTION_SERVICE;
    private static final Expert_SubServiceService EXPERT_SUB_SERVICE_SERVICE;

    static {

        SESSION_FACTORY = SessionFactorySingleton.getInstance();

        ADMIN_REPOSITORY = new AdminRepositoryImpl(SESSION_FACTORY);
        COMMENT_REPOSITORY = new CommentRepositoryImpl(SESSION_FACTORY);
        CUSTOMER_REPOSITORY = new CustomerRepositoryImpl(SESSION_FACTORY);
        EXPERT_REPOSITORY = new ExpertRepositoryImpl(SESSION_FACTORY);
        ORDERS_REPOSITORY = new OrdersRepositoryImpl(SESSION_FACTORY);
        SERVICE_REPOSITORY = new ServiceRepositoryImpl(SESSION_FACTORY);
        SUB_SERVICE_REPOSITORY = new SubServiceRepositoryImpl(SESSION_FACTORY);
        SUGGESTION_REPOSITORY = new SuggestionRepositoryImpl(SESSION_FACTORY);
        EXPERT_SUB_SERVICE_REPOSITORY = new Expert_SubServiceRepositoryImpl(SESSION_FACTORY);

        ADMIN_SERVICE = new AdminServiceImpl(ADMIN_REPOSITORY, SESSION_FACTORY);
        COMMENT_SERVICE = new CommentServiceImpl(COMMENT_REPOSITORY, SESSION_FACTORY);
        CUSTOMER_SERVICE = new CustomerServiceImpl(CUSTOMER_REPOSITORY, SESSION_FACTORY);
        EXPERT_SERVICE = new ExpertServiceImpl(EXPERT_REPOSITORY, SESSION_FACTORY);
        ORDERS_SERVICE = new OrdersServiceImpl(ORDERS_REPOSITORY, SESSION_FACTORY);
        SERVICE_SERVICE = new ServiceServiceImpl(SERVICE_REPOSITORY, SESSION_FACTORY);
        SUB_SERVICE_SERVICE = new SubServiceServiceImpl(SUB_SERVICE_REPOSITORY, SESSION_FACTORY);
        SUGGESTION_SERVICE = new SuggestionServiceImpl(SUGGESTION_REPOSITORY, SESSION_FACTORY);
        EXPERT_SUB_SERVICE_SERVICE = new Expert_SubServiceServiceImpl(EXPERT_SUB_SERVICE_REPOSITORY,SESSION_FACTORY);

    }

    public static AdminService getAdminService() {
        return ADMIN_SERVICE;
    }

    public static CommentService getCommentService() {
        return COMMENT_SERVICE;
    }

    public static CustomerService getCustomerService() {
        return CUSTOMER_SERVICE;
    }

    public static ExpertService getExpertService() {
        return EXPERT_SERVICE;
    }

    public static OrdersService getOrdersService() {
        return ORDERS_SERVICE;
    }

    public static ServiceService getServiceService() {
        return SERVICE_SERVICE;
    }

    public static SubServiceService getSubServiceService() {
        return SUB_SERVICE_SERVICE;
    }

    public static SuggestionService getSuggestionService() {
        return SUGGESTION_SERVICE;
    }

    public static Expert_SubServiceService getExpertSubServiceService(){return EXPERT_SUB_SERVICE_SERVICE;}
}
