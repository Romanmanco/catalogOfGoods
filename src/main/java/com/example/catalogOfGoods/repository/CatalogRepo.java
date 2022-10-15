package com.example.catalogOfGoods.repository;

import com.example.catalogOfGoods.model.entity.Catalog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Repository interface for catalog.
 */

@Repository
public interface CatalogRepo extends JpaRepository<Catalog, Long> {

    Optional<List<Catalog>> findByParentIsNull();

    Optional<List<Catalog>> findByParentId(Long id);

    Page<Catalog> findAll(Pageable pageable);

}
