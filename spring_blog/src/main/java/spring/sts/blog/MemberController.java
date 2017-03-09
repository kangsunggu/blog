package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import spring.model.member.MemberDTO;
import spring.model.member.MemberDAO;
import spring.utility.blog.Utility;

@Controller
public class MemberController {
	
	@Autowired
	private MemberDAO dao;
	
	@RequestMapping(value="/member/delete", method=RequestMethod.POST)
	public String delete(String id, String oldfile, HttpServletRequest request, HttpSession session ){
		
		String basePath = request.getRealPath("/member/storage");
		if(dao.delete(id)){
			if(oldfile!=null && !oldfile.equals("member.png"))
			Utility.deleteFile(basePath, oldfile);
			session.invalidate();
			return "redirect:../";
		}else{
			return "error";
		}
	}
	
	
	
	@RequestMapping(value="/member/delete", method=RequestMethod.GET)
	public String delete(Model model, HttpSession session ){
		
		String id = (String)session.getAttribute("id");
		String oldfile = dao.getFname(id);
		
		model.addAttribute("id", id);
		model.addAttribute("oldfile", oldfile);
		
		
		return "/member/delete";
	}
	
	@RequestMapping(value="/member/updatePw", method=RequestMethod.POST)
	public String updatePw(String id, String passwd){
		
		if(dao.updatePw(id, passwd)){
			return "redirect:./read";
		}else{
			
			return "error";
		}
		
	}
	
	@RequestMapping(value="/member/updatePw", method=RequestMethod.GET)
	public String updatePw(){
		return "/member/updatePw";
	}
	
	@RequestMapping(value="/member/updateFile", method=RequestMethod.POST)
	public String updateFile(HttpServletRequest request, String id, String oldfile, MultipartFile fnameMF){
		
		String basePath = request.getRealPath("/member/storage");
		
		int filesize = (int)fnameMF.getSize();
		String fname ="";
			if(filesize>0){
				fname=Utility.saveFile(fnameMF, basePath);
			}else{
				fname = "member.png"; 
			}
		if(dao.updateFile(id, fname)){
			if(oldfile!=null && !oldfile.equals("member.png")){
			Utility.deleteFile(basePath, oldfile);
			}
			return "redirect:./read";
		}else{
			return "error";
		}
		
	}
	
	@RequestMapping(value="/member/updateFile", method=RequestMethod.GET)
	public String updateFile(){
		
		return "/member/updateFile";
	}
	
	@RequestMapping(value="/member/update",method=RequestMethod.POST)
	public String update(MemberDTO dto,HttpSession session, HttpServletRequest request, String col, String word, String nowPage, Model model){
		
		
//		String id = (String)session.getAttribute("id");
//		String grade = (String)session.getAttribute("grade");
		
		boolean flag = dao.update(dto);
		
		if(flag){
			if(nowPage!=null && !nowPage.equals("")){
				model.addAttribute("nowPage", nowPage);
				model.addAttribute("col", col);
				model.addAttribute("word", word);
			return "redirect:../admin/list";
			}else{
			return "redirect:./read";
		}
	}else{
		return "error";
	}
}
	
	@RequestMapping(value="/member/update",method=RequestMethod.GET)
	public String update(String id, MemberDTO dto, HttpServletRequest request, HttpSession session){
		//String id = request.getParameter("id");
		if(id==null){
		id = (String)session.getAttribute("id"); //사용자가
		}
		
		dto = dao.read(id);
		
		request.setAttribute("id", id);
		request.setAttribute("dto", dto);
		
		return "/member/update";
	}
	
	@RequestMapping("/member/read")
	public String read(HttpSession session, HttpServletRequest request){
		
		String id = request.getParameter("id");	
		//관리자가 list에서 id를 클릭하고 사용자 상세정보 보기
		String grade = (String)session.getAttribute("grade"); //관리자의 그레이드 권한
		//read.jsp에서 회원목록 버튼 보여주기 위한 권한설정
		
		if(id==null){ //관리자가 아닐떄
			//일반사용자가 로그인후 나의 정보 메뉴를 클릭하고 본인 정보 확인 할때
			id = (String)session.getAttribute("id");
		}
		
		
		MemberDTO dto = dao.read(id);
		
		request.setAttribute("grade", grade);
		request.setAttribute("id", id);
		request.setAttribute("dto", dto);
		return "/member/read";
	}
	
