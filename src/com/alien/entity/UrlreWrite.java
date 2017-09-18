package com.alien.entity;

import java.io.Serializable;
/**
 * 设置重定向xml
 * @author alien
 *
 */
public class UrlreWrite implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4112218593697487411L;

	private String[] note;
	
	private String[] from;
	
	private String[] to;

	public String[] getNote() {
		return note;
	}

	public void setNote(String[] note) {
		this.note = note;
	}

	public String[] getFrom() {
		return from;
	}

	public void setFrom(String[] from) {
		this.from = from;
	}

	public String[] getTo() {
		return to;
	}

	public void setTo(String[] to) {
		this.to = to;
	}

}
