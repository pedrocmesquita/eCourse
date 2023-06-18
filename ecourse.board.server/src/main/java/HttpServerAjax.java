

import eapli.ecourse.boardmanagement.application.ViewBoardInServerController;
import eapli.ecourse.boardmanagement.newdomain.Board;


public class HttpServerAjax {

    public HttpServerAjax(){

    }

    public String ViewBoard(String title){

        String boardinfo;
        ViewBoardInServerController ctrl = new ViewBoardInServerController();
        boardinfo = ctrl.getBoardtoStringByTitle(title);
        return boardinfo;
    }
}