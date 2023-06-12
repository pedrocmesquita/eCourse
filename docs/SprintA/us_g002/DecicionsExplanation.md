## Taken Decision

This document has the objective to explain the most important decisions made by the team
in the Domain Model

* **Joining enrolled students with enrollment request**:
  there is no need, according to the provided document and existing use cases,
  to save denied enrollment request, therefore once they are denied they can be deleted.
  For a course to be in progress, the enrolemet fase needs to be over, therefore, when a
  course starts, all students in the table will be in state accepted.