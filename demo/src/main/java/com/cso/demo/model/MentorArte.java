package com.cso.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MentorArte")
public class MentorArte extends Persona{

    @Column(name = "salon", nullable = false, length = 50)
    private String salon;

    @OneToMany(mappedBy = "mentor", cascade = CascadeType.ALL)
    private List<Materiales> materiales = new ArrayList<>();


    public String getSalon() {
        return this.salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }
 
}
