package net.hb.crud;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;




@Controller
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	@Autowired
	BoardDAO dao;
	
	@Autowired
	private ServletContext  application;
	
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String board_write(Locale locale, Model model) {
		return "board";
	}//end
	
	@RequestMapping("/insert.do")
	public String board_insert(BoardDTO dto) {
		System.out.println("\n��Ʈ�� �Ѿ���̸�="  + dto.getName() );
		System.out.println("��Ʈ�� �Ѿ������="  + dto.getTitle());
		System.out.println("��Ʈ�� �Ѿ�³���="  + dto.getContent());
		System.out.println("��Ʈ�� �Ѿ�¼���="  + dto.getGender());
		System.out.println("��Ʈ�� �Ѿ�����="  + dto.getHobby());
		System.out.println("��Ʈ�� �Ѿ������="  + dto.getUpload_f());	
		
		  String path=application.getRealPath("/resources/upload");
		  System.out.println(path);
		  String img=dto.getUpload_f().getOriginalFilename();
		  File file = new File(path, img); //�ڹ� ���� io��Ű�� Fileȭ		  
		  
		  try{
		    dto.getUpload_f().transferTo(file);			
		  }catch(Exception ex){ System.out.println("���Ͽ���:"+ex); }
		
		dto.setImg_file_name(img); 
		System.out.println("��Ʈ�� �Ѿ������="  + dto.getImg_file_name());		
		
		dao.dbInsert(dto);
	 // return "boardList";
		return "redirect:/list.do" ;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView board_select(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		List<BoardDTO> list = dao.dbSelect();		
		mav.addObject("naver", list);
		mav.setViewName("boardList");
		return mav;		
	}//end
	
	/*
	@RequestMapping("/detail.do")
	public String board_detail(Model model,@RequestParam("idx") int data) {
		BoardDTO dto = dao.dbDetail(data);
		model.addAttribute("dto", dto);		
		return "boardDetail";
	}	
	*/

	  @RequestMapping("/detail.do")
		public ModelAndView board_detail(HttpServletRequest request) {
		  ModelAndView mav = new ModelAndView( );
		  int data=Integer.parseInt(request.getParameter("idx"));
		  BoardDTO dto=dao.dbDetail(data);
		  mav.addObject("dto", dto);
		  mav.setViewName("boardDetail");
		  return mav;
		}//end

	
	
	@RequestMapping("/delete.do")
	public String board_delete(Model model,@RequestParam("idx") int data) {		
		dao.dbDelete(data);
		return "redirect:/list.do";
	}
	

	@RequestMapping("/download.do")
	public String board_download(HttpServletRequest request, HttpServletResponse response) {
	   String data="";		
		 try {	
		  String filename=URLEncoder.encode(request.getParameter("fidx"), "UTF-8");
		  //String filename=request.getParameter("fidx");
		  //�������� The valid characters are defined in RFC 7230 and RFC 3986
		  data=request.getParameter("idx");
		  System.out.println("\n�ٿ�ε� filename="+filename+" idx="+data);
		  
		  String path=application.getRealPath("/resources/upload");
		  System.out.println(path);
		 
		  filename=URLEncoder.encode(filename,"UTF-8");
	      response.setHeader("Content-Disposition", "attachment;filename="+filename);
		  File file=new File(path,filename);
		 
			 InputStream is=new FileInputStream(file);
			 OutputStream os=response.getOutputStream();
			 byte[ ] b=new byte[(int)file.length()];
			
			 is.read(b,0,b.length);
			 os.write(b);
			 is.close(); os.close(); 
		  }catch(Exception ex) { }
		  return "redirect:/detail.do?idx="+data;
	   }//end
	
	@RequestMapping("/preEdit.do")
	public ModelAndView board_preEdit(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView( );
		int data=Integer.parseInt(request.getParameter("idx"));
		BoardDTO dto=dao.dbDetail(data);
		mav.addObject("dto", dto);
		mav.setViewName("boardEdit");
		return mav;
	}//end
	
	@RequestMapping("/edit.do")
	public String board_edit(BoardDTO dto) {  
		String path=application.getRealPath("/resources/upload");
		System.out.println(path);
		MultipartFile mf=dto.getUpload_f();
		String img=mf.getOriginalFilename();
	  
		System.out.println("\nedit���� �Ѿ���̸�="  + dto.getName() );
		System.out.println("edit���� �Ѿ������="  + dto.getTitle());
		System.out.println("edit���� �Ѿ�³���="  + dto.getContent());
		System.out.println("edit���� �Ѿ�¼���="  + dto.getGender());
		System.out.println("edit���� �Ѿ�����="  + dto.getHobby());
		System.out.println("edit���� ���ڿ�����="  + dto.getUpload_f());
		System.out.println("edit���� ���ڿ�����="  + img);
		File file=new File(path, img);
		try{
	    //dto.getUpload_f().transferTo(file);  
	  	//FileCopyUtilsŬ�������
	    FileCopyUtils.copy(dto.getUpload_f().getBytes(), file);
		}catch(Exception ex){ }
		dto.setImg_file_name(img);
	  
		dao.dbEdit(dto); 
		return "redirect:/list.do";
	}//end



	
}//BoardController class END

