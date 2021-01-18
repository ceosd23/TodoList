package com.example.TodoList;

import com.example.TodoList.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class TodoListApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoListApplication.class, args);
	}

}
