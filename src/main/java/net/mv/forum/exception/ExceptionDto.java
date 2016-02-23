package net.mv.forum.exception;

public class ExceptionDto extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3620731518963031381L;

	public ExceptionDto() {
	}

	public ExceptionDto(String message, Throwable t) {
		super(message, t);
	}

}
