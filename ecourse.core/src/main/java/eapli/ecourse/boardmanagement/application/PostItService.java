package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.*;
import eapli.ecourse.boardmanagement.repositories.BoardCellRepository;
import eapli.ecourse.boardmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class PostItService {
    private final PostItRepository postItRepository;
    private final BoardCellRepository boardCellRepository;
    public PostItService(final PostItRepository postItRepo){
        postItRepository=postItRepo;
        boardCellRepository = null;
    }
    public PostItService(final PostItRepository postItRepo, final BoardCellRepository boardCellRepo){
        postItRepository=postItRepo;
        boardCellRepository=boardCellRepo;
    }
    public PostIt createPostIt(Board Board, BoardCell cell, SystemUser owner, Content content){
        PostItFactory postItFactory = new PostItFactory();
        PostIt newPostIt = postItFactory.create(Board, cell, owner, content);
        return postItRepository.save(newPostIt);
    }
    public PostIt getPostItByBoardCell(BoardCell cell){
        return postItRepository.getPostItByBoardCell(cell);
    }
    public BoardCell getBoardCellByRowColumn(BoardRow row, BoardCol col, PostIt post){
        return boardCellRepository.getBoardCellByRowAndCol(row, col,post);
    }

}
