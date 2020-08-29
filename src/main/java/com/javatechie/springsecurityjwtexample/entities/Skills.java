package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;

@Entity
@Table(name = "Skills")
public class Skills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsId;
    @Column(name = "value", length = 1024)
    private String value;

    public Skills(String value) {
        this.value = value;
    }

    public Skills() {
    }

    public Long getSkillsId() {
        return skillsId;
    }

    public void setSkillsId(Long skillsId) {
        this.skillsId = skillsId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

