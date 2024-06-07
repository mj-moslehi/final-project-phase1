package repository.expert;

import base.repository.BaseRepository;
import entity.Expert;

import java.util.Optional;

public interface ExpertRepository extends BaseRepository<Expert,Long> {

    Optional<Expert> signIn(String email , String password);

}
