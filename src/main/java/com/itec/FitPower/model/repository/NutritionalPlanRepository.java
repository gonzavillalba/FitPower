package com.itec.FitPower.model.repository;

import com.itec.FitPower.model.entity.NutritionalPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalPlanRepository extends JpaRepository<NutritionalPlan, Long> {
    NutritionalPlan findByNutCode(String nutCode);
}
