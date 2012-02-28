package at.ac.uibk.paybar.model;

import java.util.ArrayList;

public class TransferAccount extends FastAccount {

	private static final long serialVersionUID = 1L;

	private ArrayList<FastCoupon> coupons;

	public ArrayList<FastCoupon> getCoupons() {
		return coupons;
	}

	public void setCoupons(ArrayList<FastCoupon> coupons) {
		this.coupons = coupons;
	}

}
