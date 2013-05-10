package tests.br.ufal.ic.tips.core.models;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.ufal.tips.core.exceptions.CannotCreateInstanceException;
import br.ufal.tips.core.exceptions.InstanceNotFoundException;
import br.ufal.tips.core.models.support.Support;
import br.ufal.tips.core.models.support.SupportFactory;

public class SupportTest {
	
	private final static String supportId="supportIdTest1";
	private final static String supportLabel="TestSupportLabel";
	private final static String supportLink="SupportLink";
	
	@BeforeClass
	public static void setUp(){
		System.out.println("SETUP");
		try {
			SupportFactory.getInstance().create(supportId, supportLabel, supportLink);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@AfterClass
	public static void tearDown(){
		SupportFactory.getInstance().delete(supportId);
		SupportFactory.getInstance().close();
		
	}
	
	
	@Test
	public void testDuplicatedSupport(){
		boolean gotException = false;
		try {
			@SuppressWarnings("unused")
			Support dummieSupport = SupportFactory.getInstance().create(supportId, supportLabel, supportLink);
		} catch (CannotCreateInstanceException e) {
			//We should not create a duplicated Support.
			//This block should be executed.
			gotException = true;
		}
		
		assertTrue("We have created a duplicated Topic.", gotException);
	}
	
	@Test
	public void testSupportRetrieval(){
		try {
			@SuppressWarnings("unused")
			Support dummieSupport = SupportFactory.getInstance().retrieve(supportId);
		} catch (InstanceNotFoundException e) {
			fail("We cannot find this instance.");
		}
	}
	
	@Test
	public void testChangeLabel(){
		
		try {
			Support dummieSupport = SupportFactory.getInstance().retrieve(supportId);
			
			dummieSupport.setLabel("ModifiedSupportLabel");
			
			assertEquals("ModifiedSupportLabel", dummieSupport.getLabel());
			
		} catch (InstanceNotFoundException e) {
			fail();
		}	
	}
	
	@Test
	public void testChangeLink(){
		try {
			Support dummieSupport = SupportFactory.getInstance().retrieve(supportId);
			
			dummieSupport.setLink("ModifiedSupportLink");
			
			assertEquals("ModifiedSupportLink", dummieSupport.getLink());
			
		} catch (InstanceNotFoundException e) {
			fail();
		}
		
	}
	
	@Test
	public void testToString(){
		
		try {
			Support dummieSupport = SupportFactory.getInstance().retrieve(supportId);
			
			System.out.println(dummieSupport);
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		boolean gotException = false;
		
		try {
			Support dummieSupport = SupportFactory.getInstance().create("Sup2", supportLabel, supportLink);
			
			dummieSupport.delete();
			
			@SuppressWarnings("unused")
			Support deletedSupport = SupportFactory.getInstance().retrieve("Sup2");
			
		} catch (CannotCreateInstanceException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		} catch (InstanceNotFoundException e) {
			gotException = true;
		}
		assertTrue("We have retrieved a deleted Topic",gotException);
	}
}
