package com.intesigroup.testcasefactory.entityView;

import java.util.ArrayList;
import java.util.List;

public class ProjectViewAttribute {
	List<ProjectViewElementList> listElement;
	String titlePageLabel;
	String titleLabel;
	String nameLabel;
	String descriptionLabel;
	String codeLabel;
	String nameProgramLabel;
	public List<ProjectViewElementList> getListElement() {
		return listElement;
	}
	public void setListElement(List<ProjectViewElementList> listElement) {
		this.listElement = listElement;
	}

	public ProjectViewAttribute() {
		listElement= new ArrayList<ProjectViewElementList>();
		titlePageLabel="";
		titleLabel="";
		nameLabel="";
		descriptionLabel="";
		codeLabel="";
		nameProgramLabel="";
	}
	public void addElementList(ProjectViewElementList element) {
		listElement.add(element);
	}
	
	public void setElement(List<ProjectViewElementList> element) {
		this.listElement = element;
	}
	public String getTitlePageLabel() {
		return titlePageLabel;
	}
	public void setTitlePageLable(String tilePageLable) {
		this.titlePageLabel = tilePageLable;
	}
	public String getTitleLabel() {
		return titleLabel;
	}
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}
	public String getNameLabel() {
		return nameLabel;
	}
	public void setNameLable(String nameLable) {
		this.nameLabel = nameLable;
	}
	public String getDescriptionLabel() {
		return descriptionLabel;
	}
	public void setDescriptionLabel(String descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}
	public String getCodeLabel() {
		return codeLabel;
	}
	public void setCodeLabel(String codeLabel) {
		this.codeLabel = codeLabel;
	}
	public String getNameProgramLabel() {
		return nameProgramLabel;
	}
	public void setNameProgramLabel(String nameLabel) {
		this.nameProgramLabel = nameLabel;
	}
}
