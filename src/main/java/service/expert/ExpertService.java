package service.expert;

import base.service.BaseService;
import entity.Expert;

import java.util.Optional;

public interface ExpertService extends BaseService<Expert,Long> {

    Optional<Expert> signIn(String email , String password);

}
