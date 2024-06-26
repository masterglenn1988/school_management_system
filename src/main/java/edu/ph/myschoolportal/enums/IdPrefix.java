package edu.ph.myschoolportal.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum IdPrefix {
    SMS("SMS"),
    RS("RS");

    private final String id;

}
