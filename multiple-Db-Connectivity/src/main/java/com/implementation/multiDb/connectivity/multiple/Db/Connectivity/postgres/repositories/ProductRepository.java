package com.implementation.multiDb.connectivity.multiple.Db.Connectivity.postgres.repositories;


import com.implementation.multiDb.connectivity.multiple.Db.Connectivity.postgres.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
 }
