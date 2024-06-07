package repository.subService;

import base.repository.BaseRepository;
import entity.Service;
import entity.SubService;

import java.util.List;

public interface SubServiceRepository extends BaseRepository<SubService,Long> {

    List<SubService> findByService(Service service);

}
