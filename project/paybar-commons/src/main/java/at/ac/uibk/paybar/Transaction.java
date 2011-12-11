package at.ac.uibk.paybar;

public class Transaction {
    
    String id;
    
    String locationHash;
    
    long transactionTime;
    
    float ammount;

    public String getId() {
        return id;
    }

    public String getLocationHash() {
        return locationHash;
    }

    public long getTransactionTime() {
        return transactionTime;
    }

    public float getAmmount() {
        return ammount;
    }
    

}
