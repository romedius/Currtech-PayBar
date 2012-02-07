package paybar.data;

import javax.ejb.Stateless;

import paybar.model.Coupon;
import paybar.model.DetailAccount;
import paybar.model.Partner;
import paybar.model.PointOfSale;
import paybar.model.Transaction;
import paybar.wsmodel.WsCoupon;
import paybar.wsmodel.WsDetailAccount;
import paybar.wsmodel.WsPartner;
import paybar.wsmodel.WsPointOfSale;
import paybar.wsmodel.WsTransaction;

@Stateless
public class ModelToWebserviceTransformer {

	//@PersistenceContext(name = "primary")
	//private EntityManager em;
	
	public WsCoupon couponToWsCoupon(Coupon c){
		return new WsCoupon(c);
	}

	public WsDetailAccount detailAccountToWsDetailAccount(DetailAccount da){
		return new WsDetailAccount(da);
	}

	public WsPartner partnerToWsPartner(Partner p){
		return new WsPartner(p);
	}

	public WsPointOfSale pointOfSaleToWsPointOfSale(PointOfSale p){
		return new WsPointOfSale(p);
	}

	public WsTransaction transactionToWsTransaction(Transaction t){
		return new WsTransaction(t);
	}

}
