package eapli.ecourse.boardmanagement.domain;

import eapli.framework.domain.model.ValueObject;

/**
 * Title class, used for board rows and columns
 */
public class Title
{
    String title;
    
    public Title(String title)
    {
        this.title = title;
    }
    
    public String getTitle()
    {
        return title;
    }
}
