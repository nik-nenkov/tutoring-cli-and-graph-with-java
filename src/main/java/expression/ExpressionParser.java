package expression;

import java.util.*;
import java.util.stream.Collectors;

import static utilities.Validator.*;

public class ExpressionParser {

    public static ExpressionTree parse(String input1) {
        Set<Node> treeNodes = new HashSet<>();
        String input = removeEmptySpaces(input1);
        List<Node> leafNodes = new ArrayList<>();
        List<InnerNode> innerNodes = new ArrayList<>();
        if (input.contains("(") && input.contains(")")) {
            int pointer = 0;
            int start = 0;
            int balance = 0;
            while (pointer < input.length()) {
                if (input.charAt(pointer) == '(') {
                    if (balance == 0) {
                        appendArraysWithExpression(input.substring(start, pointer), leafNodes, innerNodes);
                        start = pointer + 1;
                    }
                    balance++;
                    pointer++;
                } else if (input.charAt(pointer) == ')') {
                    balance--;
                    if (balance == 0) {
                        leafNodes.add(parse(input.substring(start, pointer)));
                        start = pointer + 1;
                    }
                    pointer++;
                } else {
                    pointer++;
                    if (pointer == input.length()) {
                        appendArraysWithExpression(input.substring(start, pointer), leafNodes, innerNodes);
                    }
                }
            }
            compute(treeNodes, leafNodes, innerNodes);
            return new ExpressionTree(leafNodes.get(0), treeNodes);
        } else {
            return parseExpressionTreeFromString(input);
        }
    }

    private static ExpressionTree parseExpressionTreeFromString(String input) {
        Set<Node> treeNodes = new HashSet<>();
        List<Node> leafNodes = new ArrayList<>();
        List<InnerNode> innerNodes = new ArrayList<>();
        appendArraysWithExpression(input, leafNodes, innerNodes);
        compute(treeNodes, leafNodes, innerNodes);
        return new ExpressionTree(leafNodes.get(0), treeNodes);
    }

    private static void compute(Set<Node> treeNodes, List<Node> leafNodes, List<InnerNode> innerNodes) {
        int priorityLevel = 4;
        treeNodes.addAll(
                applyFromRightToLeft(leafNodes, innerNodes, priorityLevel)
        );
        priorityLevel--;
        while (priorityLevel >= 0) {
            treeNodes.addAll(
                    applyFromLeftToRight(leafNodes, innerNodes, priorityLevel)
            );
            priorityLevel--;
        }
    }

    private static Set<Node> applyFromRightToLeft(List<Node> leafNodes, List<InnerNode> innerNodes, int priorityLevel) {
        Set<Node> addedNodes = new HashSet<>();
        for (int i = innerNodes.size() - 1; i >= 0; i--) {
            if (innerNodes.get(i).getPriority() == priorityLevel) {
                innerNodes.get(i).setLeftNode(leafNodes.get(i));
                innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                addedNodes.add(leafNodes.get(i + 1));
                addedNodes.add(innerNodes.get(i));

                leafNodes.set(i, innerNodes.get(i));
                leafNodes.remove(i + 1);
                innerNodes.remove(i);
            }
        }
        return addedNodes;
    }

    private static Set<Node> applyFromLeftToRight(List<Node> leafNodes, List<InnerNode> innerNodes, int priorityLevel) {
        Set<Node> addedNodes = new HashSet<>();
        for (int i = 0; i < innerNodes.size(); i++) {
            boolean changeWasMade;
            do {
                changeWasMade = false;
                if (innerNodes.get(i).getPriority() == priorityLevel) {
                    innerNodes.get(i).setLeftNode(leafNodes.get(i));
                    innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                    addedNodes.add(leafNodes.get(i));
                    addedNodes.add(innerNodes.get(i));

                    leafNodes.set(i + 1, innerNodes.get(i));
                    leafNodes.remove(i);
                    innerNodes.remove(i);

                    changeWasMade = true;
                }
            }
            while (changeWasMade && i < innerNodes.size());
        }
        return addedNodes;
    }

    private static void appendArraysWithExpression(String input, List<Node> leafNodes, List<InnerNode> innerNodes) {
        leafNodes.addAll(Arrays.stream(extractOperators(input))
                .map(String::trim)
                .filter(leaf -> !leaf.equals(""))
                .map(LeafNode::new)
                .collect(Collectors.toList()));

        innerNodes.addAll(Arrays.stream(extractDecimals(input))
                .map(String::trim)
                .filter(inner -> !inner.equals(""))
                .map(InnerNode::new)
                .collect(Collectors.toList()));
    }
}
