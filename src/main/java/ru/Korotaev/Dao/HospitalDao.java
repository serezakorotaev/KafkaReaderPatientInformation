package ru.Korotaev.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.Korotaev.Model.HospitalEmail;

@Component
public class HospitalDao {

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public HospitalDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


public HospitalEmail returnHospitalEmailById(Long id){
return jdbcTemplate.query("Select email from hospital_email where id=?",new BeanPropertyRowMapper<>(HospitalEmail.class),new Object[]{id})
        .stream().findAny().orElse(null);
}

}
