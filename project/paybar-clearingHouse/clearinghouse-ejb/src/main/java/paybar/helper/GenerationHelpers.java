package paybar.helper;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class GenerationHelpers implements Serializable {

	private static final long serialVersionUID = -7213673465118041882L;

	private java.util.Random random = new java.util.Random(
			System.currentTimeMillis());

	java.util.Random getRandom() {
		return random;
	}

	@Produces
	int next() {
		return getRandom().nextInt();
	}

	@Produces
	 public String getNextSecurityKey() {
		return getRandom().nextInt() + "-" + getRandom().nextInt();
	}

}