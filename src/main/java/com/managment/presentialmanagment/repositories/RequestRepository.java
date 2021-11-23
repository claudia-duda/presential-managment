package com.managment.presentialmanagment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.managment.presentialmanagment.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer>{

}
