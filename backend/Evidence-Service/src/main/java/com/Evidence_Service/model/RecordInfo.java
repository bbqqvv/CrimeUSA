package com.Evidence_Service.model;

import com.Evidence_Service.model.base.BaseClass;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.units.qual.C;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "record_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RecordInfo extends BaseClass {
    @Id
    @Column(name = "record_info_id")
    private String recordInfoId;

    @Column(name = "evidence_id")
    private String evidenceId;

    private String typeName;
    private String source;
    private String summary;

}
