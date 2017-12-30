package com.guru.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the excelsheetinfo database table.
 * 
 */
@Entity
@Table(name="excelsheetinfo")
@NamedQuery(name="Excelsheetinfo.findAll", query="SELECT e FROM Excelsheetinfo e")
public class Excelsheetinfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer excelsheet_Id;

	private Integer age;

	private String company;

	private Double salary;

	private String username;

	public Excelsheetinfo() {
	}

	public Integer getExcelsheet_Id() {
		return this.excelsheet_Id;
	}

	public void setExcelsheet_Id(Integer excelsheet_Id) {
		this.excelsheet_Id = excelsheet_Id;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Double getSalary() {
		return this.salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}