package com.scheduler.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduler.model.JobEntity;

public interface JobRepository extends JpaRepository<JobEntity,Long>{}