	@RequestMapping("/member/logout")
	public String logout(HttpServletRequest request, HttpSession session){
		
		session.invalidate();
		return "redirect:../";
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
	public String login(String id, String passwd, String c_id, 
			String no, String ino, String nowPage, String nPage, String col, String word, Model model,
			String bflag, HttpSession session, HttpServletResponse response){

		boolean flag = dao.loginCheck(id,passwd);
		String grade = null; //회원등급
		if(flag){ //회원인경우
			grade = dao.getGrade(id);
			session.setAttribute("id", id);
			session.setAttribute("grade", grade);
			   // ---------------------------------------------- 
		    // Cookie 저장, Checkbox는 선택하지 않으면 null 임 
		    // ---------------------------------------------- 
		    Cookie cookie = null; 
		
		       
		    if (c_id != null){  // 처음에는 값이 없음으로 null 체크로 처리
		      cookie = new Cookie("c_id", "Y");    // 아이디 저장 여부 쿠키 
		      cookie.setMaxAge(120);               // 2 분 유지  
		      response.addCookie(cookie);          // 쿠키 기록 
		   
		      cookie = new Cookie("c_id_val", id); // 아이디 값 저장 쿠키  
		      cookie.setMaxAge(120);               // 2 분 유지 
		      response.addCookie(cookie);          // 쿠키 기록  
		         
		      
		    }else{ 
		      cookie = new Cookie("c_id", "");     // 쿠키 삭제 되어 읽어 올게 없어진다.
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		         
		      cookie = new Cookie("c_id_val", ""); // 쿠키 삭제 
		      cookie.setMaxAge(0); 
		      response.addCookie(cookie); 
		    } 
		    // --------------------------------------------- 
		   String url = "redirect:/";
		    if(bflag !=null && !bflag.equals("")){
			    model.addAttribute(ino,no);
			    model.addAttribute("nowPage", nowPage);
			    model.addAttribute("nPage", nPage);
			    model.addAttribute("col", col);
			    model.addAttribute("word", word);
			    url = "redirect:"+bflag;
		    }
		    
		    
		    return url;
		}else{
			return "member/idPwError";
		}
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.GET)
	public String login(HttpServletRequest request){
		
		/*----쿠키설정 내용시작----------------------------*/
		String c_id = "";     // ID 저장 여부를 저장하는 변수, Y, 저장되어질 변수
		String c_id_val = ""; // ID 값
		 
		Cookie[] cookies = request.getCookies(); 
		Cookie cookie=null; 
		 
		if (cookies != null){ 
		 for (int i = 0; i < cookies.length; i++) { 
		   cookie = cookies[i]; 
		 
		   if (cookie.getName().equals("c_id")){ //c_id로 저장된 아디가 있다면
		     c_id = cookie.getValue();     // Y c_id에다가 넣겠다는 의미
		   }else if(cookie.getName().equals("c_id_val")){ 
		     c_id_val = cookie.getValue(); // user1... 
		   } 
		 } 
		} 

		/*----쿠키설정 내용 끝----------------------------*/
		request.setAttribute("c_id", c_id);
		request.setAttribute("c_id_val", c_id_val);
		return "/member/login";
	}
	
	@RequestMapping("/member/email_form")
	public String email_form(){
		return "/member/email_form";
	}
	@RequestMapping("/member/email_proc")
	public String email_proc(String email, Model model){
		
		boolean flag = dao.duplicateEmail(email);
		
		model.addAttribute("email", email);
		model.addAttribute("flag", flag);
		
		
		return "/member/email_proc";
	}
	
	@RequestMapping("/member/id_form")
	public String id_form(){

		
		
		return "/member/id_form";
	}
	
	
	@RequestMapping("/member/id_proc")
	public String id_proc(String id, Model model){
		
		boolean flag = dao.duplicateId(id);
		
		model.addAttribute("id",id);
		model.addAttribute("flag",flag);
		
		
		return "/member/id_proc";
	}
	
	
	@RequestMapping(value="/member/create", method=RequestMethod.POST)
	public String create(MemberDTO dto, HttpServletRequest request){
		
		
		
		String str =null;
		String viewPage = "member/prcreateProc";
		
		if(dao.duplicateId(dto.getId())){
			str="중복된 아이디입니다.";
			request.setAttribute("str", str);
			
		}else if(dao.duplicateEmail(dto.getEmail())){
			str="중복된 이메일입니다.";
			request.setAttribute("str", str);
		}else{
			String upDir = request.getRealPath("/member/storage");
			String filename ="";
			int filesize = (int)dto.getFnameMF().getSize();
			
			if(filesize>0){
				filename = Utility.saveFile(dto.getFnameMF(), upDir);
			}else{
				filename="member.png";
			}
			
			dto.setFname(filename);
			
			boolean flag = dao.create(dto);
			
			if(flag){
				
				viewPage = "redirect:../";
			}else{
				viewPage = "error";
			}
			
		}
		
		return viewPage;
	}
	
	
	@RequestMapping(value="/member/create", method=RequestMethod.GET)
	public String create(){
		
		
		return "/member/createForm";
	}
	
	@RequestMapping("/member/agree")
	public String agree(){
	
		return "/member/agree";
	}
	
	@RequestMapping("/admin/list")
	public String list(HttpServletRequest request){
		//검색관련
		String col = Utility.checkNull(request.getParameter("col"));
		String word = Utility.checkNull(request.getParameter("word"));
		if(col.equals("total")){
			word = "";
		}
		//페이징관련
		
		int nowPage = 1; //첫번째 페이지부터 보여주겠다라는 뜻
		int recordPerPage = 5; //한페이지당 보여줄 레코드 
		//페이지가 바뀔수 있도록 해야한다. 
		if(request.getParameter("nowPage")!=null){
			nowPage = Integer.parseInt(request.getParameter("nowPage"));
		}
		//DB에서 가져올 순번
		int sno = ((nowPage - 1)*recordPerPage)+1;
		int eno = nowPage * recordPerPage;
		
		Map map = new HashMap();
		map.put("col", col);
		map.put("word", word);
		map.put("sno", sno);
		map.put("eno", eno);
		
		int total = dao.total(col,word);
		List<MemberDTO> list = dao.list(map);
		
		String paging = Utility.paging3(total, nowPage, recordPerPage, col, word);
		
		request.setAttribute("col", col);
		request.setAttribute("word", word);
		request.setAttribute("nowPage", nowPage);
		request.setAttribute("paging", paging);
		request.setAttribute("list", list);
		
		return "/member/list";
	}
	
}
