package net.hb.crud.reply;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class ReplyDAO {
	
	@Autowired	
	SqlSessionTemplate temp;
	
	public void dbInsert(ReplyDTO dto) {
		System.out.println("댓글dao 넘어온idx="  + dto.getHobby_idx());
		System.out.println("댓글dao 넘어온저자="  + dto.getRwriter() );
		System.out.println("댓글dao 넘어온메모="  + dto.getRmemo());
		temp.insert("board_reply.add", dto);	
	}
	
	public List<ReplyDTO> dbSelect(int idx) {
		List<ReplyDTO> list = temp.selectList("board_reply.selectAll", idx);
		return list;
	}//end
	
	public void dbDelete(int data) {
		temp.delete("board_reply.delete", data);
	}//end
	
	public ReplyDTO dbDetail(ReplyDTO dto) {		
		ReplyDTO list = temp.selectOne("board_reply.detail", dto);
		return list;
	}//end
	
	public void dbEdit(ReplyDTO dto) {
		temp.update("board_reply.Edit",dto);
	}
	

}//class END
