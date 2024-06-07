package repository.admin;

import base.repository.BaseRepository;
import entity.Admin;

import java.util.Optional;

public interface AdminRepository extends BaseRepository<Admin,Long> {

    Optional<Admin> signIn(String email,String password);

}
