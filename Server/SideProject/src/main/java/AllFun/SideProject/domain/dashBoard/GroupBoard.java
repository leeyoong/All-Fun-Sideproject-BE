package AllFun.SideProject.domain.dashBoard;

import AllFun.SideProject.domain.base.BaseEntity;
import AllFun.SideProject.domain.base.BoardKinds;
import AllFun.SideProject.domain.member.Member;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GroupBoard extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="group_board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="group_id")
    private DashGroup group; // 그룹 id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member; // member id

    private String title;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    private BoardKinds kinds;

    @OneToMany(mappedBy = "groupBoard")
    private List<BoardHit> boardHits = new ArrayList<>();

    public static GroupBoard createGroupBoard(String title, String content, BoardKinds kinds, Member member){
        GroupBoard groupBoard = new GroupBoard();
        groupBoard.setMember(member);
        groupBoard.setContent(content);
        groupBoard.setTitle(title);
        groupBoard.setKinds(kinds);
        return groupBoard;
    }
    public void addBoardHit(BoardHit boardHit){
        boardHits.add(boardHit);
        boardHit.setGroupBoard(this);
    }
}
