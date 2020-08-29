package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Competence")
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long competenceId;
    @Column(name = "title")
    private String title;
    @Column(name = "picByte", length = 100000)
    private byte[] img;
    @Column(name = "type")
    private String type;
    @Column(name = "svg")
    private boolean svg;
    @Column(name = "svgName")
    private String svgName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Experience> experience = new ArrayList<>();

    public Competence(String title, byte[] img, String type, boolean svg, String svgName, List<Experience> experience) {
        this.title = title;
        this.img = img;
        this.type = type;
        this.svg = svg;
        this.svgName = svgName;
        this.experience = experience;
    }

    public Competence() {
    }

    public Long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Long competenceId) {
        this.competenceId = competenceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSvg() {
        return svg;
    }

    public void setSvg(boolean svg) {
        this.svg = svg;
    }

    public String getSvgName() {
        return svgName;
    }

    public void setSvgName(String svgName) {
        this.svgName = svgName;
    }

    public List<Experience> getExperience() {
        return experience;
    }

    public void setExperience(List<Experience> experience) {
        this.experience = experience;
    }
}
