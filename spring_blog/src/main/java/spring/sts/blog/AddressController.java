package spring.sts.blog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.junit.runner.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spring.model.address.AddressDTO;
import spring.model.address.AddressDAO;
import spring.utility.blog.Utility;



@Controller
public class AddressController {
	@Autowired
	private AddressDAO dao;
	
	@RequestMapping("/address/delete")
	public String delete(AddressDTO dto, int no, Model model, String col, String word, String nowPage){
		
		boolean flag = dao.delete(no);
		if(flag){
			model.addAttribute("col",col);
			model.addAttribute("word",word);
			model.addAttribute("nowPage",nowPage);
			
			return "redirect:./list";
		}else{
			return "error";
		}
		
		
	}
	
	
	@RequestMapping("/address/read")
	public String read(int no, Model model){
		
		
		AddressDTO dto = dao.read(no);
		
		model.addAttribute("dto", dto);
		
		return "/address/read";
	}
	
	@RequestMapping(value="/address/update", method=RequestMethod.POST)
	public String update(AddressDTO dto, int no, Model model, String col, String word, String nowPage){
		
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
	@RequestMapping(value="/address/update", method=RequestMethod.GET)
	public String update(int no, Model model){
		
		model.addAttribute("dto", dao.read(no));
		
		return "/address/update";
	}
	
	@RequestMapping(value="/address/create", method=RequestMethod.POST)
	public String create(AddressDTO dto, Model model){
		
		boolean flag = dao.create(dto);
		if(flag){
			return "redirect:./list";
		}else{	
			return "error";
		}
		
	}
	@RequestMapping(value="/address/create", method=RequestMethod.GET)
	public String create(){
		
		return "/address/create";
	}
	
	@RequestMapping("/address/list")
	public String list(HttpServletRequest request){
		
		String col=Utility.checkNull(request.getParameter("col"));
		String word=Utility.checkNull(request.getParameter("word"));
		if(col.equals("total")){
			word="";
		}
		int nowPage=1;
		int recordPerPage=5;
		if(request.getParameter("nowPage")!=null){
			nowPage=Integer.parseInt(request.getParameter("nowPage"));
		}
		int ano=((nowPage-1)*recordPerPage)+1;
		int bno=(nowPage*recordPerPage);
		Map p=new HashMap();
		p.put("col", col);
		p.put("word",word);
		p.put("ano", ano);
		p.put("bno", bno);
		List<AddressDTO> list=dao.list(p);
		int total=dao.total(col, word);
		String paging=Utility.paging3(total, nowPage, recordPerPage, col, word);
		request.setAttribute("list",list);
		request.setAttribute("paging",paging);
		request.setAttribute("col",col);
		request.setAttribute("word",word);
		request.setAttribute("nowPage",nowPage);
		request.setAttribute("paging",paging);
		
		return "/address/list";
		
	}
}
