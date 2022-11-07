package com.vaadin.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.entity.Todo;
import com.vaadin.repository.TodoRepository;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.ui.VerticalLayout;

@SpringComponent
public class TodoLayout extends VerticalLayout {

	
	TodoRepository repo = new TodoRepository();
	
	@PostConstruct
	void init() {
		setTodos(repo.getAll());
		
	}

	private void setTodos(List<Todo> todos) {
		removeAllComponents();
		todos.forEach(todo -> addComponent(new TodoItemLayout(todo)));
		
	}

	public void add(Todo todo) {
	repo.list.add(todo);
	setTodos(repo.getAll());
	}
}
