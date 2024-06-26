package edu.ph.myschoolportal.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * This is an enum for UserRoles
 */

@Getter
@RequiredArgsConstructor
public enum UserRole {
    ROLE_SUPER_ADMIN("RS0", "ROLE_SUPER_ADMIN"),
    ROLE_ADMIN("RS1", "ROLE_ADMIN"),
    ROLE_HR("RS2", "ROLE_HR"),
    ROLE_GUIDANCE("RS3", "ROLE_GUIDANCE"),
    ROLE_REGISTRAR("RS4", "ROLE_REGISTRAR"),
    ROLE_MEDIC("RS5", "ROLE_MEDIC"),
    ROLE_TEACHER("RS6", "ROLE_TEACHER"),
    ROLE_ACCOUNTING("RS7", "ROLE_ACCOUNTING"),
    ROLE_CLERK("RS8", "ROLE_CLERK"),
    ROLE_STUDENT("RS9", "ROLE_STUDENT"),
    ROLE_PARENT("RS10", "ROLE_PARENT");

    private final String roleId;
    private final String role;

}
