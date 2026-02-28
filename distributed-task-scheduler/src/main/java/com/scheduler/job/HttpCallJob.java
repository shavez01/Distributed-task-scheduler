package com.scheduler.job;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.scheduler.util.RedisLockUtil;

@Component
public class HttpCallJob implements Job {

    @Autowired
    private RedisLockUtil lock;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        String maxAttemptStr = context.getMergedJobDataMap().getString("maxAttempts");
        int maxAttempts = maxAttemptStr != null ? Integer.parseInt(maxAttemptStr) : 1;

        int attempt = context.getRefireCount() + 1;

        System.out.println("--------------------------------------------------");
        System.out.println("Job: " + context.getJobDetail().getKey().getName());
        System.out.println("Time: " + java.time.LocalDateTime.now());
        System.out.println("Attempt: " + attempt);

        try {

            String url = context.getMergedJobDataMap().getString("url");

            HttpClient.newHttpClient()
                    .send(
                            HttpRequest.newBuilder(URI.create(url)).GET().build(),
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println("SUCCESS on attempt " + attempt);

        } catch (Exception e) {

            System.out.println("FAILED attempt " + attempt);

            if (attempt < maxAttempts) {
                System.out.println("Retrying...");
                throw new JobExecutionException(e, true); // refire immediately
            } else {
                System.out.println("Max attempts reached. Job permanently failed.");
            }
        }
    }
}


