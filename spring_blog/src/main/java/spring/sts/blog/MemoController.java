package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.memo.MemoDTO;
import spring.model.memo.MemoService;
import spring.model.IReplyDAO;
import spring.model.memo.MReplyDAO;
import spring.model.memo.MReplyDTO;
import spring.model.memo.MemoDAO;
import spring.utility.blog.Utility;

@Controller
public class MemoController {
	@Autowired
	private MemoDAO dao;
	
	@Autowired
	private MReplyDAO mdao;
	
	@Autowired
	private MemoService service;
	
	@RequestMapping("/memo/rdelete")
	public String rdelete(int rnum,int memono, int nowPage,int nPage, String col, String word,Model model){ 
	 
	int total = mdao.total(memono);//댓글전체레코드값을 가져와서  
	int totalPage = (int)(Math.ceil((double)total/3)); // 전체 페이지  
	if(mdao.delete(rnum)){
	if(nPage!=1&&nPage==totalPage&&total%3==1){//마지막페이지의 마지막레코드이면(3은 한페이지당보여줄 레코드 갯수)
	nPage=nPage-1;  //현재의 페이지값에서 1을 빼자 
	}
	model.addAttribute("memono", memono);
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	 
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	 
	@RequestMapping("/memo/rupdate")
	public String rupdate(MReplyDTO dto,int nowPage,int nPage, String col, String word,Model model){
	if(mdao.update(dto)){
	model.addAttribute("memono", dto.getMemono());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("nPage", nPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	@RequestMapping("/memo/rcreate")
	public String rcreate(MReplyDTO dto,int nowPage,String col, String word,Model model){
	 
	if(mdao.create(dto)){
	model.addAttribute("memono", dto.getMemono());
	model.addAttribute("nowPage", nowPage);
	model.addAttribute("col", col);
	model.addAttribute("word", word);
	}else{
	return "error/error";
	}
	 
	return "redirect:./read";
	}
	
	
	@RequestMapping("/memo/delete")
	public String delete(int memono, String col, String nowPage, String word, Model model){		
		String url = "";
		try {
			service.delete(memono);
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			url = "redirect:./list";
		} catch (Exception e) {
			e.printStackTrace();
			url = "error";
		}
		return url;
	}

	@RequestMapping(value="/memo/update", method=RequestMethod.POST)
	public String update(MemoDTO dto, String col, String word, String nowPage, Model model, HttpServletRequest request){
		
		boolean flag = dao.update(dto);
		if(flag){
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			
			return "redirect:./list";
			
		}else{
			return "error";
		}
		
	}
	
	@RequestMapping(value="/memo/update", method=RequestMethod.GET)
	public String update(int memono, Model model){
		
		model.addAttribute("dto", dao.read(memono));
		
		return "/memo/update";
	}
	
	@RequestMapping("/memo/read")
	public String read(int memono, HttpServletRequest request, Model model, int nowPage, String col, String word){
	
		/*조회수 증가*/
		dao.upViewcnt(memono);
		/*조회수 증가 끝 */
		
		/*조회 */
		MemoDTO dto = dao.read(memono);
		/*조회 끝*/

			String content = dto.getContent();
			content = content.replaceAll("\r\n", "<br>");
	
		
		request.setAttribute("dto", dto);
		request.setAttribute("content", content);
		
		
		//model.addAttribute("dto", dao.read(memono));
		
		/* 댓글 관련  시작 */
		String url = "read";
		String no = "memono";
		int nPage= 1; //시작 페이지 번호는 1부터 
		 
		if (request.getParameter("nPage") != null) { 
		nPage= Integer.parseInt(request.getParameter("nPage"));  
		}
		int recordPerPage = 3; // 한페이지당 출력할 레코드 갯수
		 
		int sno = ((nPage-1) * recordPerPage) + 1; // 
		int eno = nPage * recordPerPage;
		 
		Map map = new HashMap();
		map.put("sno", sno);
		map.put("eno", eno);
		map.put("memono", memono);
		 
		List<MReplyDTO> list = mdao.list(map);
		 
		int total = mdao.total(memono);
		 
		String paging = Utility.paging(total, nPage, recordPerPage, url, no, memono, nowPage, col, word);
		 
		model.addAttribute("rlist",list);
		model.addAttribute("paging",paging);
		model.addAttribute("nPage",nPage);
		 
		/* 댓글 관련 끝 */ 
		
		return "/memo/read";
	}
	
	@RequestMapping(value="/memo/create", method=RequestMethod.POST)
	public String create(MemoDTO dto){
		if(dao.create(dto)){
			return "redirect:./list";
		}else{
			return "error";
		}
	}
	
	@RequestMapping(value="/memo/create", method=RequestMethod.GET)
	public String create(){
		return "/memo/create";
	}
	
	
	
	@RequestMapping("/memo/list")
	public String list(HttpServletRequest request, Model model){
		
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		
		if(col.equals("total")){
			word="";
		}
		//페이지관련-------------------------
		int nowPage = 1; //현재페이지(변경가능해야함)
		if(request.getParameter("nowPage")!=null){//널이 아닌때 보내줬다 
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		
		
		int recordPerPage = 5; //한페이지당 보여줄 레코드 갯수
		
		//DB에서 읽어올 시작 순번(sno)과 끝순번(eno) 생성
		int sno = ((nowPage-1)*recordPerPage) +1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		//----------------------------------
		List<MemoDTO> list = dao.list(map);// DAO에서 가지고 온 데이터를 뿌려준다.
		int total = dao.total(col,word);
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		model.addAttribute("list", list);
		model.addAttribute("paging", paging);
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		IReplyDAO irdao = mdao;
		model.addAttribute("irdao", irdao);
		
		
		
		
		return "/memo/list";
	}
	
}
