package com.backend.reportservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data // Lombok: Tự động tạo getter, setter, toString,...
@Entity
@Table(name = "reports") // Tên bảng trong database
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_crime")
    private String typeOfCrime;

    private String severity;

    private LocalDate date;

    private String reporter;

    private String status;
}