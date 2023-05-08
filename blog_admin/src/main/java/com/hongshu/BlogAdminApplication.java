package com.hongshu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * projectName: my_blog
 *
 * @author: WangYiBing
 * time: 2023/5/8 15:18 周一
 * description:
 */
@SpringBootApplication
@EnableScheduling
public class BlogAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }
}
