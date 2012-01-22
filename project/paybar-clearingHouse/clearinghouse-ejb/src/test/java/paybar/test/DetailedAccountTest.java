package paybar.test;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import paybar.data.AccountResource;
import paybar.data.DetailAccountResource;
import paybar.model.Account;
import paybar.model.DetailAccount;
import paybar.util.Resources;

@RunWith(Arquillian.class)
public class DetailedAccountTest {
   @Deployment
   public static Archive<?> createTestArchive() {
      return ShrinkWrap.create(WebArchive.class, "test.war")
            .addClasses(DetailAccount.class, DetailAccountResource.class, Resources.class, Account.class, AccountResource.class)
            .addAsResource("META-INF/persistence.xml", "META-INF/persistence.xml")
            .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
   }


   @Inject
   DetailAccountResource detailAccountResource;

   @Inject
   AccountResource accountResource;

   @Inject
   Logger log;

   @Test
   public void testCreate() throws Exception {
	   Account account = new Account();//...
//	   
//      Member newMember = memberRegistration.getNewMember();
//      newMember.setName("Jane Doe");
//      newMember.setEmail("jane@mailinator.com");
//      newMember.setPhoneNumber("2125551234");
//      memberRegistration.register();
//      assertNotNull(newMember.getId());
//      log.info(newMember.getName() + " was persisted with id " + newMember.getId());
   }
   
}
