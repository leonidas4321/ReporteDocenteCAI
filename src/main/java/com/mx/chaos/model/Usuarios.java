package com.mx.chaos.model;

public class Usuarios {
	
	
	private Integer userId;
	private Integer empNo;
	private String empLastName;
	private String empFirstName;
	private String fullName;
	private String nameDepartament;

    
    public Usuarios() {}


	public Usuarios(Integer userId, Integer empNo, String empLastName, String empFirstName,String fullName, String nameDepartament) {
		this.userId = userId;
		this.empNo = empNo;
		this.empLastName = empLastName;
		this.empFirstName = empFirstName;
		this.fullName = fullName;
		this.nameDepartament = nameDepartament;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public Integer getEmpNo() {
		return empNo;
	}


	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}


	public String getEmpLastName() {
		return empLastName;
	}


	public void setEmpLastName(String empLastName) {
		this.empLastName = empLastName;
	}


	public String getEmpFirstName() {
		return empFirstName;
	}


	public void setEmpFirstName(String empFirstName) {
		this.empFirstName = empFirstName;
	}

	

	public String getFullName() {
		return fullName;
	}


	public void setFullName(String fullName) {
		this.fullName = fullName;
	}


	public String getNameDepartament() {
		return nameDepartament;
	}


	public void setNameDepartament(String nameDepartament) {
		this.nameDepartament = nameDepartament;
	} 
	
	@Override
    public String toString(){
        
        return getFullName();
    }
    
}
