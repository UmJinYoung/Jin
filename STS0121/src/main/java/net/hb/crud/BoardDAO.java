package net.hb.crud;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public class BoardDAO {

	//org.mybatis.spring.SqlSessionTemplate
@Autowired	
SqlSessionTemplate temp;
	
 public void dbInsert(BoardDTO dto) {
	
	System.out.println("\ndao�Ѿ���̸�=" +dto.getName() );
	System.out.println("dao�Ѿ������=" +dto.getTitle());
	System.out.println("dao�Ѿ�³���=" +dto.getContent());
	System.out.println("dao�Ѿ�¼���=" +dto.getGender());
	System.out.println("dao�Ѿ�����=" +dto.getHobby());
	System.out.println("dao���ڿ�����="  + dto.getUpload_f());
	System.out.println("dao����������="  + dto.getImg_file_name());	
	temp.insert("board.add", dto);
	System.out.println("hobby ���̺� ������ ���� ����");
 }//end
 
 public List<BoardDTO> dbSelect() {
	 List<BoardDTO> list = temp.selectList("board.selectAll");
	 return list;
 }//end
 
 public BoardDTO dbDetail(int data) {
	 BoardDTO dto = (BoardDTO)temp.selectOne("board.detail", data);
	 return dto;
 }//end
 
 public void dbDelete(int data) {
	 temp.delete("board.delete", data);
 }//end
 
 public void dbEdit(BoardDTO dto) {
	 temp.update("board.edit", dto);
 }//end
 
}//BoardDA end
