package expression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Validator.*;

class ExpressionParser {

    static ExpressionTree parseExpressionTreeFromString(String input) {

        List<Node> leafNodes = new ArrayList<>();
        List<InnerNode> innerNodes = new ArrayList<>();

        appendArraysWithExpression(input, leafNodes, innerNodes);

        int priorityLevel = 4;
        applyFromRightToLeft(leafNodes, innerNodes, priorityLevel);
        priorityLevel--;

        while (priorityLevel >= 0) {
            applyFromLeftToRight(leafNodes, innerNodes, priorityLevel);
            priorityLevel--;
        }

        return new ExpressionTree(leafNodes.get(0));
    }

    static ExpressionTree parseExpressionWithBrackets(String input1) {

        String input = input1.trim().replace(" ", "");
        List<Node> leafNodes = new ArrayList<>();
        List<InnerNode> innerNodes = new ArrayList<>();

        int balance = 0;
        int start = 0;

        if (input.contains("(") && input.contains(")") && hasValidOrderOfBrackets(input)) {


            for (int i = 0; i < input.length(); i++) {

                if (input.charAt(i) == '(') {
                    appendArraysWithExpression(input.substring(start, i), leafNodes, innerNodes);
                    start = i;
                    balance = 1;
                    while (balance > 0) {
                        i = i + 1;
                        balance = checkIfCharacterIsBracket(input.charAt(i));
                    }
                    System.out.println(input.substring(start, i - 1));
                    leafNodes.add(parseExpressionWithBrackets(input.substring(start, i - 1)));
                } else {
                    start = i;
                    while (i < input.length() && checkIfCharacterIsBracket(input.charAt(i)) == 0) {
                        i++;
                    }
                    System.out.println(input.substring(start, i));
                    appendArraysWithExpression(input.substring(start, i), leafNodes, innerNodes);
                }

            }

            int priorityLevel = 4;
            applyFromRightToLeft(leafNodes, innerNodes, priorityLevel);
            priorityLevel--;

            while (priorityLevel >= 0) {
                applyFromLeftToRight(leafNodes, innerNodes, priorityLevel);
                priorityLevel--;
            }

            return new ExpressionTree(leafNodes.get(0));

        } else {
            return parseExpressionTreeFromString(input);
        }
    }

    private static void appendArraysWithExpression(String input, List<Node> leafNodes, List<InnerNode> innerNodes) {
        leafNodes.addAll(Arrays.stream(input.split(VALID_OPERATOR_PATTERN))
                .map(String::trim)
                .filter(leaf -> !leaf.equals(""))
                .map(Node::new)
                .collect(Collectors.toList()));

        innerNodes.addAll(Arrays.stream(input.split(VALID_NUMBER_PATTERN))
                .map(String::trim)
                .filter(inner -> !inner.equals(""))
                .map(InnerNode::new)
                .collect(Collectors.toList()));
    }

    private static void applyFromRightToLeft(List<Node> leafNodes, List<InnerNode> innerNodes, int priorityLevel) {
        for (int i = innerNodes.size() - 1; i >= 0; i--) {
            if (innerNodes.get(i).getPriority() == priorityLevel) {
                innerNodes.get(i).setLeftNode(leafNodes.get(i));
                innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                leafNodes.set(i, innerNodes.get(i));
                leafNodes.remove(i + 1);
                innerNodes.remove(i);
            }
        }
    }

    private static void applyFromLeftToRight(List<Node> leafNodes, List<InnerNode> innerNodes, int priorityLevel) {
        for (int i = 0; i < innerNodes.size(); i++) {
            boolean changeWasMade;
            do {
                changeWasMade = false;
                if (innerNodes.get(i).getPriority() == priorityLevel) {
                    innerNodes.get(i).setLeftNode(leafNodes.get(i));
                    innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                    leafNodes.set(i + 1, innerNodes.get(i));
                    leafNodes.remove(i);
                    innerNodes.remove(i);

                    changeWasMade = true;
                }
            }
            while (changeWasMade && i < innerNodes.size());
        }
    }


    static void doSomething(String input) {
        String trimmed = input.replace(" ", "").trim();
        int pointer = 0;
        int balance = 0;
        while (pointer < trimmed.length()) {
            balance += checkIfCharacterIsBracket(trimmed.charAt(pointer));
            System.out.println("[" + pointer + "]" + "[" + balance + "] " + trimmed.charAt(pointer));
            pointer++;
        }
    }
}