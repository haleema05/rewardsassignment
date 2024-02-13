package com.management.rewards.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.management.rewards.entity.TransactionData;

@Repository
public interface TransactionDataRepository extends JpaRepository<TransactionData, Integer>{

	public List<TransactionData> findByCustomerId(int id);
	
	@Query(value = "SELECT customer_Id customerId, sum(rewards) rewards FROM public.transaction_data where date BETWEEN :beginDate AND :endDate "
			+ "group by customer_Id order by customer_Id", nativeQuery = true)
	public List<Integer[]> getTransactions(@Param("beginDate") LocalDate beginDate, @Param("endDate") LocalDate endDate);

	@Query(value = "SELECT customer_Id customerId, sum(rewards) rewards FROM public.transaction_data where date BETWEEN :beginDate AND :endDate "
			+ "and customer_Id = :customerId group by customer_Id order by customer_Id", nativeQuery = true)
	public List<Integer[]> getTransactions(@Param("beginDate") LocalDate beginDate, @Param("endDate") LocalDate endDate, @Param("customerId") int customerId);
}
