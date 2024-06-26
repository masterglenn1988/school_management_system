package edu.ph.myschoolportal.repository;

import edu.ph.myschoolportal.model.SmsUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsUserRepository extends JpaRepository<SmsUser, String> {

    public SmsUser findByUsername(@Param("username") String username);

    public SmsUser findByEmail(@Param("email") String email);

    @Query("SELECT MAX(U.userId) FROM SmsUser U")
    public String findByUserId();
}
