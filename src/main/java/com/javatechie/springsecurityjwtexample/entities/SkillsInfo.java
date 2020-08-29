package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "SkillsInfo")
public class SkillsInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillsInfoId;
    @Column(name = "title")
    private String title;
    @Column(name = "subTitle", length = 1024)
    private String subTitle;
    @Column(name = "picByte", length = 100000)
    private byte[] img;
    @Column(name = "type")
    private String type;
    @Column(name = "svg")
    private boolean svg;
    @Column(name = "svgName")
    private String svgName;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Skills> skills = new ArrayList<>();

    public SkillsInfo(String title, String subTitle, byte[] img, String type, boolean svg, String svgName, List<Skills> skills) {
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
        this.type = type;
        this.svg = svg;
        this.svgName = svgName;
        this.skills = skills;
    }

    public SkillsInfo() {
    }

    public Long getSkillsInfoId() {
        return skillsInfoId;
    }

    public void setSkillsInfoId(Long skillsInfoId) {
        this.skillsInfoId = skillsInfoId;
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

    public Collection<Skills> getSkills() {
        return skills;
    }

    public void setSkills(List<Skills> skills) {
        this.skills = skills;
    }
}
