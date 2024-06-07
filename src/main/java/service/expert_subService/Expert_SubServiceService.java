package service.expert_subService;

import base.service.BaseService;
import entity.Expert;
import entity.Expert_SubService;
import entity.SubService;

import java.util.List;

public interface Expert_SubServiceService extends BaseService<Expert_SubService, Long> {

    List<SubService> findSubServiceByExpert(Expert expert);

    List<Expert_SubService> findByExpertAndSubService(Expert expert, SubService subService);
}
