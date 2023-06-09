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

### 6. Integration/Demonstration



### 7. Observations

Example of BoardEntry:

| Coluna 1 & Linha 1 | Coluna 2 & Linha 1 | Coluna 3 & Linha 1 |
|:------------------:|:------------------:|:------------------:|
| Coluna 1 & Linha 2 |                    |                    |
| Coluna 1 & Linha 3 |                    |                    |
|                    |                    |                    |