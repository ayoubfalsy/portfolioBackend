package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ProjectInfo")
public class ProjectInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;
    @Column(name = "title")
    private String title;
    @Column(name = "subTitle", length = 1024)
    private String subTitle;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Project> listProjects = new ArrayList<>();

    public ProjectInfo(String title, String subTitle, List<Project> listProjects) {
        this.title = title;
        this.subTitle = subTitle;
        this.listProjects = listProjects;
    }

    public ProjectInfo() {
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public List<Project> getListProjects() {
        return listProjects;
    }

    public void setListProjects(List<Project> listProjects) {
        this.listProjects = listProjects;
    }
}
