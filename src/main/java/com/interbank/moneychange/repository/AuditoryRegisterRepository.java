package com.interbank.moneychange.repository;

import com.interbank.moneychange.entity.Auditory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditoryRegisterRepository extends JpaRepository<Auditory, String> {
}
