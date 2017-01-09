package com.zs.model;

public class Users {
	private int id;
	private String name;
	
	public Users(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	
	public int getId() {
		return id;
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


	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj!=null) {
			Users u=(Users) obj;
			if (u.getId()==id && u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
}
