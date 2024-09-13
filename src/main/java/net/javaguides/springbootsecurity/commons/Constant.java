package net.javaguides.springbootsecurity.commons;

public enum Constant {
	
	Id_Proof("idProof"), 
	Fg_Adhar("fgAdhar"),
	Fg_Pan("fgPan"),  
	Fg_Cancelled_Cheque("fgCancelledCheque"),  
	Sg_Adhar("sgAdhar"),
	Sg_Pan("sgPan"),  
	Sg_Cancelled_Cheque("sgCancelledCheque"),
    APPROVED("APPROVED"),
    PENDING("PENDING"),
    Daily("Daily"),
    Monthly("Monthly"),
    REJECT("REJECT");
 
    private String value;
 
    Constant(String value) {
        this.value = value;
    }

	public String getValue() {
		return value;
	}

}
