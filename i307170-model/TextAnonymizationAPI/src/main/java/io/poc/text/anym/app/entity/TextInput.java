package io.poc.text.anym.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "DLP", name = "TestHana.HDBModule::inputTable")
public class TextInput {

	@Id
	long id;
	@Column(name="STRING")
	String text;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}

