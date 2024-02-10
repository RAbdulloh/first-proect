package org.example.my_java_project.repository;

import org.example.my_java_project.module.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdRepository extends JpaRepository<Product,Integer> {

    Optional<Product> findByProdIdAndDeleteAtIsNull(Integer prodId);

    Boolean existsByUserIdAndDeleteAtIsNull(Integer userId);

    Boolean existsByProdNameAndDeleteAtIsNull(String prodName);

}
