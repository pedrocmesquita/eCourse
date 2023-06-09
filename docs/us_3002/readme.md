# US 3002

## 1. Context

In Sprint B the client wants us to develop a feature for our System. He wants a User to be able to create a board

## 2. Requirements

As User, I want to create a board.

The only dependecy is that the User needs to be authenticated to access to this feature.


## 3. Analysis

Information in System Specification

    It has a unique title. It is divided into a certain number of columns and rows. Therefore it has a certain number of cells. 
    For the moment it makes sense to define the maximum number of rows to 20 and of columns to 10. But this should be a setting in a property file.
    Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number.
    The user that creates the board is its owner. The owner can share the board with other users.
    Users may have read or write access to the board.

Information in Forum

    "Can a user own more than one board?" 
    Yes.

    "As stated in the system specifications document, "Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number", we wanted to clarify if it should be possible to have a column with no title and no identifier.Also, can the identifier of a column/row change once it's assigned?"
    In order for user to post content into a cell they must identify the cell. Therefore, I think at least, ir should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number.
    Regarding changing the title of the columns and rows after creating the board, there is nothing explicit about that. Therefore, I would accept the solution that does not support that possibility.

This is an excerpt of our domain Model, it provides the clear idea of how the Board should be identified according to the information in System Specification.

![Domain Model Excerpt](Analysis/DomainModelExcerpt.svg)

## 4. Design

### 4.1. Realization

#### 4.1.1. Sequence Diagram


### 4.2. Class Diagram


### 4.3. Applied Patterns

#### 4.3.1. Factory

- Our PersistenceContext will create a RepositoryFactory based on the configuration file then the RepositoryFactory will create the repository that we need in order to persist our domain entity.
- Factories are also used for the creation of Board, BoardPermission and BoardEntry ( BoardFactory, BoardPermissionFactory and BoardEntryFactory )



#### 4.3.2 Service

- Services are operations or functions that are not naturally in line with the responsibility of an entity or value object. They are used to model operations that involve multiple objects or complex behaviour.

#### 4.3.3 Single Responsibility Principle (SRP)

- Ensure that each object has a clear and well-defined responsibility within the domain.

#### 4.3.4 Tell, Don't Ask

- Ensure that objects do not expose their internal state or behaviour to the outside world. On the contrary, objects should receive commands telling them what they should do, rather than being asked for information about their current state.


#### 4.3.5 Model-View-Controller (MVC)

- Model is responsible for managing the data and business logic of the application. (BoardService)
- View is responsible for presenting the data to the user in a human-readable format. (CreateBoardUI)
- Controller is responsible for handling the user input and updating the model and the view accordingly. (CreateBoardController)

### 4.4. Tests

