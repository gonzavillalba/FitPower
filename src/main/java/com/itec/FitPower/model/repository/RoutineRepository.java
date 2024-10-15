package com.itec.FitPower.model.repository;


import com.itec.FitPower.model.entity.Client;
import com.itec.FitPower.model.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {
    List<Routine>findByClient(Client client);
    Optional<Routine> findByRoutineCode(String code);
}
