package com.example.catalogOfGoods.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Roman Manko
 * @version 1.0
 *
 * Simple bean that represent catalog.
 */

@Entity
@Table(name = "catalog")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Catalog implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "catalog_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    private Catalog parent;

    @Column(name = "level")
    private String level;

}
