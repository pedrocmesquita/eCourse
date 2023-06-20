# US 3006

## 1. Context

As a User I want to create a post-it on a board.

## 2. Requirements

As User, I want to create a post-it on a board.

Dependencies:

**US 3002:** As User, I want to create a board.


## 3. Analysis

Information in System Specification

    Users with write permission may post content to a cell in the board. The content can be a text or an image. When the server commits a post it also should notify all clients with access to the board of the update.

Information in Forum

    "Uma célula pode ter mais do que um post it?" 

    Neste momento (no âmbito deste projeto) isso não será necessário. A ser possível (uma célula com mais do que 1 post-it) isso iria dificultar algumas funcionalidades, como a que permite mudar um post-it.

    "As stated in the system specifications document, "Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number", we wanted to clarify if it should be possible to have a column with no title and no identifier.Also, can the identifier of a column/row change once it's assigned?"

    In order for user to post content into a cell they must identify the cell. Therefore, I think at least, ir should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number.
    Regarding changing the title of the columns and rows after creating the board, there is nothing explicit about that. Therefore, I would accept the solution that does not support that possibility.

    "Quando um User cria um post-it deve passar um link da imagem por exemplo: "https://www.isep.ipp.pt/img/logo_20230106.png" Ou devemos anexar uma imagem que está no nosso computador?"

    Para o cliente é um pouco indiferente o mecanismo que usam para fazer o "post" de imagens (assim como o(s) formato(s) suportado(s)).
    Podem optar pela solução que for mais simples. Suponho que suportar o "upload" de imagens locais e suportar um formato comum, como png ou jpeg, seja suficiente

## 4. Design


## 5. Implementation

```java

public class CreatePostItsUI extends AbstractUI {

    private final CreatePostItController theController = new CreatePostItController();

    private final AccessLevel accessLevel = AccessLevel.WRITE;
    private final SelectBoardWidget boardWidget = new SelectBoardWidget(theController.findBoards(theController.getUser(), accessLevel));

    @Override
    protected boolean doShow() {
        System.out.println("Select a Board to create a postit in ");
        final Board selectedBoard = boardWidget.selectBoard();
        if (selectedBoard == null)
            return false;
        final int col = Console.readInteger("Column of postit:");
        final int row = Console.readInteger("Row of postit:");
        final String option = Console.readLine("Is the content text or image? (t/i)");
        if(option.equals("t")){
            final String text = Console.readLine("Text of postit:");
            System.out.println(theController.attemptCreatePost(selectedBoard, row, col, text, null).toString());
        }
        else if(option.equals("i")){
            final String image = Console.readLine("Image of postit:");
            System.out.println(theController.attemptCreatePost(selectedBoard, row, col, null, image).toString());
        }
        else{
            System.out.println("Invalid option");
        }
        return true;
    }

    @Override
    public String headline() {
        return "Create PostIt";
    }

}


public class CreatePostItController
{
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final BoardRepository repo = PersistenceContext.repositories().boards();

    private final BoardPermissionRepository repo1 = PersistenceContext.repositories().boardPermissions();

    private final PostItService postSvc = new PostItService(PersistenceContext.repositories().postIts());

    private final BoardCellRepository cellRepo = PersistenceContext.repositories().cells();

    public PostIt attemptCreatePost(Board board, int row, int column, String text, String image)
    {
        Optional<UserSession> session = authz.session();
        SystemUser user = session.get().authenticatedUser();
        //if cell exists
        Preconditions.ensure(board.getCellByRowColumn(row, column) != null, "Specified cell in row" + row + "column"+column +" doesn't exist.");
        Content cont = new Content();
        //create content with text, if it exists
        if (text != null)
        {
            cont.createContentText(text);
        }
        
        //otherwise, create content with image
        else if (image != null)
        {
            cont.createContentText(image);
        }
        return postSvc.createPostIt(board, board.getCellByRowColumn(row, column), user, cont);
    }
    public Iterable<Board> findBoards(SystemUser user, AccessLevel accessLevel) {
        Iterable<Board> board1 = findBoardsByOwner(user);
        Iterable<Board> board2 = findBoardsByUserPermission(user, accessLevel);
        Set<Board> boards1 = new HashSet<>();
        Set<Board> boards2 = new HashSet<>();
        board1.forEach(boards1::add);
        board2.forEach(boards2::add);
        boards1.addAll(boards2);
        return boards1;
    }


    public Iterable<Board> findBoardsByOwner(SystemUser user) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        return repo.findByOwner(user);
    }
    public Iterable<Board> findBoardsByUserPermission(SystemUser user, AccessLevel accessLevel) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.TEACHER, BaseRoles.POWER_USER, BaseRoles.ADMIN, BaseRoles.STUDENT);
        Iterable<BoardPermission> permission = repo1.findBySystemUserAccessLevel(user, accessLevel);
         Set<Board> boards = new HashSet<>();

        for(BoardPermission bp : permission)
            boards.add(repo.findByBoardPermission(bp));
        return boards;
    }
    public SystemUser getUser(){
        Optional<UserSession> session = authz.session();
        if(session.isEmpty())
            throw new IllegalArgumentException("No user authentication");
        SystemUser user = session.get().authenticatedUser();
        return user;
    }



    }

```