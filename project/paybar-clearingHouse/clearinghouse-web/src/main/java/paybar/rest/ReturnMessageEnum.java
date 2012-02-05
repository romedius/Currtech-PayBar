package paybar.rest;

public enum ReturnMessageEnum {

	SUCCESS("success"),FAILURE("failure"),ERROR("error");

    private final String key;

    ReturnMessageEnum(String key) {
        this.key = key;
    }
    
    public String key()   { 
    	return this.key; 
    }

}
