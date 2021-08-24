package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    List<User> findByAgeBetweenOrderByAgeDesc(String from, String to);

    Page<User> findByAgeBetween(String from, String to, Pageable pageable);

    @Query("select u from User u order by u.age desc")
    List<User> getUserDesc();

    @Transactional
    @Modifying
    @Query("update User u set u.age = :#{#param.age} where u.name = :#{#param.name} ")
    int updateUserByName(@Param("param") User user );
}
