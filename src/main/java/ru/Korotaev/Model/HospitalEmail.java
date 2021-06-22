package ru.Korotaev.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "hospital_email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalEmail {
    @Id
    @Column(name = "hospitalId")
    private int hospitalId;
    @Column(name = "email")
    private String email;
}
