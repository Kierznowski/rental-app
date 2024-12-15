package com.kierznowski.rentalApp.authServer.repositories;

import com.kierznowski.rentalApp.authServer.model.User;
import com.kierznowski.rentalApp.authServer.model.UserAdditionalData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);
    UserAdditionalData findUserAdditionalDataByEmail(String email);
}
