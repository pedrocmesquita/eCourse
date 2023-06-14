import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.Trees;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        int option;
        String keepFilename = "";
        int keepQuestionName;

        Scanner sc = new Scanner(System.in);

        System.out.println("What do you want to do?\n");
        System.out.println("1.Create an exam");
        System.out.println("2.Update an exam");
        System.out.println("3.Add exam question");
        System.out.println("4.Update exam question\n");
        System.out.println("Type your option:");

        try {
            option = Integer.parseInt(sc.nextLine());
        } catch (InputMismatchException e) {
            sc.next();
            System.out.println("Invalid input. Please enter a valid option (an integer).");
            option = -1;
        }

        switch (option) {
            case 1:
                System.out.println("Please input the name of the file with the exam");
                keepFilename = sc.nextLine();

                try (FileInputStream fis = new FileInputStream(keepFilename)) {
                    GrammarLexer lexer = new GrammarLexer(CharStreams.fromStream(fis));
                    CommonTokenStream tokens = new CommonTokenStream(lexer);
                    GrammarParser parser = new GrammarParser(tokens);
                    ParseTree tree = parser.prog();

                    CustomGrammarListener customGrammarListener = new CustomGrammarListener();

                    // Write the exam to a text file
                    ParseTreeWalker walker = new ParseTreeWalker();

                    walker.walk(customGrammarListener, tree);
                    writeExamToFile(customGrammarListener);
                }
                    break;
            case 2:
                System.out.println("Which exam you wanna update?");
                break;
            case 3:
                System.out.println("Which type of question you want to add?");
                System.out.println("1.Matching");
                System.out.println("2.Multiple Choice");
                System.out.println("3.Numerical");
                System.out.println("4.Select Missing Words");
                System.out.println("5.Short Answer");
                System.out.println("6.True/False");
                System.out.println("Type your option:");

                try {
                    keepQuestionName = Integer.parseInt(sc.nextLine());
                } catch (InputMismatchException e) {
                    sc.next();
                    System.out.println("Invalid input. Please enter a valid option (an integer).");
                    keepQuestionName = -1;
                }

                switch (keepQuestionName) {
                    case 1:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                    case 2:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                    case 3:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                    case 4:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                    case 5:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                    case 6:
                        System.out.println("Question text:");
                        System.out.println("");
                        break;
                }
                break;
            case 4:
                System.out.println("Which type of question you want to update?");
                break;
        }

    }
    private static void writeExamToFile(CustomGrammarListener customGrammarListener) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            // Write exam title
            writer.write(customGrammarListener.getTitle());
            writer.newLine();
            writer.newLine();

            // Write exam header
            writer.write( "Feedback: " + customGrammarListener.getFeedbackType() + "\n" + "Grade: " + customGrammarListener.getGradeType());
            writer.newLine();
            writer.newLine();

            // Write exam section

            for (String sections : customGrammarListener.getSections()) {
                writer.write("Section: " + sections + "\n");
                for(String descriptions: customGrammarListener.getDescriptions()){
                    writer.write(descriptions + "\n");
                    for(String multipleQuestion: customGrammarListener.getMultipleQuestions()){
                        writer.write("Multiplechoice Question: " + multipleQuestion + "\n");
                        for(String options: customGrammarListener.getOptions()){
                            writer.write(options + "\n");
                        }
                    }

                }
            }

        }
    }
}


