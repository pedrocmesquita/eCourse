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
matchingPair: 'Question:' STRING (';Answer:' STRING)?;

multipleChoiceQuestion: 'MultipleChoice:' STRING option+;
option: 'Option:' STRING (';Weight: ' INT)? ;

shortAnswerQuestion: 'ShortAnswer:' STRING shortAnswer+;
shortAnswer: 'Answer:' STRING (';Grade: ' INT)?;

numericalQuestion: 'Numerical:' STRING numericalAnswer+;
numericalAnswer: 'Answer:' FLOAT (';AcceptedError: ' FLOAT)?;

selectMissingWordsQuestion: 'SelectMissingWords:' STRING itemGroup+;
itemGroup: 'Group:' STRING item+;
item: 'Item:' STRING;

trueFalseQuestion: 'TrueFalse' STRING trueFalseAnswer;
trueFalseAnswer: ';Answer:' ('True' | 'False')?;

STRING: [ a-zA-Z.]+;
FLOAT: DIGIT+ '.' DIGIT+;
INT: DIGIT+;
DIGIT: [0-9];
WS : [ \t\r\n]+ -> skip ;