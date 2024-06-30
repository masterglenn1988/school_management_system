package edu.ph.myschoolportal.repository;

import edu.ph.myschoolportal.model.entity.SmsRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRoleRepository extends JpaRepository<SmsRole, String> {

    @Query("SELECT MAX(R.roleId) FROM SmsRole R")
    public String findByRoleId();

}
