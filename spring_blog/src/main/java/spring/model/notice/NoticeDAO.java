package spring.model.notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class NoticeDAO {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	
	public void setSqlSession(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}

//	public boolean checkRefno(int bbsno){
//		
//		boolean flag = false;
//		
//		int cnt = sqlSession.selectOne("bbs.checkRefno", bbsno);
//		if(cnt>0)flag = true;
//		return flag;
//	}
	
	public int ntotal(String col, String word){
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		
		
		return sqlSession.selectOne("notice.total",map);
	}
	
//	public void upAnsnum(Map map){
//		
//		sqlSession.selectOne("bbs.upAnsnum", map);
//		
//	}
	
	
//	public boolean createReply(NoticeDTO dto){
//		boolean flag = false;
//		int cnt = sqlSession.insert("notice.createReply", dto);
//		if(cnt>0) flag = true;
//		return flag;
//	}
//	
	
//	public NoticeDTO readReply(int no){
//		
//		return sqlSession.selectOne("bbs.readReply", no);
//	}
//	
	public boolean ndelete(int noticeid){
		boolean flag = false;
		
		int cnt = sqlSession.delete("notice.delete", noticeid);
		if(cnt>0)flag=true;
		
		return flag;
	}
	
	public boolean nupdate(NoticeDTO dto){
		boolean flag = false;
		
		int cnt = sqlSession.update("notice.update", dto);
		if(cnt>0)flag=true;
		return flag;
		
	}
	
	public boolean passCheck(Map map){
		boolean flag = false;
		
		int cnt = sqlSession.selectOne("notice.passCheck", map);
		if(cnt>0)flag = true;
		return flag;
	}
	
	public NoticeDTO nread(int noticeid){
		
		return sqlSession.selectOne("notice.read", noticeid);
	}
	
	public void upViewcnt(int noticeid){
		sqlSession.update("notice.upViewcnt", noticeid);
	}
	
	public List<NoticeDTO> nlist(Map map){
		
		
		return sqlSession.selectList("notice.list", map);
		
	}
	
	
	
		public boolean ncreate(NoticeDTO dto){
			boolean flag = false;
			int cnt = sqlSession.insert("notice.create", dto);
			if(cnt>0)flag=true;
			return flag;
		}
}
