package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.app.common.console.presentation.course.CoursesPrinter;
import eapli.ecourse.boardmanagement.newdomain.Board;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.framework.presentation.console.SelectWidget;

/**
 * Course widget to show and select a course
 */
public class SelectBoardWidget {

    private final Iterable<Board> boards;

    /**
     * Instantiates a new Select board widget.
     *
     * @param boards the boards
     */
    public SelectBoardWidget(Iterable<Board> boards) {
        this.boards = boards;
    }

    /**
     * shows courses passed as parameter in constructor and ask to select one
     *
     * @return selected course
     */
    public Board selectBoard() {
        if (!boards.iterator().hasNext()) {
            System.out.println("There are no courses available");
            return null;
        }
            final SelectWidget<Board> selector = new SelectWidget<>(BoardsPrinter.HEADER, boards, new BoardsPrinter());
        selector.show();
        return selector.selectedElement();
    }
}
