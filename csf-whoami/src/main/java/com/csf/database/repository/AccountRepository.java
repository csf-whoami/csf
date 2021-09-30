package com.csf.database.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.csf.database.models.TbAccount;

@Repository
public interface AccountRepository extends JpaRepository<TbAccount, Long> {
    
    /**
     * @param username
     * @return User
     * @description find User by username
     */
    TbAccount findByUsername(String username);
    
    /**
     * @param username
     * @param password
     * @return boolean
     * @description check exist by username and password
     */
    boolean existsByUsernameAndPassword(String username, String password);

    /**
     * Find all accounts by IDs.
     * 
     * @param ids
     * @return
     */
    @Query(value = "SELECT acc "
            + " FROM TbAccount acc "
            + " WHERE acc.id IN (:ids)"
            + " ORDER BY acc.createdAt ")
    List<TbAccount> findAllByIds(@Param("ids") List<Long> ids);
}

