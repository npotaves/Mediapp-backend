package com.mitocode.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SignDTO {

    @EqualsAndHashCode.Include
    private Integer idSign;

    @NotNull
    private PatientDTO patient;

    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String temperature;

    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String pulse;

    @NotNull
    @NotEmpty
    @Size(min=2, max = 50)
    private String respiratoryRate;

    @NotNull
    private LocalDateTime signDate;

}
