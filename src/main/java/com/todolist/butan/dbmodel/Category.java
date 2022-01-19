package com.todolist.butan.dbmodel;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Table(name= "category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable{

    private static final long serialVersionUID = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="sequence_category")
    @SequenceGenerator(schema = "public", sequenceName = "sequence_category", allocationSize = 1, name = "sequence_category")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy="category")
    private Set<Task> tasks;

}
