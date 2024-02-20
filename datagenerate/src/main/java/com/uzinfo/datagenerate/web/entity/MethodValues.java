package com.uzinfo.datagenerate.web.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "method_values")
public class MethodValues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "method_value")
    private String methodValue;
    @Column(name = "description")
    private String description;
    @Column(name = "has_params")
    private boolean hasParams;
    @Column(name = "params_max_count")
    private Integer paramsMaxCount;

    @ManyToOne
    @JoinColumn(name = "base_method_id")
    private BaseMethod baseMethod;
}
