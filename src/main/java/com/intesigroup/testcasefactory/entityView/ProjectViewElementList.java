package com.intesigroup.testcasefactory.entityView;

public class ProjectViewElementList {
	
	Long id;
	String name;
	String description;
	String code;
	Long idFather;
	public Long getIdFather() {
		return idFather;
	}
	public void setIdFather(Long idFather) {
		this.idFather = idFather;
	}
	public ProjectViewElementList(Long idFather, Long id, String name, String description, String code) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.code = code;
		this.idFather=idFather;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
