package expression;

import java.util.*;
import java.util.stream.Collectors;

import static utilities.Validator.*;

public class ExpressionParser {

    private static void compute(List<Node> leafNodes, List<InnerNode> innerNodes) {
        int priorityLevel = 4;
        applyFromRightToLeft(leafNodes, innerNodes, priorityLevel);
        priorityLevel--;
        while (priorityLevel >= 0) {
            applyFromLeftToRight(leafNodes, innerNodes, priorityLevel);
            priorityLevel--;
        }
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

    public static ExpressionTree parseWithReference(String rawInput, String rawPosition) {
        ExpressionTree et = parse(rawInput);
        return new ExpressionTree(et.getRoot(), et.getNodes(), removeEmptySpaces(rawPosition));
    }

    static ExpressionTree parse(String rawInput) {

        String input = removeEmptySpaces(rawInput);

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
//                        newCrazyIdea(input.substring(start, pointer), leafNodes, innerNodes);
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
//                        newCrazyIdea(input.substring(start, pointer), leafNodes, innerNodes);
                    }
                }
            }

            Set<Node> treeNodes = new HashSet<>(leafNodes);
            treeNodes.addAll(innerNodes);

            compute(leafNodes, innerNodes);
            return new ExpressionTree(leafNodes.get(0), treeNodes);
        } else {
            return parseExpressionTreeFromString(input);
        }
    }

    private static ExpressionTree parseExpressionTreeFromString(String input) {

        List<Node> leafNodes = new ArrayList<>();
        List<InnerNode> innerNodes = new ArrayList<>();
        appendArraysWithExpression(input, leafNodes, innerNodes);
//        newCrazyIdea(input, leafNodes, innerNodes);

        Set<Node> treeNodes = new HashSet<>(leafNodes);
        treeNodes.addAll(innerNodes);

        compute(leafNodes, innerNodes);
        return new ExpressionTree(leafNodes.get(0), treeNodes);
    }

    private static void appendArraysWithExpression(String input, List<Node> leafNodes, List<InnerNode> innerNodes) {

        leafNodes.addAll(
                Arrays.stream(extractOperands(input))
                        .map(String::trim)
                        .filter(leaf -> !leaf.equals(""))
                        .map(LeafNode::new)
                        .collect(Collectors.toList())
        );

        innerNodes.addAll(
                Arrays.stream(extractOperators(input))
                        .map(String::trim)
                        .filter(inner -> !inner.equals(""))
                        .map(InnerNode::new)
                        .collect(Collectors.toList())
        );
    }

//    static void newCrazyIdea(
//            String input,
//            List<Node> leafNodes,
//            List<InnerNode> innerNodes){
//        char ch;
//        int i=0;
//        int begin=i;
//        i++;
//        while(i<input.length()){
//            ch = input.charAt(i);
//            if(ch=='+'||ch=='-'||ch=='^'||ch=='/'||ch=='*') {
//                if(!input.substring(begin, i).isEmpty())
//                {
//                    if(isValidOperator(input.substring(begin, i))){
//                        innerNodes.add(new InnerNode("" + input.charAt(i)));
//                    }else{
//                        leafNodes.add(new LeafNode(input.substring(begin, i)));
//                    }
//                }
//                innerNodes.add(new InnerNode("" + input.charAt(i)));
//                begin=i+1;
//            }
//            i++;
//        }
//        if((begin!=0&&i!=1)&&!input.substring(begin, i).isEmpty()){
//            leafNodes.add(new LeafNode(input.substring(begin, i)));
//        }
//    }
}
