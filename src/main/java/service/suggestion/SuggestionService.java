package service.suggestion;

import base.service.BaseService;
import entity.Expert;
import entity.Orders;
import entity.Suggestion;

import java.util.List;

public interface SuggestionService extends BaseService<Suggestion,Long> {
    List<Suggestion> findByOrders (Orders orders);

    List<Suggestion> findByOrdersAndExpert (Orders orders , Expert expert);

}
