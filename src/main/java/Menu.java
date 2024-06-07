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
import utility.Validation;

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

    public String choosingEmail() {
        String email;
        while (true) {
            System.out.println("email:");
            email = scanner.nextLine();
            if (Validation.isValidEmail(email)) break;
            else System.out.println("not valid");
        }
        return email;
    }

    public String choosingPassword() {
        String password;
        while (true) {
            System.out.println("password :");
            password = scanner.nextLine();
            if (Validation.isValidPassword(password)) break;
            else System.out.println("not valid");
        }
        return password;
    }

    public void singInAdmin() {
        System.out.println("sign in as admin :");
        if (adminService.signIn(choosingEmail(), choosingPassword()).isPresent()) {
            System.out.println("admin enter successfully");
        } else {
            System.out.println("user not found");
            singInAdmin();
        }
    }


}
