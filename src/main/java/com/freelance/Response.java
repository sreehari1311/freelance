package com.freelance;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.freelance.util.Freelance;

@JsonInclude(Include.NON_NULL)
public class Response<T> {

	private T response;
	private String status;
	private String message;
	public Response() {

	}
	public Response(String status,String message) {
		this.status = status;
		this.message = message;
	}
	public Response(T response) {
		this.response = response;
		this.setStatus(Freelance.SUCCESS);
	}
 
	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
