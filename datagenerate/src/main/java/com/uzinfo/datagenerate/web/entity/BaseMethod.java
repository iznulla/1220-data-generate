package com.uzinfo.datagenerate.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "base_method")
public class BaseMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "description")
    private String description;

    @Builder.Default
    @OneToMany(mappedBy = "baseMethod", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<MethodValues> methodValues = new ArrayList<>();
}
