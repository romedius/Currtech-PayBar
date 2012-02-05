package paybar.rest;

public class ReturnMessage {
	
	private String success;
	
	private String Message;
	
	public ReturnMessage(ReturnMessageEnum renum, String message) {
		this.setSuccess(renum.toString());
		this.setMessage(message);
	}

	public String isSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

}
