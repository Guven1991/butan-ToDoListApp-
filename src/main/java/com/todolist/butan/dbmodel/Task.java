package com.todolist.butan.dbmodel;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "task")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_task")
    @SequenceGenerator(schema = "public", sequenceName = "sequence_task", allocationSize = 1, name = "sequence_task")
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_completed")
    private boolean isCompleted;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

}
