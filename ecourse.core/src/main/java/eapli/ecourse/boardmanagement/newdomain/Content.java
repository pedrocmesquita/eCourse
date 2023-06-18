package eapli.ecourse.boardmanagement.newdomain;

public class Content {
    private String text;
    private String image;

    public Content()
    {
        text = null;
        image = null;
    }

    public Content createContentText(String content)
    {
        text = content;
        image = null;
        return this;
    }

    public Content createContentImage(String content)
    {
        text = null;
        image = content;
        return this;
    }
}
