package tests.br.ufal.ic.tips.core.models;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.ufal.tips.core.exceptions.CannotCreateInstanceException;
import br.ufal.tips.core.exceptions.InstanceNotFoundException;
import br.ufal.tips.core.models.topic.Topic;
import br.ufal.tips.core.models.topic.TopicFactory;

@RunWith(JUnit4.class)
public class TopicTest{
	
	private final static String topicId="IdTest1";
	private final static String topicLabel="TestTopicLabel";
	
	@BeforeClass
	public static void setUp(){
		System.out.println("SETUP");
		try {
			TopicFactory.getInstance().create(topicId, topicLabel);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	@AfterClass
	public static void tearDown(){
		TopicFactory.getInstance().delete(topicId);
		TopicFactory.getInstance().delete("IdTest2");
		TopicFactory.getInstance().close();
	}
	
	@Test
	public void testDuplicateCreation(){
		boolean gotException = false;
		try {
			@SuppressWarnings("unused")
			Topic footopic = TopicFactory.getInstance().create(topicId, topicLabel);
		} catch (CannotCreateInstanceException e) {
			gotException = true;
		}
		assertTrue("We have created a duplicated Topic.", gotException);
	}
	
	@Test
	public void testRetrieve(){

		try{
		
			@SuppressWarnings("unused")
			Topic fooTopic = TopicFactory.getInstance().retrieve(topicId);
		
		} catch (InstanceNotFoundException e) {
			fail("We cannot find this instance.");
		}

	}
	
	@Test
	public void testLabel(){

		try{
		
			Topic fooTopic = TopicFactory.getInstance().retrieve(topicId);
			
			fooTopic.setLabel("ModifiedTestTopicLabel");
			
			assertEquals("ModifiedTestTopicLabel",fooTopic.getLabel());
		
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
		
	@Test
	public void testProblems(){
		
		try{
			Topic fooTopic = TopicFactory.getInstance().retrieve("IdTest1");
			
			Set<String> problems = new HashSet<>();
			problems.add("Problem1");
			problems.add("Problem2");
			
			fooTopic.setProblems(problems);
			
			assertEquals(problems, fooTopic.getProblems());
			
			problems.add("Problem3");
			
			fooTopic.addProblem("Problem3");
			
			assertEquals(problems, fooTopic.getProblems());
			
			fooTopic.removeProblem("Problem2");
			problems.remove("Problem2");
			
			assertEquals(problems, fooTopic.getProblems());			
		
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSupports(){
		
		try{
			Topic fooTopic = TopicFactory.getInstance().retrieve("IdTest1");
			
			Set<String> supports = new HashSet<>();
			supports.add("Support1");
			supports.add("Support2");
			
			fooTopic.setSupports(supports);
			
			assertEquals(supports, fooTopic.getSupports());
			
			supports.add("Support3");
			
			fooTopic.addSupport("Support3");
			
			assertEquals(supports, fooTopic.getSupports());
			
			fooTopic.removeSupport("Support1");
			supports.remove("Support1");
			
			assertEquals(supports, fooTopic.getSupports());
		
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParents(){
		try{
			Topic fooTopic = TopicFactory.getInstance().retrieve("IdTest1");
			
			Set<String> parents = new HashSet<>();
			parents.add("Parent1");
			parents.add("Parent2");
			
			fooTopic.setParents(parents);
			
			assertEquals(parents, fooTopic.getParents());
			
			parents.add("Parent3");
			
			fooTopic.addParents("Parent3");
			
			assertEquals(parents, fooTopic.getParents());
			
			fooTopic.removeParents("Parent1");
			parents.remove("Parent1");
			
			assertEquals(parents, fooTopic.getParents());
		
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	@Test
	public void testNext(){
		try{
		
			Topic fooTopic = TopicFactory.getInstance().retrieve("IdTest1");
			
			fooTopic.setNext("NextTopic");
			
			assertEquals("NextTopic",fooTopic.getNext());
		
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToString(){
		try{
			Topic fooTopic = TopicFactory.getInstance().retrieve("IdTest1");
			
			System.out.println(fooTopic);
		} catch (InstanceNotFoundException e) {
			// TODO Bloco catch gerado automaticamente
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDelete(){
		boolean gotException = false;
		try{
			Topic fooTopic = TopicFactory.getInstance().create("IdTest2","TestLabel2");
			
			fooTopic.delete();
			
			@SuppressWarnings("unused")
			Topic deletedTopic = TopicFactory.getInstance().retrieve("IdTest2");
			
			System.out.println("Topic after deletion: "+fooTopic);
		} catch (InstanceNotFoundException e) {
			gotException=true;
		} catch (CannotCreateInstanceException e) {
			e.printStackTrace();
		}
		assertTrue("We have retrieved a deleted Topic",gotException);
	}

}
