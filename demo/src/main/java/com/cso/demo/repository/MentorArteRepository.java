package com.cso.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cso.demo.model.MentorArte;

@Repository
public interface MentorArteRepository extends JpaRepository<MentorArte, Integer> {
    
    List<MentorArte> findByNombre(String nombre); 
    List<MentorArte> findById(int id); 
    List<MentorArte> findBySalon(String salon); 

}