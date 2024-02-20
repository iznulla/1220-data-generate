package com.uzinfo.datagenerate.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "data_base")
public class DataBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "url")
    private String url;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "driver")
    private String driver;
}
