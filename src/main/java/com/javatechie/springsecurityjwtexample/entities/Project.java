package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;

@Entity
@Table(name = "Project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prjId;
    @Column(name = "title", length = 1024)
    private String title;
    @Column(name = "subTitle", length = 1024)
    private String subTitle;
    private Long periodeStart;
    private Long periodeEnd;


    public Project() {
    }

    public Long getPrjId() {
        return prjId;
    }

    public void setPrjId(Long prjId) {
        this.prjId = prjId;
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

    public Long getPeriodeStart() {
        return periodeStart;
    }

    public void setPeriodeStart(Long periodeStart) {
        this.periodeStart = periodeStart;
    }

    public Long getPeriodeEnd() {
        return periodeEnd;
    }

    public void setPeriodeEnd(Long periodeEnd) {
        this.periodeEnd = periodeEnd;
    }
}
