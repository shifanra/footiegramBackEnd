package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")

public class Profile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "work")
	private String work;
	
	@Column(name = "from")
	private String from;
	
	@Column(name = "live")
	private String live;
	
	@Column(name = "status")
	private String status;
	
    public Profile() {
		
	}
    
    public Profile(String work, String from, String live, String status) {
		super();
		this.work = work;
		this.from = from;
		this.live = live;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getwork() {
		return work;
	}
	public void setwork(String work) {
		this.work = work;
	}
	public String getfrom() {
		return from;
	}
	public void setfrom(String from) {
		this.from = from;
	}
	public String getlive() {
		return live;
	}
	public void setlive(String live) {
		this.live = live;
	}

	public String getstatus() {
		return status;
	}
	public void setstatus(String status) {
		this.status = status;
	}


}
