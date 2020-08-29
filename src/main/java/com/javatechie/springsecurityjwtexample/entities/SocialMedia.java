package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;

@Entity
@Table(name = "Social_Media")
public class SocialMedia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mediaId;
    @Column(name = "linkedin")
    private String linkedin;
    @Column(name = "google")
    private String google;
    @Column(name = "facebook")
    private String facebook;
    @Column(name = "instagram")
    private String instagram;
    @Column(name = "isLinkedin")
    private String isLinkedin;
    @Column(name = "isGoogle")
    private String isGoogle;
    @Column(name = "isFacebook")
    private String isFacebook;
    @Column(name = "isInstagram")
    private String isInstagram;

    public SocialMedia(String linkedin, String google, String facebook, String instagram, String isLinkedin, String isGoogle, String isFacebook, String isInstagram) {
        this.linkedin = linkedin;
        this.google = google;
        this.facebook = facebook;
        this.instagram = instagram;
        this.isLinkedin = isLinkedin;
        this.isGoogle = isGoogle;
        this.isFacebook = isFacebook;
        this.isInstagram = isInstagram;
    }

    public SocialMedia() {
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public String getGoogle() {
        return google;
    }

    public void setGoogle(String google) {
        this.google = google;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getIsLinkedin() {
        return isLinkedin;
    }

    public void setIsLinkedin(String isLinkedin) {
        this.isLinkedin = isLinkedin;
    }

    public String getIsGoogle() {
        return isGoogle;
    }

    public void setIsGoogle(String isGoogle) {
        this.isGoogle = isGoogle;
    }

    public String getIsFacebook() {
        return isFacebook;
    }

    public void setIsFacebook(String isFacebook) {
        this.isFacebook = isFacebook;
    }

    public String getIsInstagram() {
        return isInstagram;
    }

    public void setIsInstagram(String isInstagram) {
        this.isInstagram = isInstagram;
    }
}
