package com.hebut.triptogether.Model;

public class person {
	private int id;
	private String name;
	public person(int id, String name, String adress) {
		super();
		this.id = id;
		this.name = name;
		this.adress = adress;
	}



	public int getId() {
		return id;
	}



	@Override
	public String toString() {
		return "person [id=" + id + ", name=" + name + ", adress=" + adress
				+ "]";
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAdress() {
		return adress;
	}



	public void setAdress(String adress) {
		this.adress = adress;
	}



	private String adress;
	
	
	
public  person() {
	
	
	
}
}
