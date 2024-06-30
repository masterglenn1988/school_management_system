package edu.ph.myschoolportal.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmailDto {

    @Schema(example = "glenmark1018@gmail.com")
    private String to;

    @Schema(example = "project.repository1988@gmail.com")
    private String from;

    @Schema(example = "Sample Email Test")
    private String subject;

    @Schema(example = "Sample email test..")
    private String body;

    @Schema(hidden = true)
    private String attachment;
}
