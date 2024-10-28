package ec.gob.ambiente.vo;

public class PaymentInVO {
	
	   private String bankEntityCode;

	   private String transactionNumber;
	   
	   

	public PaymentInVO() {
		
	}

	public String getBankEntityCode() {
		return bankEntityCode;
	}

	public void setBankEntityCode(String bankEntityCode) {
		this.bankEntityCode = bankEntityCode;
	}

	public String getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

}
