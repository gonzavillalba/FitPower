package com.itec.FitPower.model.repository;

import com.itec.FitPower.model.entity.TrainingDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingDiaryRepository extends JpaRepository<TrainingDiary, Long> {
}
