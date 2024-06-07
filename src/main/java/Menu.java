import entity.Expert;
import entity.ExpertStatus;
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

import java.util.List;
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

    public void publicMenu() {

    }

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

    public void emptyChecking(List<Long> ids) {
        if (ids.isEmpty()) {
            System.out.println("not found anything , try again ");
            publicMenu();
        }
    }

    public Long choosingExpert() {
        Long expertId;
        System.out.println("experts :");
        expertService.findAll().forEach(System.out::println);
        emptyChecking(expertService.findAll().stream().map(Expert::getId).toList());
        while (true) {
            System.out.println("enter expert id:");
            try {
                expertId = scanner.nextLong();
                if (expertService.findById(expertId) != null) {
                    break;
                } else System.out.println("not found");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return expertId;
    }

    public void updateExpertStatusToConfirmed() {
        singInAdmin();
        Long expertId = choosingExpert();
        Expert expert = expertService.findById(expertId);
        expert.setExpertStatus(ExpertStatus.CONFIRMED);
        expertService.saveOrUpdate(expert);
    }


}
