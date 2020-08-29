package com.javatechie.springsecurityjwtexample.entities;


import javax.persistence.*;

@Entity
@Table(name = "Contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;
    @Column(name = "title")
    private String title;
    @Column(name = "subTitle", length = 1024)
    private String subTitle;
    @Column(name = "picByte", length = 100000)
    private byte[] img;
    @Column(name = "type")
    private String type;
    @Column(name = "number")
    private String number;
    @Column(name = "email")
    private String email;
    @Column(name = "svg")
    private boolean svg;
    @Column(name = "svgName")
    private String svgName;

    public Contact(String title, String subTitle, byte[] img, String type, String number, String email, boolean svg, String svgName) {
        this.title = title;
        this.subTitle = subTitle;
        this.img = img;
        this.type = type;
        this.number = number;
        this.email = email;
        this.svg = svg;
        this.svgName = svgName;
    }

    public Contact() {
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
