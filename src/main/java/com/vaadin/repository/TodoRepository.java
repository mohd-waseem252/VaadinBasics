package com.vaadin.repository;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.entity.Todo;

public class TodoRepository {

	public List<Todo> list= new ArrayList<>();
	{
		list.add(new Todo("Exercise",true));
		list.add(new Todo("yoga",false));
		list.add(new Todo("Judo",false));
		list.add(new Todo("karate",true));
		
		
	}
	
	public List<Todo> getAll(){
		return this.list;
	}
	
	
}
