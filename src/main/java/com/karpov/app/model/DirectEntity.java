package com.karpov.app.model;

import javax.persistence.*;


@Entity
@Table (name = "Direct")
public class DirectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_ins")
    @OneToOne(mappedBy = "id")
    private Long id_ins;

    @Column (name = fam, length = 20)
    private String directorFam;

    @Column (name = nam, length = 20)
    private String directorName;

    @Temporal(TemporalType.DATE)
    @Column (name = bdate)
    private Date bdate;
    
}
