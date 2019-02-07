package net.hb.crud.reply;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReplyController {
	
	@Inject
	@Autowired
	ReplyDAO dao;
	
	@RequestMapping("/reply_insert.do")
	public String reply_insert(ReplyDTO dto) {
		dao.dbInsert(dto);
		return "redirect:/detail.do?idx="+dto.getHobby_idx();
	}//end
	
	
	@RequestMapping("/reply_list.do")
	public ModelAndView reply_select(@RequestParam("idx") int idx) {
		ModelAndView mav = new ModelAndView();
		List<ReplyDTO> list = dao.dbSelect(idx);
		mav.addObject("list", list);
		mav.setViewName("board_reply");
		return mav;				
	}//end
	
	@RequestMapping("/reply_delete.do")
	public String reply_delete(Model model,@RequestParam("idx") int data, @RequestParam("hobby_idx") int hobby_idx) {
		dao.dbDelete(data);
		return "redirect:/detail.do?idx="+hobby_idx;
	}//end
	
	@RequestMapping("/reply_preEdit.do")
	public ModelAndView reply_preEdit(ReplyDTO dto) {
		ModelAndView mav = new ModelAndView();
		ReplyDTO list = dao.dbDetail(dto);
		mav.addObject("list", list);
		mav.setViewName("board_reply_edit");
		return mav;		
	}//end
	
	@RequestMapping("/reply_edit.do")
	public String reply_edit(ReplyDTO dto) {
		dao.dbEdit(dto);
		return "redirect:/detail.do?idx="+dto.getHobby_idx();
	}


}//class END
