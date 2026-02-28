package com.scheduler.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class JobEntity {
 @Id @GeneratedValue
 private Long id;
 private String name;
 private String cron;
 private String url;
 private int maxAttempts;
}
