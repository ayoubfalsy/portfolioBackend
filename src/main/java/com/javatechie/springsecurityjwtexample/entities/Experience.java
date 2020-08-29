package com.javatechie.springsecurityjwtexample.entities;

import javax.persistence.*;

@Entity
@Table(name = "Experience")
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expId;
    @Column(name = "stack", length = 1024)
    private String stack;
    private long progressPercentage;

    public Experience(String stack, long progressPercentage) {
        this.stack = stack;
        this.progressPercentage = progressPercentage;
    }

    public Experience() {
    }

    public Long getExpId() {
        return expId;
    }

    public void setExpId(Long expId) {
        this.expId = expId;
    }

    public String getStack() {
        return stack;
    }

    public void setStack(String stack) {
        this.stack = stack;
    }

    public long getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(long progressPercentage) {
        this.progressPercentage = progressPercentage;
    }
}
