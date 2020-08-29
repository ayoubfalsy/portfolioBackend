package com.javatechie.springsecurityjwtexample.repositories;

import com.javatechie.springsecurityjwtexample.entities.ProjectInfo;
import com.javatechie.springsecurityjwtexample.entities.SkillsInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Long> {
    ProjectInfo findByProjectId(long lngProjectInfoId);
}
