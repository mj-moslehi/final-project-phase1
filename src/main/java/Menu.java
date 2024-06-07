import service.admin.AdminService;
import service.comment.CommentService;
import service.customer.CustomerService;
import service.expert.ExpertService;
import service.expert_subService.Expert_SubServiceService;
import service.orders.OrdersService;
import service.service.ServiceService;
import service.subService.SubServiceService;
import service.suggestion.SuggestionService;
import utility.ApplicationContext;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    private final AdminService adminService = ApplicationContext.getAdminService();
    private final CommentService commentService = ApplicationContext.getCommentService();
    private final CustomerService customerService = ApplicationContext.getCustomerService();
    private final ExpertService expertService = ApplicationContext.getExpertService();
    private final OrdersService ordersService = ApplicationContext.getOrdersService();
    private final ServiceService serviceService = ApplicationContext.getServiceService();
    private final SubServiceService subServiceService = ApplicationContext.getSubServiceService();
    private final SuggestionService suggestionService = ApplicationContext.getSuggestionService();
    private final Expert_SubServiceService expert_subServiceService = ApplicationContext.getExpertSubServiceService();


}
