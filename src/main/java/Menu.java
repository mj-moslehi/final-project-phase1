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

import java.text.SimpleDateFormat;
import java.util.Date;
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

    public void choosingExpertForSubService() {
        singInAdmin();
        System.out.println("choosing expert for a sub service");
        Expert expert = expertService.findById(choosingExpert());
        if (expert.getExpertStatus().equals(ExpertStatus.CONFIRMED))
            choosingSubServiceForExpert(expert);
        else System.out.println("the expert haven't confirmed");
    }

    public void choosingSubServiceForExpert(Expert expert) {
        SubService subService = choosingSubService();
        if (expert_subServiceService.findByExpertAndSubService(expert, subService).isEmpty()) {
            Expert_SubService expert_subService = Expert_SubService.builder()
                    .expert(expert)
                    .subService(subService)
                    .build();
            expert_subServiceService.saveOrUpdate(expert_subService);
        } else System.out.println("repetitive sub service");
    }

    public Integer choosingTimeOfJob() {
        int timeOfJob;
        while (true) {
            System.out.println("how much hours will it take ? :");
            try {
                timeOfJob = scanner.nextInt();
                break;
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return timeOfJob;
    }

    public void addSuggestion() {
        Expert expert = singInExpert();
        System.out.println("add suggestion");
        Long orderId = choosingValidOrdersForSuggestion(expert);
        if (orderId != null) {
            saveSuggestion(expert, orderId);
        } else System.out.println("there isn't any order for you");
    }

    public void saveSuggestion(Expert expert, Long orderId) {
        Date now = new Date();
        System.out.println("proposed price :");
        Long proposedPrice = choosingPrice();

        Integer timeOfJob = choosingTimeOfJob();

        Suggestion suggestion = Suggestion.builder()
                .date(now)
                .proposedPrice(proposedPrice)
                .timeOfJob(timeOfJob)
                .expert(expert)
                .orders(ordersService.findById(orderId))
                .build();
        suggestionService.saveOrUpdate(suggestion);
        Orders orders = suggestion.getOrders();
        orders.setOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        ordersService.saveOrUpdate(orders);
    }

    public List<Long> getOrderIdsForSuggestion(Expert expert) {
        List<Orders> ordersList = expert_subServiceService.findSubServiceByExpert(expert).stream()
                .map(SubService::getOrders).flatMap(List::stream).toList();

        List<Long> ordersIds = ordersList.stream()
                .filter(orders -> orders.getOrderStatus().equals(
                        OrderStatus.WAITING_FOR_The_SUGGESTION_OF_EXPERTS)
                        ||
                        orders.getOrderStatus().equals(
                                OrderStatus.WAITING_FOR_SPECIALIST_SELECTION)).map(Orders::getId).toList();
        emptyChecking(ordersIds);
        ordersIds.stream().map(ordersService::findById).forEach(System.out::println);
        return ordersIds;
    }

    public Long choosingValidOrdersForSuggestion(Expert expert) {
        List<Long> ordersIds = getOrderIdsForSuggestion(expert);
        Long orderId = choosingOrderIdFromOrderIds(ordersIds);
        Orders orders = ordersService.findById(orderId);
        if (suggestionService.findByOrdersAndExpert(orders, expert).isEmpty()) {
            return orderId;
        } else {
            System.out.println("repetitive order");
            return null;
        }
    }

    public void addService() {
        singInAdmin();
        while (true) {
            System.out.println("add service");
            System.out.println("service name :");
            String name = choosingName();

            Service service = Service.builder().name(name).build();
            try {
                serviceService.saveOrUpdate(service);
                break;
            } catch (Exception e) {
                System.out.println("Repetitive name");
                System.out.println(e.getMessage());

            }
        }
    }

    public String choosingText() {
        String description;
        scanner.nextLine();
        while (true) {
            description = scanner.nextLine();
            if (Validation.isValidText(description)) break;
            else System.out.println("not valid");
        }
        return description;
    }

    public Service choosingService() {
        Service service;
        serviceService.findAll().forEach(System.out::println);
        emptyChecking(serviceService.findAll().stream().map(Service::getId).toList());
        while (true) {
            System.out.println("enter service id :");
            try {
                Long serviceId = scanner.nextLong();
                if (serviceService.findById(serviceId) != null) {
                    service = serviceService.findById(serviceId);
                    break;
                } else System.out.println("not found");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return service;
    }

    public void addSubService() {
        singInAdmin();
        while (true) {
            System.out.println("add sub service");
            System.out.println("sub service name:");
            String name = choosingName();

            Service service = choosingService();

            System.out.println("base price");
            Long basePrice = choosingPrice();

            System.out.println("description :");
            String description = choosingText();

            SubService subService = SubService.builder()
                    .name(name)
                    .description(description)
                    .basePrice(basePrice)
                    .service(service)
                    .build();
            try {
                subServiceService.saveOrUpdate(subService);
                break;
            } catch (Exception e) {
                System.out.println("Repetitive name");
                System.out.println(e.getMessage());
            }
        }
    }

    public void addExpert() {
        Date now = new Date();
        while (true) {
            System.out.println("add expert");
            List<String> personFields = addCleanlyPerson();
            byte[] image = readImage();
            Expert expert = Expert.builder()
                    .firstname(personFields.get(0))
                    .lastname(personFields.get(1))
                    .password(personFields.get(2))
                    .email(personFields.get(3))
                    .image(image)
                    .score(0.0)
                    .expertStatus(ExpertStatus.New)
                    .validity(0L)
                    .dateOfSingUp(now)
                    .build();
            try {
                expertService.saveOrUpdate(expert);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void addCustomer() {
        Date now = new Date();
        while (true) {
            System.out.println("add customer");
            List<String> personFields = addCleanlyPerson();
            Customer customer = Customer.builder()
                    .firstname(personFields.get(0))
                    .lastname(personFields.get(1))
                    .password(personFields.get(2))
                    .email(personFields.get(3))
                    .validity(0L)
                    .dateOfSingUp(now)
                    .build();
            try {
                customerService.saveOrUpdate(customer);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public Long choosingPrice() {
        long price;
        while (true) {
            try {
                price = scanner.nextLong();
                break;
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return price;
    }

    public Date choosingDateOfOrder() {
        Date dateOfOrder;
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("when do you want order ? (yyyy-MM-dd)");
            String dateString = scanner.nextLine();
            try {
                if (Validation.isValidDateString(dateString)) {
                    dateOfOrder = sdf.parse(dateString);
                    break;
                } else System.out.println("not valid");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return dateOfOrder;
    }

    public void addOrders() {
        Customer customer = singInCustomer();
        while (true) {
            System.out.println("add order");

            SubService subService = choosingSubService();
            System.out.println("address :");
            String address = choosingText();

            Date dateOfOrder = choosingDateOfOrder();

            System.out.println("proposed price :");
            Long proposedPrice = choosingPrice();

            System.out.println("description :");
            String description = choosingText();

            Orders orders = Orders.builder()
                    .proposedPrice(proposedPrice)
                    .description(description)
                    .dateOfOrder(dateOfOrder)
                    .address(address)
                    .orderStatus(OrderStatus.WAITING_FOR_The_SUGGESTION_OF_EXPERTS)
                    .subService(subService)
                    .customer(customer)
                    .build();
            try {
                ordersService.saveOrUpdate(orders);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public List<Long> getOrderIdsWithOrderStatusAndCustomer(OrderStatus orderStatus, Customer customer) {
        List<Orders> ordersList = ordersService.findByCustomer(customer).stream()
                .filter(orders -> orders.getOrderStatus().equals(orderStatus)).toList();
        List<Long> orderIds = ordersList.stream().map(Orders::getId).toList();
        emptyChecking(orderIds);
        orderIds.stream().map(ordersService::findById).forEach(System.out::println);
        return orderIds;
    }

    public Long choosingExpertIdFromExpertIds(List<Long> expertIds) {
        Long expertId;
        emptyChecking(expertIds);
        while (true) {
            System.out.println("enter expert id:");
            try {
                expertId = scanner.nextLong();
                if (expertIds.contains(expertId)) {
                    break;
                } else System.out.println("not found");
            } catch (Exception e) {
                scanner.next();
                System.out.println(e.getMessage());
            }
        }
        return expertId;
    }

}
