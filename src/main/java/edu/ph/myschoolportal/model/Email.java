package edu.ph.myschoolportal.model;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Email {

    @NotNull
    private String to;
    @NotNull
    private String from;
    private String subject;
    private String body;
    private String attachment;
}
