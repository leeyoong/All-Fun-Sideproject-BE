package AllFun.SideProject.service.matching;

import AllFun.SideProject.domain.base.BoardStatus;
import AllFun.SideProject.domain.base.RoleType;
import AllFun.SideProject.domain.matching.Board;
import AllFun.SideProject.domain.matching.BoardRole;
import AllFun.SideProject.repository.matching.BoardRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardRoleService {
    private final BoardRoleRepository boardRoleRepository;

    /**
     * create (board - proj member - role)
     * @param boardRole
     */
    @Transactional
    public void save(BoardRole boardRole){
        boardRoleRepository.save(boardRole);
    }

    /**
     * get board - role type
     * @param board
     * @return
     */
    public List<RoleType> getRoleType(Board board){
        List<BoardRole> boardRoles = boardRoleRepository.findAllByBoard(board).orElse(null);
        List<RoleType> response = new ArrayList<>();
        for (BoardRole boardRole : boardRoles) {
            if(boardRole.getHope() != 0)
                response.add(boardRole.getRole());
        }
        return response;
    }

    public Page<BoardRole> boardListFilter(Pageable pageable, RoleType role){
        Page<BoardRole> boardRoles = boardRoleRepository.findAllByRole(role, pageable);

        return boardRoles;
    }
}
