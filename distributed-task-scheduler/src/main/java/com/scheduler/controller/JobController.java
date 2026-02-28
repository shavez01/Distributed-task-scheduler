package com.scheduler.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scheduler.model.JobEntity;
import com.scheduler.service.JobService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jobs")
public class JobController {
 private final JobService service;
 @PostMapping
 public String create(@RequestBody JobEntity j) throws Exception{
  service.schedule(j.getName(),j.getCron(),j.getUrl(), j.getMaxAttempts());
  return "Scheduled";
 }
}