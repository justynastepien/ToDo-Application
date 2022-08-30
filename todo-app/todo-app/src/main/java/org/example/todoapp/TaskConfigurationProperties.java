package org.example.todoapp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration //od weersji 2.2 nie ma wymagania dawania tego
@ConfigurationProperties("task") //prefix
public class TaskConfigurationProperties {

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private Template template;

    @Configuration
    @ConfigurationProperties("template")
    public static class Template{
        @Getter(AccessLevel.PUBLIC)
        @Setter(AccessLevel.PUBLIC)
        private boolean allowMultipleTasks;
    }


}
