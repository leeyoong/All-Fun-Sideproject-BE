package AllFun.SideProject.controller;

import AllFun.SideProject.domain.dashBoard.DashGroup;
import AllFun.SideProject.domain.matching.Board;
import AllFun.SideProject.dto.mainPage.MyGroupDto;
import AllFun.SideProject.dto.mainPage.MyMatchingBoardDto;
import AllFun.SideProject.dto.mainPage.MyMatchingStatusDto;
import AllFun.SideProject.dto.mainPage.MyToDoDto;
import AllFun.SideProject.service.dashBoard.DashGroupService;
import AllFun.SideProject.service.dashBoard.GroupMemberService;
import AllFun.SideProject.service.dashBoard.ToDoService;
import AllFun.SideProject.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/home/{memberId}")
@RequiredArgsConstructor
public class HomeController {
    private final DashGroupService dashGroupService;
    private final GroupMemberService groupMemberService;
    private final ToDoService toDoService;
    private final MemberService memberService;
    /**
     * my group
     * @param memberId
     * @return
     */
    @GetMapping("/group")
    public ResponseEntity<?> getMyGroup(@PathVariable("memberId")Long memberId){
        List<DashGroup> dashGroups = groupMemberService.getDashGroup(memberId);
        // 아직 그룹이 없는 경우
        if (dashGroups==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 그룹이 있는 경우
        List<MyGroupDto> response = new ArrayList<>();
        for(DashGroup dashGroup : dashGroups){
            MyGroupDto myGroupDto = new MyGroupDto(dashGroup.getId(),dashGroup.getGroupName());
            response.add(myGroupDto);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 월별 그룹 일정 보기
     * @param memberId
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/todo/{year}/{month}")
    public ResponseEntity<?> getMonthTodo(@PathVariable("memberId")Long memberId, @PathVariable("year")String year,
                                        @PathVariable("month")String month){
        LocalDateTime startDateTime = LocalDateTime.parse(year+"-"+month+"-"+"1 00:00:00",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        LocalDateTime endDateTime = startDateTime.plusMonths(1).minusSeconds(1);

        List<DashGroup> dashGroups = groupMemberService.getDashGroup(memberId);
        // 아직 그룹이 없는 경우
        if (dashGroups==null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        // 그룹이 있는 경우
        List<MyToDoDto> response = toDoService.getGroupTodo(dashGroups, startDateTime, endDateTime);

        // 일정이 없는 경우
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 내가 쓴 매칭 글
     * @param memberId
     * @return
     */
    @GetMapping("/matching/board")
    public ResponseEntity<?> getMyMatchingBoard(@PathVariable("memberId")Long memberId){
        List<MyMatchingBoardDto> response = memberService.getMyMatchingBoard(memberId);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(response);
    }

    /**
     * 내가 신청한 매칭글에 대한 결과
     * @param memberId
     * @return
     */
    @GetMapping("/matching/status")
    public ResponseEntity<?> getMyMatchingStatus(@PathVariable("memberId")Long memberId){
        List<MyMatchingStatusDto> response = memberService.getMyMatchingStatus(memberId);
        if(response == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(response);
    }

}
