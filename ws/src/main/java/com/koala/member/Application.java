package com.koala.member;

import com.koala.utils.config.annotation.EnableDataSourceConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author Liuyf
 * @Date 2016-08-09
 * @Time 14:00
 * @Description
 */
@SpringBootApplication
@ComponentScan("com.koala")
@EnableDataSourceConfiguration
@ImportResource(locations = "dubbo-provider.xml")
public class Application implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        System.in.read();
    }
}
