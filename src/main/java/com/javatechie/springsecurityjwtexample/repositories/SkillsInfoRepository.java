package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.Skills;
import com.javatechie.springsecurityjwtexample.entities.SkillsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsInfoRepository extends JpaRepository<SkillsInfo, Long> {
    SkillsInfo findBySkillsInfoId(long lngSkillsInfoId);
}
