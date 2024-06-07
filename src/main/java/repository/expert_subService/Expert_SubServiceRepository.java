package repository.expert_subService;

import base.repository.BaseRepository;
import entity.Expert;
import entity.Expert_SubService;
import entity.SubService;

import java.util.List;

public interface Expert_SubServiceRepository extends BaseRepository<Expert_SubService , Long> {

    List<SubService> findSubServiceByExpert(Expert expert);

    List<Expert_SubService> findByExpertAndSubService(Expert expert, SubService subService);

}
