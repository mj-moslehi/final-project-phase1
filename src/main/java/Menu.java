import entity.*;
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

    public Expert singInExpert() {
        System.out.println("sing in as expert :");
        Expert expert = null;
        String email = choosingEmail();
        String password = choosingPassword();
        if (expertService.signIn(email, password).isPresent()) {
            expert = expertService.signIn(email, password).get();
            if (expert.getExpertStatus().equals(ExpertStatus.CONFIRMED)) {
                System.out.println("expert enter successfully");
                System.out.println(expert);
            } else {
                System.out.println("expert haven't confirmed");
                singInExpert();
            }
        } else {
            System.out.println("user not found");
            singInExpert();
        }
        return expert;
    }

    public void updateExpert() {
        Expert expert = singInExpert();
        System.out.println("update expert");
        System.out.println("enter new password");
        expert.setPassword(choosingPassword());
        expertService.saveOrUpdate(expert);
        System.out.println("updating is done");
    }

    public void updateCustomer() {
        Customer customer = singInCustomer();
        System.out.println("update customer");
        System.out.println("enter new password");
        customer.setPassword(choosingPassword());
        customerService.saveOrUpdate(customer);
        System.out.println("updating is done");
    }

    public void deletingExpertFromSubService() {
        singInAdmin();
        Expert expert = expertService.findById(choosingExpert());
        SubService subService = choosingSubService();
        if (!expert_subServiceService.findByExpertAndSubService(expert, subService).isEmpty()) {
            expert_subServiceService.delete(
                    expert_subServiceService.findByExpertAndSubService(expert, subService).get(0));
        } else System.out.println("you haven't chosen this sub service for this expert");
    }

    public List<Long> getSubServicesWithService() {
        List<SubService> subServiceList = subServiceService.findByService(choosingService());
        System.out.println("sub services :");
        subServiceList.forEach(System.out::println);
        return subServiceList.stream().map(SubService::getId).toList();
    }

    public SubService choosingSubService() {
        SubService subService;
        List<Long> subServiceIds = getSubServicesWithService();
        emptyChecking(subServiceIds);
        while (true) {
            System.out.println("enter sub service id: ");
            try {
                Long subServiceId = scanner.nextLong();
                if (subServiceIds.contains(subServiceId)) {
                    subService = subServiceService.findById(subServiceId);
                    break;
                } else System.out.println("not found");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return subService;
    }

    public void updateSubService() {
        singInAdmin();
        System.out.println("updating sub service");

        SubService subService = choosingSubService();

        System.out.println("base price:");
        Long basePrice = choosingPrice();

        System.out.println("description :");
        String description = choosingText();

        subService.setBasePrice(basePrice);
        subService.setDescription(description);
        subServiceService.saveOrUpdate(subService);

    }

    public Long choosingOrderIdFromOrderIds(List<Long> orderIds) {
        Long orderId;
        while (true) {
            System.out.println("enter order id :");
            try {
                orderId = scanner.nextLong();
                if (orderIds.contains(orderId)) break;
                else System.out.println("not found");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return orderId;
    }

    public Double choosingScore() {
        double score;
        while (true) {
            System.out.println("enter a number between 0 to 5 for score :");
            try {
                score = scanner.nextDouble();
                if (score <= 5 && score >= 0) {
                    break;
                } else System.out.println("out of range");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return score;
    }

    public void addComment() {
        Customer customer = singInCustomer();
        System.out.println("add comment");

        List<Long> ordersIds = getOrderIdsWithOrderStatusAndCustomer(OrderStatus.STARTED, customer);

        Long orderId = choosingOrderIdFromOrderIds(ordersIds);

        Double score = choosingScore();

        System.out.println("description:");
        String commentPart = choosingText();

        Comment comment = Comment.builder()
                .comment(commentPart)
                .score(score)
                .expert(ordersService.findById(orderId).getExpert())
                .customer(customer)
                .build();
        commentService.saveOrUpdate(comment);
        Orders orders = ordersService.findById(orderId);
        orders.setOrderStatus(OrderStatus.DONE);
        ordersService.saveOrUpdate(orders);
    }

    public void updateOrderStatusToComeToPlace() {
        Customer customer = singInCustomer();
        List<Long> ordersIds = getOrderIdsWithOrderStatusAndCustomer
                (OrderStatus.WAITING_FOR_THE_SPECIALIST_TO_COME_TO_YOUR_PLACE, customer);
        Orders orders = ordersService.findById(choosingOrderIdFromOrderIds(ordersIds));

        orders.setOrderStatus(OrderStatus.STARTED);
        ordersService.saveOrUpdate(orders);

    }

    public void addValidity() {
        Customer customer = singInCustomer();
        List<Long> ordersIds = getOrderIdsWithOrderStatusAndCustomer(OrderStatus.DONE, customer);

        Long orderId = choosingOrderIdFromOrderIds(ordersIds);
        Orders orders = ordersService.findById(orderId);
        Expert expert = orders.getExpert();

        Long validity = suggestionService.findByOrdersAndExpert(
                orders, expert).get(0).getProposedPrice();

        expert.setValidity(validity + expert.getValidity());

        customer.setValidity(customer.getValidity() - validity);
        orders.setOrderStatus(OrderStatus.PAID);
        expertService.saveOrUpdate(expert);
        customerService.saveOrUpdate(customer);
        ordersService.saveOrUpdate(orders);
    }
    public Expert singInExpert() {
        System.out.println("sing in as expert :");
        Expert expert = null;
        String email = choosingEmail();
        String password = choosingPassword();
        if (expertService.signIn(email, password).isPresent()) {
            expert = expertService.signIn(email, password).get();
            if (expert.getExpertStatus().equals(ExpertStatus.CONFIRMED)) {
                System.out.println("expert enter successfully");
                System.out.println(expert);
            } else {
                System.out.println("expert haven't confirmed");
                singInExpert();
            }
        } else {
            System.out.println("user not found");
            singInExpert();
        }
        return expert;
    }

    public Customer singInCustomer() {
        System.out.println("sing in as customer");
        Customer customer = null;
        String email = choosingEmail();
        String password = choosingPassword();
        if (customerService.signIn(email, password).isPresent()) {
            System.out.println("customer enter successfully");
            customer = customerService.signIn(email, password).get();
            System.out.println(customer);
        } else {
            System.out.println("user not found");
            singInCustomer();
        }
        return customer;
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
