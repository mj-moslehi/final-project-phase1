package service.subService;

import base.service.BaseService;
import entity.Service;
import entity.SubService;

import java.util.List;

public interface SubServiceService extends BaseService<SubService,Long> {

    List<SubService> findByService(Service service);

}
