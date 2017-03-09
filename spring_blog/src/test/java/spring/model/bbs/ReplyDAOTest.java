package spring.model.bbs;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class ReplyDAOTest {
	
	private static BeanFactory beans;
	private static ReplyDAO dao;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Resource resource = new ClassPathResource("blog.xml");
		beans = new XmlBeanFactory(resource);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		dao = (ReplyDAO)beans.getBean("rdao");
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testRcount() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testCreate() {
		ReplyDTO dto = new ReplyDTO();
		dto.setBbsno(58);
		dto.setContent("댓글111");
		dto.setId("user2");
		assertTrue(dao.create(dto));
	}

	@Test @Ignore
	public void testRead() {
		ReplyDTO dto = dao.read(3);
		assertNotNull(dto);
//		assertEquals(dto.getBbsno(), 58);
//		assertEquals(dto.getId(), "user2");
//		assertEquals(dto.getContent(), "댓글1");
	}

	@Test @Ignore
	public void testUpdate() {
		ReplyDTO dto = dto = new ReplyDTO();
		dto.setBbsno(58);
		dto.setContent("댓글2로 변경");
		dto.setId("user2");
		dto.setRnum(3);
		assertTrue(dao.update(dto));
	}

	@Test @Ignore
	public void testList() {
		Map map = new HashMap();
		map.put("bbsno", 58);
		map.put("sno", 1);
		map.put("eno", 5);
		
		List<ReplyDTO> list = dao.list(map);
		assertEquals(list.size(), 5);
		
	}

	@Test @Ignore
	public void testTotal() {
		int bbsno = 2;
		int cnt = dao.total(bbsno);
		assertEquals(cnt, 1);
	}

	@Test @Ignore
	public void testDelete() {
		assertTrue(dao.delete(3));
	}

	@Test
	public void testBdelete() throws Exception {
		int bbsno = 37;
		assertEquals(dao.bdelete(bbsno), 4);
	}

}
