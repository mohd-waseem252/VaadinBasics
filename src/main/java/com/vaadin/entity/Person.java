package com.vaadin.entity;

import java.util.OptionalLong;

public class Person {

	private Long id;
    private String name;
    private String address;
    
	public Person() {
		
	}
	
	

	public Person(Long id, String name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
	}



	public Person(String name, String address) {
		super();
		this.id = setId();
		this.name = name;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public Long setId() {
		PersonGrid personGrid = new PersonGrid();
		Long id = personGrid.persons.stream().mapToLong(s -> s.getId()).max().orElse(0L);
		return 1L + id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

   

}