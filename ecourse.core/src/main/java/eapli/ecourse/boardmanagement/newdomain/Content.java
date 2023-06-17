package eapli.ecourse.boardmanagement.newdomain;

public class Content {
    private String text;
    private String image;

    public Content()
    {
        text = null;
        image = null;
    }

    public void createContentText(String content)
    {
        text = content;
        image = null;
    }

    public void createContentImage(String content)
    {
        text = null;
        image = content;
    }
}
