package com.javatechie.springsecurityjwtexample.entities;


import javax.persistence.*;

@Entity
@Table(name = "Greeting")
public class Greeting {
    //      title subTitle img
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long greetingId;
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
    @Column(name = "pdfByte", length = 100000)
    private byte[] pdf;
    @Column(name = "typePdf")
    private String typePdf;

    public Greeting(String title, String subTitle, byte[] img) {
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
    }

    public Greeting(String title, String subTitle, byte[] img, String type, boolean svg, String svgName) {
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
        this.type = type;
        this.svg = svg;
        this.svgName = svgName;
    }

    public Greeting(String title, String subTitle, byte[] img, String type, boolean svg, String svgName, byte[] pdf, String typePdf) {
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
        this.type = type;
        this.svg = svg;
        this.svgName = svgName;
        this.pdf = pdf;
        this.typePdf = typePdf;
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }

    public String getTypePdf() {
        return typePdf;
    }

    public void setTypePdf(String typePdf) {
        this.typePdf = typePdf;
    }

    public String getSvgName() {
        return svgName;
    }

    public void setSvgName(String svgName) {
        this.svgName = svgName;
    }

    public Greeting() {
    }

    public boolean isSvg() {
        return svg;
    }

    public void setSvg(boolean svg) {
        this.svg = svg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(Long greetingId) {
        this.greetingId = greetingId;
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
}
