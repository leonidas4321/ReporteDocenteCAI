package com.mx.chaos.model;


public class Asistencia {
	
	private Integer userId;
	private String punch_time;
	private Integer terminalId;
	
	
	public Asistencia() {
	}


	public Asistencia(Integer userId, String punch_time, Integer terminalId) {
		this.userId = userId;
		this.punch_time = punch_time;
		this.terminalId = terminalId;
	}


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getPunch_time() {
		return punch_time;
	}


	public void setPunch_time(String punch_time) {
		this.punch_time = punch_time;
	}


	public Integer getTerminalId() {
		return terminalId;
	}


	public void setTerminalId(Integer terminalId) {
		this.terminalId = terminalId;
	}
	
	
	
}
