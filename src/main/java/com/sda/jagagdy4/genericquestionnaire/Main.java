package com.sda.jagagdy4.genericquestionnaire;

import com.sda.jagagdy4.genericquestionnaire.model.Question;
import lombok.extern.log4j.Log4j;

import java.util.Map;
import java.util.Scanner;

@Log4j
public class Main {
    public static void main(String[] args) {Scanner scanner = new Scanner(System.in);
        String command;
        do {
            log.info("Please enter command [listQuestions]:");
            command = scanner.nextLine();
            if (command.startsWith("listQuestions")) {
                handleLoadAndPrintQuestions();
            } else if (command.startsWith("addQuestion")) {
                handleAddQuestion(command);
            }
        } while (!command.equalsIgnoreCase("quit"));
    }
    private static void handleAddQuestion(String command) {
        FileLoader<Question> questionFileLoader = new FileLoader<>(Question.class);
        String[] words = command.split(" ", 3);
        Long questionIdentifier = Long.valueOf(words[1]);
        String questionContent = words[2];
        Question question = new Question(questionContent);
        Map<Long, Question> questionsFromFile = questionFileLoader.load();
        questionsFromFile.put(questionIdentifier, question);
        questionFileLoader.save(questionsFromFile);
    }
    private static void handleLoadAndPrintQuestions() {
        FileLoader<Question> questionFileLoader = new FileLoader<>(Question.class);
        Map<Long, Question> questionMap = questionFileLoader.load();
        // map.values()
        // map.keySet()
        // map.entrySet()
        for (Map.Entry<Long, Question> longQuestionEntry : questionMap.entrySet()) {
            log.info(longQuestionEntry.getKey() + " -> " + longQuestionEntry.getValue());
        }
    }
}
