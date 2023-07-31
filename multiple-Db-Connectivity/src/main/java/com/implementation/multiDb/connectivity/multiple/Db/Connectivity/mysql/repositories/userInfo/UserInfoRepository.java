package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.repositories.userInfo;

import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.mysql.model.userInfo.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

}
