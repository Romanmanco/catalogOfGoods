package com.example.catalogOfGoods.repository;

import com.example.catalogOfGoods.model.entity.Catalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class CatalogRepoTest {
    private static final String NAME = "name";
    private static final String NAME_SECOND = "name2";
    private static final String LEVEL = "1.1";

    @Autowired
    CatalogRepo repository;
    private Catalog parent;
    private Catalog child;

    @Test
    public void catalogSaveTest() {
        init();

        List<Catalog> catalogListTwo = repository.findAll();

        assertEquals(2, catalogListTwo.size());

        for (Catalog catalog : catalogListTwo) {
            if (catalog.getId().equals(parent.getId())) {
                assertEquals(NAME_SECOND, catalog.getName());
                assertEquals("1.", catalog.getLevel());
            }
            if (catalog.getId().equals(child.getId())) {
                assertEquals(NAME, catalog.getName());
                assertEquals(parent, catalog.getParent());
                assertEquals(LEVEL, catalog.getLevel());
            }
        }
    }

    @Test
    public void findByIdTest() {
        init();
        Optional<Catalog> optionalCatalog = repository.findById(parent.getId());
        if (!optionalCatalog.isPresent()) {
            fail("catalog by id not found");
        }
        Catalog catalog = optionalCatalog.get();

        assertEquals(parent.getName(), catalog.getName());
        assertEquals(null, catalog.getParent());
        assertEquals(parent.getLevel(), catalog.getLevel());
    }

    @Test
    public void catalogUpdateTest() {
        init();
        Optional<Catalog> optionalCatalog = repository.findById(parent.getId());
        if (!optionalCatalog.isPresent()) {
            fail("catalog by id not found");
        }
        Catalog storedCatalog = optionalCatalog.get();

        storedCatalog.setName(NAME_SECOND);

        repository.save(storedCatalog);

        List<Catalog> catalogListTwo = repository.findAll();
        for (Catalog catalog : catalogListTwo) {
            if (catalog.getId().equals(parent.getId())) {
                assertEquals(NAME_SECOND, catalog.getName());
                break;
            }
        }
    }

    private void init() {
        Catalog parentCatalog = new Catalog();
        parentCatalog.setLevel("1.");
        parentCatalog.setName("name2");
        parent = repository.save(parentCatalog);

        Catalog catalog = new Catalog();
        catalog.setName(NAME);
        catalog.setParent(parent);
        catalog.setLevel(LEVEL);
        child = repository.save(catalog);
    }
}
