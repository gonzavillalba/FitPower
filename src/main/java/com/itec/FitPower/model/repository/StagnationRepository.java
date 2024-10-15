package com.itec.FitPower.model.repository;

import com.itec.FitPower.model.entity.Stagnation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StagnationRepository extends JpaRepository<Stagnation, Long> {
}