### 5. Implementation
CreateBoardUI
```java
package eapli.ecourse.app.teacher.console.presentation.board;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.BoardEntry;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.ArrayList;
import java.util.List;

public class CreateBoardUI extends AbstractUI {
    private final CreateBoardController theController = new CreateBoardController();

    /**
     * The constant MIN_ROWS_COLUMNS.
     */
    private static final String MIN_ROWS_COLS = "1";

    /**
     * User want to create a new Board.
     * Ask User fields.
     * BoardTitle, BoardNRow, BoardNCol,
     * @return false
     */
    @Override
    protected boolean doShow() {
        final String boardTitle = Console.readLine("Board Title:");
        final String boardNCols = Console.readLine("Board Number of Columns:");
        final String boardNRows = Console.readLine("Board Number of Rows:");

        List<BoardEntry> allBoardEntrys = new ArrayList<>();


        try{
            //Board Entrys for columns
            System.out.println("----COLUMNS ENTRYS----");
            int nCols = Integer.parseInt(boardNCols);
            for(int i = 1; i <= nCols; i++){
                System.out.println("Board Row position -> " + MIN_ROWS_COLS);
                System.out.println("Board Column position -> " + i);

                final String entryTitle = Console.readLine("Entry Title:");

                BoardEntry boardEntry = theController.createBoardEntry(
                        String.valueOf(i),
                        MIN_ROWS_COLS,
                        String.valueOf(i),
                        entryTitle,
                        boardNRows,
                        boardNCols
                );
            }

            //Board Entrys for rows
            System.out.println("----ROWS ENTRYS----");
            for(int i = 2; i <= Integer.parseInt(boardNRows); i++) {
                String entryTitle = null;
                for (int j = 1; j <= nCols; j++) {
                    System.out.println("Board Row position -> " + i);
                    System.out.println("Board Column position -> " + j);
                    entryTitle = Console.readLine("Entry Title:");
                }


                BoardEntry boardEntry = theController.createBoardEntry(
                        String.valueOf(i),
                        String.valueOf(i),
                        MIN_ROWS_COLS,
                        entryTitle,
                        boardNRows,
                        boardNCols
                );

                allBoardEntrys.add(boardEntry);
            }

            theController.createBoard(boardTitle, boardNRows, boardNCols, allBoardEntrys);


            System.out.println("Board Successfully created!");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (IntegrityViolationException e){
            System.out.println("A board with that name already exists");
        }

        return true;
    }

    /**
     * @return String to headline
     */
    @Override
    public String headline() {
        return "Create Board";
    }
}
```
CreateBoardController
```java
package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardEntry;
import eapli.ecourse.boardmanagement.domain.BoardEntryFactory;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserSession;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.util.List;
import java.util.Optional;

public class CreateBoardController {
    /**
     * Authorization service instance.
     */
    private final AuthorizationService authz;


    //temporario
    private final BoardRepository repository = new BoardRepository();

    /**
     * Create a board service with repository injection.
     */
    private final BoardService boardSvc = new BoardService(
            PersistenceContext.repositories().boards());

    public CreateBoardController() {
        authz = AuthzRegistry.authorizationService();
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
    }


    /**
     * Create shared board.
     * @param boardTitlep Board Title
     * @param boardNRowp Board number of rows
     * @param boardNColp Board number of columns
     * @param allBoardEntrys Board entrys
     * @return Board
     */
    public Board createBoard(final String boardTitlep,
                             final String boardNRowp,
                             final String boardNColp,
                             final List<BoardEntry> allBoardEntrys) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());

        Board board = boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authz.session().get().authenticatedUser());
        repository.add(board);
        return board;
    }

    /**
     * Create shared board.
     * @param boardTitlep Board Title
     * @param boardNRowp Board number of rows
     * @param boardNColp Board number of columns
     * @param allBoardEntrys Board entrys
     * @param authUser authenticated user
     * @return Board
     */
    public Board createBoard(final String boardTitlep,
                             final String boardNRowp,
                             final String boardNColp,
                             final List<BoardEntry> allBoardEntrys,
                             final SystemUser authUser) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return boardSvc.createBoard(boardTitlep, boardNRowp, boardNColp,
                allBoardEntrys, authUser);
    }

    /**
     * Create board entry.
     * @param entryNumberp Entry number
     * @param boardRowp Row position
     * @param boardColp Column position
     * @param entryTitlep Entry Title
     * @param boardNRowp Board number of rows
     * @param boardNColps Board number of columns
     * @return BoardEntry
     */
    public BoardEntry createBoardEntry(final String entryNumberp,
                                       final String boardRowp,
                                       final String boardColp,
                                       final String entryTitlep,
                                       final String boardNRowp,
                                       final String boardNColps
                                       ) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.nonUserValues());

        return new BoardEntryFactory().create(
                entryNumberp,
                boardRowp,
                boardColp,
                entryTitlep,
                boardNRowp,
                boardNColps
        );
    }

    /**
     * Create board entry.
     * @param entryNumberp Entry number
     * @param boardRowp Row position
     * @param boardColp Column position
     * @param entryTitlep Entry Title
     * @param boardNRowp Board number of rows
     * @param boardNColps Board number of columns
     * @return BoardEntry
     */
    public BoardEntry createBoardEntry(final String entryNumberp,
                                       final String boardRowp,
                                       final String boardColp,
                                       final String entryTitlep,
                                       final String boardNRowp,
                                       final String boardNColps,
                                       final SystemUser authUser ) {
        Preconditions.ensure(authUser != null,
                "You need to authenticate first");

        return new BoardEntryFactory().create(
                entryNumberp,
                boardRowp,
                boardColp,
                entryTitlep,
                boardNRowp,
                boardNColps
        );
    }
}
```
```java
//todo add rest of classes
```
### 6. Integration/Demonstration



### 7. Observations

Example of BoardEntry:

| Coluna 1 & Linha 1 | Coluna 2 & Linha 1 | Coluna 3 & Linha 1 |
|:------------------:|:------------------:|:------------------:|
| Coluna 1 & Linha 2 |                    |                    |
| Coluna 1 & Linha 3 |                    |                    |
|                    |                    |                    |