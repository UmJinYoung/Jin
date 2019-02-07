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
	
	System.out.println("\ndao넘어온이름=" +dto.getName() );
	System.out.println("dao넘어온제목=" +dto.getTitle());
	System.out.println("dao넘어온내용=" +dto.getContent());
	System.out.println("dao넘어온성별=" +dto.getGender());
	System.out.println("dao넘어온취미=" +dto.getHobby());
	System.out.println("dao문자열파일="  + dto.getUpload_f());
	System.out.println("dao물리적파일="  + dto.getImg_file_name());	
	temp.insert("board.add", dto);
	System.out.println("hobby 테이블 데이터 저장 성공");
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
