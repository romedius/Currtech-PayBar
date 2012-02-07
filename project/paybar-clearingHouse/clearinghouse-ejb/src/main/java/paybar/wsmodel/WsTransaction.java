package paybar.wsmodel;

import java.io.Serializable;

import paybar.model.Transaction;

public class WsTransaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private double amount;

	private long transactionTime;

	private Long couponId;

	private Long posId;

	private Long detailAccountId;

	public WsTransaction() {
	}

	public WsTransaction(Transaction t) {
		this.id = t.getId();
		this.amount = t.getAmount();
		this.couponId = t.getCoupon().getId();
		this.detailAccountId = t.getDetailAccount().getId();
		this.posId = t.getPos().getId();
		this.transactionTime = t.getTransactionTime();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Long getCouponId() {
		return couponId;
	}

	public void setCouponId(Long couponId) {
		this.couponId = couponId;
	}

	public Long getPosId() {
		return posId;
	}

	public void setPosId(Long posId) {
		this.posId = posId;
	}

	public Long getDetailAccountId() {
		return detailAccountId;
	}

	public void setDetailAccountId(Long detailAccountId) {
		this.detailAccountId = detailAccountId;
	}

}
