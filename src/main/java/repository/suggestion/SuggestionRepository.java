package repository.suggestion;

import base.repository.BaseRepository;
import entity.Expert;
import entity.Orders;
import entity.Suggestion;

import java.util.List;

public interface SuggestionRepository extends BaseRepository<Suggestion,Long> {
    List<Suggestion> findByOrders (Orders orders);

    List<Suggestion> findByOrdersAndExpert (Orders orders , Expert expert);


}
