package com.vaadin.service;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.entity.Person;
import com.vaadin.entity.PersonGrid;
import com.vaadin.event.MouseEvents.ClickListener;
import com.vaadin.event.MouseEvents.DoubleClickEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class FilteredGridLayout extends VerticalLayout {

	VerticalLayout menu = new VerticalLayout();
    private final PersonGrid personGrid;
    private TextField nameFilter;
    private TextField taskField;
    private TextField idFilter;
    private TextField addressFilter;
    private Label label;
    private MenuBar menuBar;

    public FilteredGridLayout() {
    	personGrid = new PersonGrid();
    	
    	
    	
    	Button button = new Button();
    	button.setStyleName(ValoTheme.BUTTON_DANGER);
    	button.setIcon(VaadinIcons.TRASH);
    	
    	button.addClickListener(click-> {
        	personGrid.remove(personGrid.getSelectedItems());
        	personGrid.getDataProvider().refreshAll();
        	});
    
        nameFilter = new TextField();
        nameFilter.setPlaceholder("Name...");
        nameFilter.addValueChangeListener(this::mainFilter);
        
        
        
        addressFilter = new TextField();
        addressFilter.setPlaceholder("address...");
        addressFilter.addValueChangeListener(this::mainFilter);
        
        idFilter = new TextField();
        idFilter.setPlaceholder("id");
        idFilter.addValueChangeListener(this::onIdFilterTextChange);
        
        label=new Label("Actions ->");
             
        Column<Person, Long> c1 = personGrid.addColumn(Person::getId).setCaption("Person Id");
        Column<Person, String> c2 = personGrid.addColumn(Person::getName).setCaption("Person Name");
        Column<Person, String> c3 = personGrid.addColumn(Person::getAddress).setCaption("Address");
        
        
        
        
        personGrid.getEditor().setEnabled(true);
        Binder<Person> editorBinder = personGrid.getEditor().getBinder();
        
        TextField nameField = new TextField();
        Binder.Binding<Person, String> nameBinding = editorBinder.forField(nameField).bind(Person::getName, Person::setName);
        c2.setEditorBinding(nameBinding);
        
        TextField addressField = new TextField();
        Binder.Binding<Person, String> addressBinding = editorBinder.forField(addressField).bind(Person::getAddress, Person::setAddress);
        c3.setEditorBinding(addressBinding);
        
        personGrid.getFilterRow().getCell(c1).setComponent(idFilter);
        personGrid.getFilterRow().getCell(c2).setComponent(nameFilter);
        personGrid.getFilterRow().getCell(c3).setComponent(addressFilter);
        
//        HasValue<?> nameField = personGrid.getColumn("Person Name").getEditorBinding().getField();
//        nameField.setRequiredIndicatorVisible(true);
//        
        
//        c2.setEditorComponent(taskField, Person:: setName).setExpandRatio(1);
//        c3.setEditorComponent(taskField, Person:: setAddress).setExpandRatio(1);
//        
        personGrid.setSelectionMode(SelectionMode.MULTI);
        
        
        
        
       
     
//        menu.addComponent(button);
//        menu.setComponentAlignment(button, Alignment.BOTTOM_RIGHT);
        personGrid.setSizeFull();
        
        addComponent(button);
        addComponent(gridMenuBar());
        setComponentAlignment(button, Alignment.TOP_RIGHT);
        addComponent(personGrid);
        
     
    }
    
    public MenuBar gridMenuBar() {
    	menuBar = new MenuBar();
    	
   
    	
    MenuBar.Command command = new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				
				UI.getCurrent().addWindow(openWindow());	
			}
    		
    	};
    	
     	menuBar.addItem("Add",VaadinIcons.PLUS,command );
     	
    	return menuBar;
    }
    
    public Window openWindow() {
    	
    	VerticalLayout verticalLayout = new VerticalLayout();
    	
    	Window popUp = new Window("Add Person");
    	popUp.setHeight("300px");
    	popUp.setWidth("400px");
    	
    	popUp.setPosition(400, 200);
    	TextField addressField = new TextField();
    	addressField.setCaption("Address");
		TextField nameField = new TextField();
		nameField.setCaption("Name");
		
		Button saveButton = new Button("save",click ->{
			personGrid.addToList(new Person(nameField.getValue(), addressField.getValue()));
			personGrid.getDataProvider().refreshAll();
			popUp.close();
			Notification.show("Added Successfully").setDelayMsec(1000);
		});
		
		
		
		
		Button cancelButton = new Button("cancel");
		
		HorizontalLayout buttonLayout = new HorizontalLayout();
		
		
		
		popUp.setContent(verticalLayout);
		buttonLayout.addComponents(saveButton, cancelButton);
		verticalLayout.addComponents(nameField, addressField, buttonLayout);
    	
		return popUp;
    }

    public void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.addFilter(Person::getName, s -> caseInsensitiveContains(s, event.getValue()));
       
    }
    
    public void onAddressFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.addFilter(Person::getAddress, s-> caseInsensitiveContains(s, event.getValue()));
       
    }
    
    public void mainFilter(HasValue.ValueChangeEvent<String> event) {
    	ListDataProvider<Person> listDataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
    	listDataProvider.setFilter((item) -> {
    		boolean nameDoesMatch = item.getName().toLowerCase().contains(nameFilter.getValue().toLowerCase());
    		boolean addressDoesMatch = item.getAddress().toLowerCase().contains(addressFilter.getValue().toLowerCase());
    		
    		return nameDoesMatch && addressDoesMatch;
    	});
    	
    	
    }
    
    public void onIdFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getId, s-> caseInsensitiveContains(Double.toString(s), event.getValue()));
      
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

}