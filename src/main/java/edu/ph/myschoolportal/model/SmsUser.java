package edu.ph.myschoolportal.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.ph.myschoolportal.enums.UserStatus;
import edu.ph.myschoolportal.util.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "sms_user")
@NoArgsConstructor
@AllArgsConstructor
public class SmsUser implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "dob")
    private String dob;

    @Column(name = "gender")
    private String gender;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "occupation")
    private String occupation;

    @Column(name = "schoolId")
    private String schoolId;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "attempts")
    private int attempts;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "status")
    private String status;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modification_date")
    private Date lastModificationDate;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    @PrePersist
    private void prePersist(){
        creationDate = ObjectUtils.getLocalDateTime();
    }

    @PreUpdate
    private void preUpdate(){
        lastModificationDate = ObjectUtils.getLocalDateTime();
    }

    @JsonManagedReference
    @OneToMany(mappedBy = "smsUser", cascade = {CascadeType.ALL})
    @ToString.Exclude
    private List<SmsRole> smsRole;

    public static SmsUser smsUser(SmsUser smsUser){
        smsUser.setAttempts(0);
        smsUser.setActive(true);
        smsUser.setStatus(UserStatus.ACTIVE.toString());
        smsUser.setCreatedBy("ADMIN");
        return smsUser;
    }
}
