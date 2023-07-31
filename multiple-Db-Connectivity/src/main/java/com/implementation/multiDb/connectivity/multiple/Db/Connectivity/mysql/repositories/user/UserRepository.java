package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.repositories.user;

import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
