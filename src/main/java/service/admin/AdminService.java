package service.admin;

import base.service.BaseService;
import entity.Admin;

import java.util.Optional;

public interface AdminService extends BaseService<Admin, Long> {

    Optional<Admin> signIn(String email, String password);


}
