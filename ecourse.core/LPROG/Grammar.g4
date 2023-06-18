grammar Grammar;

// Grammar rules
prog: title header section+;

title: 'Title:' STRING;

header: 'Header:' feedbackType gradeType;

feedbackType: 'Feedback: ' feedbackOption;
feedbackOption: 'None' | 'On-Submission' | 'After-Closing';

gradeType: 'Grade: ' gradeOption;
gradeOption: 'None' | 'On-Submission' | 'After-Closing';

section: 'Section:' sectionName description question+;

sectionName: STRING;
description: 'Description:' STRING;

question: matchingQuestion | multipleChoiceQuestion | shortAnswerQuestion | numericalQuestion | selectMissingWordsQuestion | trueFalseQuestion;

matchingQuestion: 'Matching:' STRING matchingPair+;
matchingPair: 'Question:' STRING answer;
answer: 'Answer:' STRING;

multipleChoiceQuestion: 'MultipleChoice:' STRING option+;
option: 'Option:' STRING weight?;
weight: 'Weight: ' INT ;

shortAnswerQuestion: 'ShortQuestion:' STRING shortAnswer+;
shortAnswer: 'Answer:' STRING grade?;
grade: 'Grade: ' INT;

numericalQuestion: 'Numerical:' STRING numericalAnswer+;
numericalAnswer: 'Answer:' FLOAT acceptedError?;
acceptedError: 'AcceptedError: ' FLOAT;

selectMissingWordsQuestion: 'SelectMissingWords:' STRING itemGroup+;
itemGroup: 'Group:' STRING item;
item: 'Item:' STRING;

trueFalseQuestion: 'TrueFalse' STRING trueFalseAnswer;
trueFalseAnswer: 'Answer:' ('True' | 'False');

STRING: [ a-zA-Z.]+;
FLOAT: DIGIT+ '.' DIGIT+;
INT: DIGIT+;
DIGIT: [0-9];
WS : [ \t\r\n]+ -> skip ;
