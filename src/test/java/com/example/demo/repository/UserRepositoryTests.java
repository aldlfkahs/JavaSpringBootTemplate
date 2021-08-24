package com.example.demo.repository;

import com.example.demo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Test
    public void testInsert(){
        User user = User.builder().name("SWLEE").age("27").build();

        userRepository.save(user);
    }

    @Test
    public void testSelect(){
        String name = "SWLEE";
        Optional<User> result = userRepository.findById(name);
        System.out.println("=======================");

        if(result.isPresent()){
            User user = result.get();
            System.out.println(user);
        }
    }

    @Test
    @Transactional
    public void testSelect2(){
        User user = userRepository.getOne("SWLEE");
        System.out.println("======================");
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = User.builder().name("gorum").age("12").build();
        System.out.println(userRepository.save(user));
    }

    @Test
    public void testPageDefault(){
        //1페이지 10개
        Pageable pageable = PageRequest.of(0, 10);
        Page<User> result = userRepository.findAll(pageable);
        System.out.println(result);
        System.out.println("=================");
        System.out.println("Total Pages: " + result.getTotalPages());
        System.out.println("Total Count: " + result.getTotalElements());
        System.out.println("Page Number: " + result.getNumber());
        System.out.println("Page Size: " + result.getSize());
        System.out.println("has next page?: " + result.hasNext());
        System.out.println("first page?: " + result.isFirst());
        System.out.println("=================");
        for (User user : result.getContent()) {
            System.out.println(user);
        }
    }

    @Test
    public void testSort(){
        Sort sort1 = Sort.by("age").descending();
        Pageable pageable = PageRequest.of(0, 10, sort1);
        Page<User> result = userRepository.findAll(pageable);
        result.get().forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void testQueryMethods(){
        List<User> list = userRepository.findByAgeBetweenOrderByAgeDesc("10", "20");

        for(User user : list){
            System.out.println(user);
        }
    }

    @Test
    public void testQueryMethodWithPageable(){
        Pageable pageable = PageRequest.of(0, 10, Sort.by("age").descending());
        Page<User> result = userRepository.findByAgeBetween("10", "30", pageable);
        result.get().forEach(user -> System.out.println(user));
    }

    @Test
    public void testQueryAnnotations(){
        List<User> list = userRepository.getUserDesc();

        for(User user : list){
            System.out.println(user);
        }
    }

    @Test
    public void testQueryAnnotationsUpdate(){
        User user = User.builder().name("SWLEE").age("11").build();
        int result = userRepository.updateUserByName(user);
        System.out.println("result = " + result);
    }
}
