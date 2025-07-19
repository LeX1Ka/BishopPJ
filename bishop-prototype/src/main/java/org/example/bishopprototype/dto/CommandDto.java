package org.example.bishopprototype.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandDto {

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotNull
    private String priority;

    @NotBlank
    @Size(max = 100)
    private String author;

    @NotBlank
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?$"
    )
    private String time;
}
