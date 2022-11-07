package com.vaadin.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.entity.Employee;
import com.vaadin.entity.PersonGrid;
import com.vaadin.entity.Todo;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.repository.EmployeeRepo;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.data.sort.SortDirection;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ComponentRenderer;
import com.vaadin.ui.themes.ValoTheme;

@SpringUI
public class TodoUI extends UI {

	private VerticalLayout root;
	private FilteredGridLayout filteredGridLayout;
	

	@Autowired
	TodoLayout todoLayout;

	@Override
	protected void init(VaadinRequest request) {
		filteredGridLayout=new FilteredGridLayout();
		setupLayout();
		addHeader();
		addForm();
		addTodoList();
		addDeleteButton();
		addFilter();
		addGrid();
		

	}

	private void addFilter() {
		root.addComponent(filteredGridLayout.menu);
		root.addComponent(filteredGridLayout);
		
//		search.addClickListener(click ->{
//			
//		})
	}

	private void addGrid() {
//		EmployeeRepo empRepo = new EmployeeRepo();
		Grid<Employee> grid = new Grid<>(Employee.class);
//		
//		PersonGrid grid=new PersonGrid();
//grid.addSelectionListener()
//		grid.addStyleName("outlined");
//		grid.setSizeFull();
//
//		grid.setItems(empRepo.getEmployees());
//		grid.setColumns("id", "name", "salary");
//
//	grid.sort("salary", SortDirection.DESCENDING);
////
		
//		
//		grid.addColumn(employee -> {
//			Button delete = new Button("");
//			delete.addStyleName(ValoTheme.BUTTON_DANGER);
//			delete.setIcon(VaadinIcons.TRASH);
//			return delete;
//		}, new ComponentRenderer()).setCaption("Delete");
//		
//		grid.getColumn("Delete").setRenderer(new ButtonRenderer(e ->{
//			empRepo.remove((Employee) e.getItem());
//			
//		}));

//		
//		root.addComponent(grid);
		
		
		
	}

//	grid.addComponentColumn(employee ->
//	{
//	    Button button = new Button("Delete");
//	    button.addClickListener(click -> {
//	       empRepo.remove(click.get); 
//	      grid.getDataProvider().refreshItem(employee);
//	    });
//	    return button;
//	},new ComponentRenderer());
	

	private void addDeleteButton() {
		root.addComponent(new Button("Delete Completed"));

	}

	private void addTodoList() {
		todoLayout.setWidth("80%");
		root.addComponent(todoLayout);

	}
	
	

	private void addForm() {
		HorizontalLayout formLayout = new HorizontalLayout();
		formLayout.setWidth("80%");

		TextField task = new TextField();
		Button add = new Button("");
		add.addStyleName(ValoTheme.BUTTON_PRIMARY);
		add.setIcon(VaadinIcons.PLUS);

		formLayout.addComponentsAndExpand(task);
		formLayout.addComponents(add);

		add.addClickListener(click -> {
			todoLayout.add(new Todo(task.getValue()));
			task.clear();
			task.focus();
		});
		task.focus();
		add.setClickShortcut(ShortcutAction.KeyCode.ENTER);

		root.addComponent(formLayout);

//		Button save =new Button("save");
//		save.addStyleName(ValoTheme.LABEL_H1);
//		save.addStyleName(ValoTheme.BUTTON_PRIMARY);

//		root.addComponent(save);

	}

	private void addHeader() {
		Label header = new Label("TODOs");
		header.addStyleName(ValoTheme.LABEL_H1);
//		header.setWidth("-50%");
		root.addComponent(header);

	}

	private void setupLayout() {
		root = new VerticalLayout();
		root.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		setContent(root);

	}

	private void delete(Employee employee1) {
	
//		HorizontalLayout horizontalLayout = new HorizontalLayout();
//		Button delete = new Button("");
//		delete.addStyleName(ValoTheme.BUTTON_DANGER);
//		delete.setIcon(VaadinIcons.TRASH);
//		horizontalLayout.addComponent(delete);
//		delete.addClickListener(e ->{
//		
//		});
//		return horizontalLayout;
	}


}
