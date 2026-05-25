package com.cso.demo.repository;
 
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
import com.cso.demo.model.Materiales;
 
@Repository
public interface MaterialesRepository extends JpaRepository<Materiales, Integer> {
    List<Materiales> findByNombre(String nombre); 
    List<Materiales> findById(int id); 
    List<Materiales> findByMentorId(Integer id);
}