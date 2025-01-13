package com.emp.managment.crude.dto;



public class EmployeeDto implements Comparable<EmployeeDto> {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public EmployeeDto() {
    }

    public EmployeeDto(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public int compareTo(EmployeeDto employeeDto) {
        // Compare based on age
        return Integer.compare(Math.toIntExact(this.id), Math.toIntExact(employeeDto.id));
    }
}
