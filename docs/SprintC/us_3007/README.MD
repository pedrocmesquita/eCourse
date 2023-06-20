# US 3007

## 1. Context

As a User I want to be able to change a post-it .

## 2. Requirements

As User, I want to change a post-it.

Dependencies:

**US 3002:** As User, I want to create a board.

**US 3006:** As User, I want to create a post-it on a board.


## 3. Analysis

Information in System Specification

    The user who created a post in a cell can change that post. It may change its contents or move the post into a free cell. The user can also undo the last change. The owner of a post can delete it.

Information in Forum

    "Uma célula pode ter mais do que um post it?" 

    Neste momento (no âmbito deste projeto) isso não será necessário. A ser possível (uma célula com mais do que 1 post-it) isso iria dificultar algumas funcionalidades, como a que permite mudar um post-it.

    "As stated in the system specifications document, "Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number", we wanted to clarify if it should be possible to have a column with no title and no identifier.Also, can the identifier of a column/row change once it's assigned?"

    In order for user to post content into a cell they must identify the cell. Therefore, I think at least, ir should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number.
    Regarding changing the title of the columns and rows after creating the board, there is nothing explicit about that. Therefore, I would accept the solution that does not support that possibility.

## 4. Design


## 5. Implementation

```java
public class ChangePostItUI extends AbstractUI {
    private final ChangePostItController theController = new ChangePostItController();

    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser()));


    @Override
    protected boolean doShow() {
        System.out.println("Select a Board to change a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final SelectPostItWidget postItWidget = new SelectPostItWidget(theController.findPostIts(selectedBoard));
        System.out.println("Select a PostIt to change");
        final PostIt selectedPostIt = postItWidget.selectPostIts();
        if (selectedPostIt == null)
            return false;
        final String option = Console.readLine("Do you want to change position or content? (p/c)");
        if(option.equals("p")){
            final int col = Console.readInteger("New rolumn of postit:");
            final int row = Console.readInteger("New row of postit:");
            theController.changePostPosition(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), row, col,selectedPostIt);
        }
        else if(option.equals("c")){
            final String option1 = Console.readLine("Is the new content text or image? (t/i)");
            final Content content = new Content();
            if(option1.equals("t")){
                final String text = Console.readLine("Text of postit:");
                PostIt post = theController.changePostContent(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), content.createContentText(text),selectedPostIt);
                System.out.println("PostIt changed to"+ post.toString());
            }
            else if(option1.equals("i")){
                final String image = Console.readLine("Image of postit:");
                PostIt post =theController.changePostContent(theController.getUser(), selectedBoard.identity(),selectedPostIt.getRow().value(),selectedPostIt.getColumn().value(), content.createContentText(image),selectedPostIt);
                System.out.println("PostIt changed to"+ post.toString());
            }
            else{
                System.out.println("Invalid option");
            }
        }
        else{
            System.out.println("Invalid option");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Change PostIt";
    }
}

public class ChangePostItController
{

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();

    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItRepository repo2 = PersistenceContext.repositories().postIts();

    private final BoardCellRepository cellRepo = PersistenceContext.repositories().cells();


    private final PostItService postSvc = new PostItService(PersistenceContext.repositories().postIts());
    public PostIt changePostContent(SystemUser user, BoardTitle boardTitle, int row, int column, Content newContent, PostIt post)
    {

        BoardCell cell = cellRepo.getBoardCellByRowAndCol(new BoardRow(Integer.toString(row),"50"), new BoardCol(Integer.toString(column), "50"),post);
        //cell.getPost().setBackup(cell.getPost());
        Preconditions.ensure(cell.getPost().setContent(newContent),"Failure in changing post content.");
        cellRepo.save(cell);
        repo2.save(cell.getPost());
        return cell.getPost();
    }
    
    public void changePostPosition(SystemUser user,BoardTitle boardTitle, int row, int column, int rownew, int columnnew, PostIt post1)
    {
        Board board = repo.getBoardByTitle(boardTitle);
        
        //cell and post of location of relocation
        BoardCell cellnew = board.getCellByRowColumn(rownew, columnnew);
        PostIt postnew = cellnew.getPost();
    
        //if new cell location is empty
        Preconditions.ensure(postnew == null, "You can only relocate a post to an empty cell.");

        //cell and post of current location
        BoardCell cell = board.getCellByRowColumn(row, column);
        PostIt post = cell.getPost();
        //New post it info
        board.getCellByRowColumn(rownew, columnnew).setPost(post);
        board.getCellByRowColumn(rownew, columnnew).getPost().setBackup(new PostIt(board,cell,post.getOwner(),post.getContent()));
        //cell.setPost(null);
        //post.setCell(cellnew);
        //cellnew.setPost(post);
    
        Preconditions.ensure(post.relocate(cellnew), "Failure relocating the post.");
        repo2.save(post);
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
}
```