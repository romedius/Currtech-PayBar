package at.ac.uibk.paybar;

public enum Configuration {
	BankName("HyperBank"),
	BankPosName("hb-tw1");

	private String v;

	@Override
	public String toString() {
		return v;
	}

	private Configuration(String s) {
		this.v = s;
	}
}
