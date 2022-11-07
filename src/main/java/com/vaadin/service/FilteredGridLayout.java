package com.vaadin.service;

import com.vaadin.data.Binder;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.HasValue;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.entity.Person;
import com.vaadin.entity.PersonGrid;
import com.vaadin.event.MouseEvents.DoubleClickEvent;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.Registration;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class FilteredGridLayout extends VerticalLayout {

	VerticalLayout menu = new VerticalLayout();
    private final PersonGrid personGrid;
    private TextField nameFilter;
    private TextField taskField;
    private TextField idFilter;
    private TextField addressFilter;
    private Label label;

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
        nameFilter.addValueChangeListener(this::onNameFilterTextChange);
        
        
        
        addressFilter = new TextField();
        addressFilter.setPlaceholder("address...");
        addressFilter.addValueChangeListener(this::onAddressFilterTextChange);
        
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
        setComponentAlignment(button, Alignment.TOP_RIGHT);
        addComponent(personGrid);
       
     
    }

    public void onNameFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getName, s -> caseInsensitiveContains(s, event.getValue()));
    }
    
    public void onAddressFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getAddress, s-> caseInsensitiveContains(s, event.getValue()));
    }
    
    public void mainFilter() {
    	ListDataProvider<Person> listDataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
//    	listDataProvider.setFilter(person->{
//    		boolean idFilterMatch= true;
//    		boolean nameFilterMatch = true;
//    		boolean addressFilterMatch = true;
//    	
//    		if(!nameFilter.isEmpty()) {
//    			
//    		}
//    	
//    	});
    	
    	
    }
    
    public void onIdFilterTextChange(HasValue.ValueChangeEvent<String> event) {
        ListDataProvider<Person> dataProvider = (ListDataProvider<Person>) personGrid.getDataProvider();
        dataProvider.setFilter(Person::getId, s-> caseInsensitiveContains(Double.toString(s), event.getValue()));
    }

    private Boolean caseInsensitiveContains(String where, String what) {
        return where.toLowerCase().contains(what.toLowerCase());
    }

}