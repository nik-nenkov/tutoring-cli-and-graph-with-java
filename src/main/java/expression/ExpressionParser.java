package expression;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static utilities.Validator.*;

class ExpressionParser {

    static ExpressionTree parseExpressionTreeFromString(String input) {

        List<Node> leafNodes = Arrays.stream(input.split(VALID_OPERATOR_PATTERN))
                .map(String::trim)
                .filter(leaf -> !leaf.equals(""))
                .map(LeafNode::new)
                .collect(Collectors.toList());

        List<InnerNode> innerNodes = Arrays.stream(input.split(VALID_NUMBER_PATTERN))
                .map(String::trim)
                .filter(inner -> !inner.equals(""))
                .map(InnerNode::new)
                .collect(Collectors.toList());

        int priorityLevel = 4;
        applyFromRightToLeft(leafNodes, innerNodes, priorityLevel);
        priorityLevel--;
        while (priorityLevel >= 0) {
            applyFromLeftToRight(leafNodes, innerNodes, priorityLevel);
            priorityLevel--;
        }
        return new ExpressionTree(leafNodes.get(0));
    }

    public static void parseExpressionWithBrackets(String input) {
        if (input.contains("(") && input.contains(")") && hasValidOrderOfBrackets(input)) {

            //TODO something here

        } else throw new InvalidParameterException();
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
}