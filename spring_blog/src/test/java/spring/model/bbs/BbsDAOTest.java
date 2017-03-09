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
import org.springframework.dao.support.DaoSupport;

public class BbsDAOTest {
	
	private static BeanFactory beans;
	private static BbsDAO bdao;
	
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
		
		bdao = (BbsDAO)beans.getBean("bdao");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test @Ignore
	public void testCheckRefno() {
		int bbsno = 90;
		assertTrue(bdao.checkRefno(bbsno));
//		assertTrue(bdao.checkRefno(90));
		
	}

	@Test @Ignore
	public void testTotal() {
		assertEquals(bdao.total("wname", "123442"),1); //전체 토탈 중 wname 한개 인것
		assertEquals(bdao.total("", ""),46); //전체 토탈 갯수
		
	}

	@Test @Ignore
	public void testUpAnsnum() {
		BbsDTO dto = bdao.readReply(89);
		dto.setWname("답변자등록");
		dto.setContent("답변내용");
		dto.setPasswd("1234");
		dto.setFilename("text.txt");
		dto.setFilesize(150);
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		bdao.upAnsnum(map);
		
		assertTrue(bdao.createReply(dto));
	}

	@Test @Ignore
	public void testCreateReply() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testReadReply() {
		BbsDTO dto = bdao.readReply(54);
		assertEquals(dto.getGrpno(), 53);
		assertEquals(dto.getIndent(), 1);
		assertEquals(dto.getAnsnum(), 2);
		assertEquals(dto.getTitle(), "방화벽");
		
	}

	@Test @Ignore
	public void testDelete() {
		assertTrue(bdao.delete(90));
	}

	@Test @Ignore
	public void testUpdate() {
		BbsDTO dto =new BbsDTO();
		dto.setBbsno(90);
		dto.setWname("홍길동");
		dto.setTitle("제목변경");
		dto.setContent("내용 변경");
		dto.setFilename("text02.txt");
		dto.setFilesize(150);
		
		assertTrue(bdao.update(dto));
		
	}

	@Test @Ignore
	public void testPassCheck() {
		Map map = new HashMap();
		map.put("bbsno", 90);
		map.put("passwd", 1234);
		
		assertTrue(bdao.passCheck(map));
	}

	@Test
	public void testRead() {
		bdao.upViewcnt(64);
		BbsDTO dto = bdao.read(90);
		assertNotNull(dto);
	}

	@Test @Ignore
	public void testUpViewcnt() {
		fail("Not yet implemented");
	}

	@Test @Ignore
	public void testList() {
		
		Map map = new HashMap();
		map.put("col","wname");
		map.put("word","123442");
		map.put("sno","1");
		map.put("eno","5");
		List<BbsDTO> list = bdao.list(map);
		assertEquals(list.size(),1);
	}

	@Test @Ignore
	public void testCreate() {
		BbsDTO dto = new BbsDTO();
		dto.setWname("홍길동");
		dto.setTitle("안녕하세요");
		dto.setContent("내용입니다.");
		dto.setPasswd("1234");
		dto.setFilename("text.txt");
		dto.setFilesize(100);
		
		
		assertTrue(bdao.create(dto));
		
	}

}
