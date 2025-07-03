package com.testapps.payroll.dtos;

public class EmployeeDto {

	private Long id;
	private String fullName;
	private String role;

	public EmployeeDto(Long id, String name, String role) {
		super();
		this.id = id;
		this.fullName = name;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String name) {
		this.fullName = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
