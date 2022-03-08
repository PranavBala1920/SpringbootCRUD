package com.example.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.springboot.entity.CustomerModel;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

	@Query(value = "SELECT * FROM customers_details WHERE (email = :email OR mobile = :mobile) AND id <> :id", nativeQuery = true)
	public List<CustomerModel> findEmaiList(@Param("email") String email, @Param("mobile") String mobile,
			@Param("id") Long id);

}
