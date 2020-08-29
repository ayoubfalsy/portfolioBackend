package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {
    SocialMedia findByMediaId(long lngMediaId);
}
