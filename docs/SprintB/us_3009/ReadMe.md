# US 3009 - View Board History

As User, I want to view the history of updates on a board.

## 1. Context

The goal of this US is to provide a way to view a list of all changes done to a board.

## 2. Requirements

* FRB07 - View Board History A user views a history of updates in a board.
* The system should maintain an history/log of all the updates in the board.
* The user should have Read permissions on the board.

## 3. Analysis

Information in System Specification

    The system should maintain an history/log of all the updates in the board.

Information in Forum

    n/a

## 4. Design

### 4.1. Realization

#### 4.1.1. Sequence Diagram

### 4.2. Class Diagram

### 4.3. Applied Patterns

#### 4.3.1. Factory

#### 4.3.2 Service

#### 4.3.3 Single Responsibility Principle (SRP)

#### 4.3.4 Tell, Don't Ask

#### 4.3.5 Model-View-Controller (MVC)

- View is responsible for presenting the data to the user in a human-readable format. (ViewBoardHistoryUI)
- Controller is responsible for handling the user input and updating the model and the view accordingly. (ViewBoardHistoryController)

### 4.4. Tests

### 5. Implementation

ViewBoardUI

    @Override
    protected boolean doShow()
    {
        System.out.println("Choose a Board to view the history of:");
        
        final Board selectedBoard = boardWidget.selectBoard();
        
        if (selectedBoard == null)
        {
            return false;
        }
        
        try
        {
            Iterable<Log> logs = controller.listBoardLogs(selectedBoard);
            
            if (logs.iterator().hasNext())
            {
                for (Log log : logs)
                {
                    System.out.println(log.toString());
                }
            }
            else
            {
                System.out.println("There are no logs on this Board.");  //shouldnt happen, theres a log for creation
            }
            
        } catch (IllegalArgumentException iae)
        {
            System.out.println(iae.getMessage());
        }
        
        return true;
    }

ViewBoardController

    public Iterable<Log> listBoardLogs(Board board)
    {
        return board.getLogs();
    }

### 6. Integration/Demonstration

### 7. Observations

Events recorded in logs:
* Creating a board (means a board should never have empty logs, since one is added on creation)
* Sharing a board
* Creating a post-it
* Moving a post-it
* Changing a post-it (altering it's content)