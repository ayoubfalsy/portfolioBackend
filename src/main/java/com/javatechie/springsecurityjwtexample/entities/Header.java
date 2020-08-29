package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;

@Entity
@Table(name = "Header")
public class Header {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHeader;
    @Column(unique = true)
    private String username;
    private boolean competence;
    private boolean project;
    private boolean experience;
    private boolean contact;

    public Header() {
    }

    public Header(String username, boolean competence, boolean project, boolean experience, boolean contact) {
        this.username = username;
        this.competence = competence;
        this.project = project;
        this.experience = experience;
        this.contact = contact;
    }

    public Long getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(Long idHeader) {
        this.idHeader = idHeader;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isCompetence() {
        return competence;
    }

    public void setCompetence(boolean competence) {
        this.competence = competence;
    }

    public boolean isProject() {
        return project;
    }

    public void setProject(boolean project) {
        this.project = project;
    }

    public boolean isExperience() {
        return experience;
    }

    public void setExperience(boolean experience) {
        this.experience = experience;
    }

    public boolean isContact() {
        return contact;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }
}
