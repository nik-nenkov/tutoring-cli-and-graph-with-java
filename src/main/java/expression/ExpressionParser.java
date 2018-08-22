package expression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ExpressionParser {

    private static final String VALID_NUMBER_PATTERN = "([1-9]+[0-9]*\\.?[0-9]*)";
    private static final String VALID_OPERATOR_PATTERN = "([+\\-*/^])";

    static ExpressionTree parse(String input) {

//        if (input.matches(VALID_NUMBER_PATTERN)) {
//            return new ExpressionTree(new LeafNode(input));
//        }

        List<String> leaves = Arrays.asList(input.split(VALID_OPERATOR_PATTERN));
        List<String> inners = Arrays.asList(input.split(VALID_NUMBER_PATTERN));

        List<Node> leafNodes = leaves
                .stream()
                .map(String::trim)
                .filter(leaf -> !leaf.equals(""))
                .map(LeafNode::new)
                .collect(Collectors.toList());

        List<InnerNode> innerNodes = inners
                .stream()
                .map(String::trim)
                .filter(inner -> !inner.equals(""))
                .map(InnerNode::new)
                .collect(Collectors.toList());

//
//        System.out.println("\nState on current iteration:");
//        System.out.println(innerNodes);
//        System.out.println(leafNodes);

        int priorityLevel = 4;

        while (priorityLevel >= 0) {
            int len = innerNodes.size();
            for (int i = len - 1; i >= 0; i--) {
                if (innerNodes.get(i).getPriority() == priorityLevel) {
                    innerNodes.get(i).setLeftNode(leafNodes.get(i));
                    innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                    leafNodes.set(i, innerNodes.get(i));
                    innerNodes.remove(i);
                    leafNodes.remove(i + 1);

//                    System.out.println("\nState on current iteration:");
//                    System.out.println(innerNodes);
//                    System.out.println(leafNodes);
                }
            }
            priorityLevel--;
        }

        return new ExpressionTree(leafNodes.get(0));
    }
}
