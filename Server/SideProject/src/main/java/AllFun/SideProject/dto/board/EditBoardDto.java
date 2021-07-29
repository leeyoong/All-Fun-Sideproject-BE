package AllFun.SideProject.dto.board;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EditBoardDto {
    private String title; // 제목
    private String content; // 내용
}