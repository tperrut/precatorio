package br.com.precatorio.system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URISyntaxException;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiViaCepException extends Exception  {

	private static final long serialVersionUID = 1L;

	public ApiViaCepException(Exception e, String msg) {
		super(msg, e);
	}
}
