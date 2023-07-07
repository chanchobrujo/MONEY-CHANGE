package com.interbank.moneychange.repository;

import com.interbank.moneychange.entity.Relations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public
interface RelationsRepository extends JpaRepository<Relations, String> {

    Optional<Relations> findByIdContainsAndIdContains (String id, String id1);

}
