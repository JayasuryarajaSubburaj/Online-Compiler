package com.onlinecompiler.entity;

public class ResponseEntity {

	private String msg;
	private String output;

	public ResponseEntity(String msg, String output) {
		this.msg = msg;
		this.output = output;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

}
