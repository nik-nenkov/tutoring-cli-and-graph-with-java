package expression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class ExpressionParser {

    private static final String VALID_NUMBER_PATTERN = "([1-9]+[0-9]*\\.?[0-9]*)";
    private static final String VALID_OPERATOR_PATTERN = "([+\\-*/^])";

    static ExpressionTree parse(String input) {

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

//        System.out.println("\nCurrent state:");
//        System.out.println(innerNodes);
//        System.out.println(leafNodes);

        int priorityLevel = 4;
        int len = innerNodes.size();
        for (int i = len - 1; i >= 0; i--) {
            if (innerNodes.get(i).getPriority() == priorityLevel) {
                innerNodes.get(i).setLeftNode(leafNodes.get(i));
                innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                leafNodes.set(i, innerNodes.get(i));
                leafNodes.remove(i + 1);
                innerNodes.remove(i);

//                System.out.println("\nCurrent state:");
//                System.out.println(innerNodes);
//                System.out.println(leafNodes);
            }
        }
        priorityLevel--;
        while (priorityLevel >= 0) {
            int i = 0;
            while (i < innerNodes.size()) {
                boolean changeWasMade = true;
                while (changeWasMade && i < innerNodes.size()) {
                    if (innerNodes.get(i).getPriority() == priorityLevel) {
                        innerNodes.get(i).setLeftNode(leafNodes.get(i));
                        innerNodes.get(i).setRightNode(leafNodes.get(i + 1));

                        leafNodes.set(i + 1, innerNodes.get(i));
                        leafNodes.remove(i);
                        innerNodes.remove(i);

//                        System.out.println("\nCurrent state:");
//                        System.out.println(innerNodes);
//                        System.out.println(leafNodes);
                    } else {
                        changeWasMade = false;
                    }
                }
                i++;
            }
            priorityLevel--;
        }


        return new ExpressionTree(leafNodes.get(0));
    }
}
