package com.itec.FitPower.model.repository;

import com.itec.FitPower.model.entity.NutritionalDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionalDiaryRepository extends JpaRepository<NutritionalDiary, Long> {
}
