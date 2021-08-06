package AllFun.SideProject.controller.dashBoard;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class GroupController {

    /**
     * get dashboard main page (show group)
     * @return
     */
    @GetMapping("/main")
    public ResponseEntity<?> dashMain(){
        return null;
    }

    /**
     * Select dashboard group (show 게시판, 일정, 깃허브, 프로젝트 소개)
     * @return
     */
    @GetMapping("/{groupId}")
    public ResponseEntity<?> selectDashBoard(){
        return null;
    }

}
