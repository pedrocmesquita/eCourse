# US 3008 - As a User, I want to undo the last change in a post-it

## 1. Context

> In this user story, we will have to undo a post-it which ultimately means that we need to have a history of the changes, so the post-it will hold its past state (a newly created post-it will have that at null).

> There is another case we have to think separately, undoing a deleted post-it, so we will treat this user story as if it was two different user stories.

> For undoing a post-it, it will search its previous reference and insert it in the database as a new one.

> For undoing a deleted post-it, we will click undo post-it in an empty board entry, and if it has a previous reference, the old post-it will pop up in that position.

## 2. Requirements

> In order for the post-it to be undone, we need to have **post-its** in a **board** and a **history** of the changes.

So, the predefined dependencies are:

- US 3005 (we need to view the board in order to undo a post-it)
- US 3006 (we need to have post-its in the board in order to undo one)
- US 3007 (we need to have a history of the changes in order to undo a post-it)

> Related to authorization, the only rule we have is that the user must be logged in and have access to the board, which is previously verified in the dependencies.
Any type of user can undo a post-it, so no need for server-side authorization, just the login.
## 3. Analysis

## 4. Design

### 4.1. Realization

### 4.2. Class Diagram

### 4.3. Applied Patterns

### 4.4. Tests


## 5. Implementation
```java
public class UndoPostItUI extends AbstractUI {
    private final UndoPostItController theController = new UndoPostItController();
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser()));
@Override
    protected boolean doShow() {
        System.out.println("Select a Board to undo a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final SelectPostItWidget postItWidget = new SelectPostItWidget(theController.findPostItsByOwner(selectedBoard, theController.getUser()));
        System.out.println("Select a PostIt to undo");
        final PostIt selectedPostIt = postItWidget.selectPostIts();
        if (selectedPostIt == null)
            return false;
        theController.rollbackPost(selectedBoard, selectedPostIt);
    System.out.println("PostIt undone");
        return false;
    }

    @Override
    public String headline() {
        return "Undo changes to PostIt";
    }
}

public class UndoPostItController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();
    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItRepository repo2 = PersistenceContext.repositories().postIts();

    private final BoardCellRepository cellRepo = PersistenceContext.repositories().cells();

    private final LogRepository repo3 = PersistenceContext.repositories().logs();
    Optional<UserSession> session = authz.session();
    SystemUser user = session.get().authenticatedUser();

    public void rollbackPost(Board board, PostIt postIt)
    {
        BoardCell cell = cellRepo.getBoardCellByRowAndCol(postIt.getRow(), postIt.getColumn(),postIt);
        Preconditions.ensure(cell.getPost().rollbackPost(),
                "Failure undoing changes to post.");
        Log log = new Log("PostIt " + postIt.identity() + " rolled back.");
        board.addLog(log);
        repo.save(board);
        cellRepo.save(cell);
        repo2.save(cell.getPost());
        System.out.println("PostIt rolled back.");
    }
    public Iterable<Board> findBoards(SystemUser user) {
        AccessLevel write = AccessLevel.WRITE;
        Iterable<Board> board1 = findBoardsByOwner(user);
        Iterable<Board> board2 = findBoardsByUserPermission(user, write);
        Set<Board> boards1 = new HashSet<>();
        Set<Board> boards2 = new HashSet<>();
        board1.forEach(boards1::add);
        board2.forEach(boards2::add);
        boards1.addAll(boards2);
        return boards1;
    }
    public Iterable<Board> findBoardsByUserPermission(SystemUser user, AccessLevel accessLevel) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        Iterable<BoardPermission> permission = repo1.findBySystemUserAccessLevel(user, accessLevel);
        Set<Board> boards = new HashSet<>();

        for(BoardPermission bp : permission)
            boards.add(repo.findByBoardPermission(bp));
        return boards;
    }
    /**
     * Find boards by owner iterable.
     *
     * @param user the user
     * @return the iterable
     */
    public Iterable<Board> findBoardsByOwner(SystemUser user)
    {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        return repo.findByOwner(user);
    }
    public SystemUser getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        return user;
    }


    public Iterable<PostIt> findPostIts(Board selectedBoard) {
        return repo2.getPostItsByBoard(selectedBoard);
    }
    public Iterable<PostIt> findPostItsByOwner(Board selectedBoard, SystemUser user) {
        return repo2.getPostItsByOwner(selectedBoard,user);
    }
}

```
```java

## 6. Integration/Demonstration