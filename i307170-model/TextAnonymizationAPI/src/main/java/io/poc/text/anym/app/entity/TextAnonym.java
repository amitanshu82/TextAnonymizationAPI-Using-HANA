package io.poc.text.anym.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(schema = "DLP", name = "$T_TEXT_ANONYM")
public class TextAnonym {

	@Id
	long id;
	@Column(name="TA_TOKEN")
	String ta_token;
	@Column(name="TA_TYPE")
	String ta_type;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTa_token() {
		return ta_token;
	}
	public void setTa_token(String ta_token) {
		this.ta_token = ta_token;
	}
	public String getTa_type() {
		return ta_type;
	}
	public void setTa_type(String ta_type) {
		this.ta_type = ta_type;
	}
}
