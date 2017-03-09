package spring.sts.blog;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import spring.model.images.ImagesDAO;
import spring.model.images.ImagesDTO;
import spring.utility.blog.Utility;



@Controller
public class ImagesController {
	@Autowired
	private ImagesDAO dao;
	
	@RequestMapping(value="/images/delete", method=RequestMethod.POST)
	public String delete(int no,String passwd, String oldfile, String col, String word, String nowPage, Model model, HttpServletRequest request){
		
		String basePath = request.getRealPath("/img/storage");
		

		Map map = new HashMap();
		map.put("no", no);
		map.put("passwd", passwd);
		boolean pflag = dao.passCheck(map);
		boolean flag= false;

	if(pflag){
		if(dao.delete(no))
		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		return "redirect:./list";
	}else{
		return "passwdError";
	}
}


	
	
	@RequestMapping(value="/images/delete", method=RequestMethod.GET)
	public String delete(int no, String passwd, Model model){
		
	 	model.addAttribute("flag", dao.checkRefno(no));
		
		return "/images/delete";
	}

	@RequestMapping(value="/images/reply", method=RequestMethod.POST)
	public String reply(ImagesDTO dto, Model model, HttpServletRequest request, MultipartFile fnameMF, String col, String word, String nowPage){
		
		String basePath = request.getRealPath("/img/storage");
		
		int filesize = (int)fnameMF.getSize();
		String fname = "member.png";
		if(filesize>0){
			fname = Utility.saveFile(dto.getFnameMF(), basePath);
		}
		dto.setFname(fname);
		
		Map map = new HashMap();
		map.put("grpno", dto.getGrpno());
		map.put("ansnum", dto.getAnsnum());
		dao.upAnsnum(map);
		
		
		if(dao.createReply(dto)){
			model.addAttribute("col", col);
			model.addAttribute("word", word);
			model.addAttribute("nowPage", nowPage);
			
			return "redirect:./list";
		}else{
			return "error";
		}

	}
	
	@RequestMapping(value="/images/reply", method=RequestMethod.GET)
	public String reply(ImagesDTO dto, int no, Model model){

		model.addAttribute("dto", dao.readReply(no));
		
		return "/images/reply";
	}
	
	@RequestMapping(value="/images/update", method=RequestMethod.POST)
	public String update(ImagesDTO dto, Model model, String oldfile, String col, String word, String nowPage, HttpServletRequest request, MultipartFile fnameMF){
		
		String basePath = request.getRealPath("/img/storage");

		int filesize = (int)dto.getFnameMF().getSize();
		String fname = "member.png";
		if(filesize > 0){
		fname = Utility.saveFile(dto.getFnameMF(), basePath);
		}
		dto.setFname(fname);

		Map map = new HashMap();
		map.put("no", dto.getNo());
		map.put("passwd", dto.getPasswd());
		
		boolean pflag = dao.passCheck(map);
		if (pflag) {
			if(dao.update(dto)){		
				model.addAttribute("col", col);
				model.addAttribute("word", word);
				model.addAttribute("nowPage", nowPage);
				return "redirect:./list";
			}else{
				return "error";
			}
		}else{
			Utility.deleteFile(basePath, oldfile);
			return "passwdError";
		}
	}
	
	@RequestMapping(value="/images/update", method=RequestMethod.GET)
	public String update(int no, Model model){
		
		model.addAttribute("dto", dao.read(no));
		
		return "/images/update";
	}
	
	@RequestMapping(value="/images/create", method=RequestMethod.POST)
	public String create(ImagesDTO dto, HttpServletRequest request, MultipartFile fnameMF){
	
		String basePath = request.getRealPath("/img/storage");
		
		int filesize = (int)fnameMF.getSize();
		String fname = "member.png";
		if(filesize>0){
			fname = Utility.saveFile(dto.getFnameMF(), basePath);
		}

		dto.setFname(fname);
		
		boolean flag = dao.create(dto);
		
		if(flag){
			return "redirect:./list";
		}else{
			return "error";
		}
		
	}
	
	@RequestMapping(value="/images/create", method=RequestMethod.GET)
	public String create(){
		
		return "/images/create";
	}
	
	@RequestMapping("/images/read")
	public String read(Model model, int no, String col, String word, String nowPage, HttpServletRequest request){
		
		//no = Integer.parseInt(request.getParameter("no"));
		
		dao.upViewcnt(no);
		
		ImagesDTO dto = dao.read(no);

		Map map = dao.imgRead(no);
		BigDecimal[] noArr = {((BigDecimal)map.get("PRE_NO2")), 
		((BigDecimal)map.get("PRE_NO1")),
		((BigDecimal)map.get("NO")),
		((BigDecimal)map.get("NEX_NO1")),
		((BigDecimal)map.get("NEX_NO2"))
		};

		String[] files = {
		((String)map.get("PRE_FILE2")),
		((String)map.get("PRE_FILE1")),
		((String)map.get("FNAME")),
		((String)map.get("NEX_FILE1")),
		((String)map.get("NEX_FILE2"))

		};

		String content = dto.getContent();
		content = content.replaceAll("\r\n", "<br>");

		model.addAttribute("col", col);
		model.addAttribute("word", word);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("dto", dto);
		model.addAttribute("no", no);
		model.addAttribute("files", files);
		model.addAttribute("noArr", noArr);

		return "/images/read";
		}
	
	@RequestMapping("/images/list")
	public String list(HttpServletRequest request,Model model ){
		
		//검색============================================

				String col= Utility.checkNull(request.getParameter("col"));
				String word= Utility.checkNull(request.getParameter("word"));

				if(col.equals("total")){
				word="";
				}

				//paging 관련=======================================
				int nowPage= 1;

				if(request.getParameter("nowPage")!=null){ 
				nowPage=Integer.parseInt(request.getParameter("nowPage"));
				}
				int record= 5;

				//DB에서 가져올 순번=====================================
				int sno= ((nowPage-1)*record)+1; 
				int eno= nowPage*record;


				Map map= new HashMap();
				map.put("col", col);
				map.put("word", word);
				map.put("sno", sno);
				map.put("eno", eno);
			
				
				int total = dao.total(col, word);
				List<ImagesDTO> list= dao.list(map);
		     	String paging = Utility.paging3(total, nowPage, record, col, word);
		     	model.addAttribute("col", col);
		     	model.addAttribute("word", word);
		     	model.addAttribute("nowPage", nowPage);
		     	model.addAttribute("paging", paging);
		     	model.addAttribute("list", list);
		
		return "/images/list";
	}

}
