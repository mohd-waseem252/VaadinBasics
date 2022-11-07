package com.vaadin.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ButtonRenderer;

public class PersonGrid extends Grid<Person> {
	List<Person> persons = new ArrayList<>();
	HeaderRow filterRow = appendHeaderRow();
	
	
	public HeaderRow getFilterRow() {
		return filterRow;
	}

	public PersonGrid() {
		
		persons.add(new Person(101L, "Shlok", "Mumbai"));
		persons.add(new Person(102L, "sunita", "Delhi"));
		persons.add(new Person(103L, "mamta", "Mumbai"));
		persons.add(new Person(104L, "arjun", "kolkata"));
		persons.add(new Person(105L, "inder", "Mumbai"));
		persons.add(new Person(106L, "rushi", "delhi"));
		persons.add(new Person(107L, "vishal", "lucknow"));
		
		addComponentColumn(attachmentGridData -> {
			Set<Person> data=Set.of(attachmentGridData);
            Button button = new Button(VaadinIcons.TRASH, e -> remove(data));
            return button;
        });
		
//		addColumn(Person::getId).setCaption("id");
//		addColumn(Person::getName).setCaption("name");
//		addColumn(Person::getAddress).setCaption("address");

		setItems(persons);
	
	}
	
	private void delete(Person attachmentGridData) {
		persons.remove(attachmentGridData);
		
//		persons.forEach(e->getDataProvider().refreshItem(e));
		getDataProvider().refreshAll();
//	System.out.println("eeeeeeeeeeeeeee"+attachmentGridData.toString());
	}

	public void remove(Set<Person> listOfPerson) {
		listOfPerson.stream().forEach(p ->{
			persons.remove(p);
			getDataProvider().refreshAll();
		});
	}
}