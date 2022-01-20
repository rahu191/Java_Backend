package com.demo.springgraphql.mysql.repository;

import com.demo.springgraphql.mysql.model.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerRepository extends JpaRepository<Dealer, Long> {

}
