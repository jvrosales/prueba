package ec.gob.ambiente.common;

public class JPAResponse {
	
	private boolean successful = true;
	private Exception exception = null;
	
	public JPAResponse(){
		
	}
		
	public boolean isSuccessful() {
		return successful;
	}

	public void setSuccessful(boolean successful) {
		this.successful = successful;
	}

	public Exception getException() {
		return exception;
	}
	
	public void setException(Exception exception) {
		this.exception = exception;
	}
}