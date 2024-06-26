package edu.ph.myschoolportal.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Table(name = "sms_role")
@NoArgsConstructor
@AllArgsConstructor
public class SmsRole implements Serializable {
    private static final long serialVersionUID = 1905122041950251207L;

    @Id
    @Column(name = "role_id", nullable = false)
    private String roleId;

    @Column(name = "user_role", length = 20, nullable = false)
    private String userRole;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName="user_id", foreignKey=@ForeignKey(name = "fk_sms_role_user_id"))
    private SmsUser smsUser;

    public static SmsRole smsRole(SmsRole smsRole, SmsUser smsUser){
        smsRole.setSmsUser(smsUser);
        smsRole.setUserRole(smsUser.getRole());
        return smsRole;
    }
}
