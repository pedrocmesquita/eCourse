package eapli.ecourse.app.common.console.presentation.boards;

import eapli.ecourse.boardmanagement.newdomain.PostIt;
import eapli.framework.presentation.console.SelectWidget;

public class SelectPostItWidget {

        private final Iterable<PostIt> postIts;

        /**
         * Instantiates a new Select PostIt widget.
         *
         * @param postIts the postIts
         */
    public SelectPostItWidget(Iterable<PostIt> postIts) {
        this.postIts = postIts;
    }

        /**
         * shows courses passed as parameter in constructor and ask to select one
         *
         * @return selected course
         */
        public PostIt selectPostIts() {
        if (!postIts.iterator().hasNext()) {
            System.out.println("There are no postIts available");
            return null;
        }
        final SelectWidget<PostIt> selector = new SelectWidget<>(PostItsPrinter.HEADER, postIts, new PostItsPrinter());
        selector.show();
        return selector.selectedElement();
    }

    }
