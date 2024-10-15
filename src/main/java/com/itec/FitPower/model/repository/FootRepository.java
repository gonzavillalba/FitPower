package com.itec.FitPower.model.repository;

import com.itec.FitPower.model.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FootRepository extends JpaRepository<Food, Long> {
    Food findByName(String name);
}
