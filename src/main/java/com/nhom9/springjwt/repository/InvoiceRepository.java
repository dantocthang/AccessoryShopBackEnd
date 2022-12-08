package com.nhom9.springjwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nhom9.springjwt.models.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
	List<Invoice> findByUser_Id(long userId);

	@Query(value = "SELECT IFNULL(SUM(total_price),0) FROM Invoice WHERE was_pay=true", nativeQuery = true)
	double totalTurnover();
}