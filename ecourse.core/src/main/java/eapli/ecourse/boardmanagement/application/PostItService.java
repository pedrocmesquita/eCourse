package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.boardmanagement.newdomain.BoardCell;
import eapli.ecourse.boardmanagement.newdomain.Content;
import eapli.ecourse.boardmanagement.newdomain.PostItFactory;
import eapli.ecourse.boardmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class PostItService {
    private final PostItRepository postItRepository;
    public PostItService(final PostItRepository postItRepo){
        postItRepository=postItRepo;

    }
    public PostIt createPostIt(Board Board, BoardCell cell, SystemUser owner, Content content){
        PostItFactory postItFactory = new PostItFactory();
        PostIt newPostIt = postItFactory.create(Board, cell, owner, content);
        return postItRepository.save(newPostIt);
    }
    public PostIt getPostItByBoardCell(BoardCell cell){
        return postItRepository.getPostItByBoardCell(cell);
    }

}
