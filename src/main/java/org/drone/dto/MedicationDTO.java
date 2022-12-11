package org.drone.dto;

import lombok.Data;

@Data
public class MedicationDTO {

    private Long medicationId;
    private Integer weight;
    private String medicineName;
    private String code;
    private String image;
}
