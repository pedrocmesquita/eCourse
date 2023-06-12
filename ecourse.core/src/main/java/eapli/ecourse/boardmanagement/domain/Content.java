package eapli.ecourse.boardmanagement.domain;

public class Content
{
    String text;
    String image;
    
    /**
     * Empty constructor, don't use
     */
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
