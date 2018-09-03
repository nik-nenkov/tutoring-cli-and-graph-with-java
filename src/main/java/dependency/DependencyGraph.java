package dependency;

import expression.ExpressionTree;
import expression.LeafNode;
import table.Cell;
import table.Table;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DependencyGraph {

    private final Table referenceTable;
    private Set<Cell> independentNodes;
    private Map<Cell, Set<Cell>> inDegree;
    private Map<Cell, Set<Cell>> outDegree;

    public DependencyGraph(Table referenceTable) {
        this.inDegree = new HashMap<>();
        this.outDegree = new HashMap<>();
        this.referenceTable = referenceTable;
        this.independentNodes = new HashSet<>();
        LeafNode.setReferenceTable(referenceTable);
        ExpressionTree.setReferenceTable(referenceTable);
    }

    public void addDependency(String position, String dependency) {
        Cell dependant = referenceTable.getCell(position);
        Cell dependencyToAdd = referenceTable.getCell(dependency);
        if (dependant != null &&
                dependencyToAdd != null &&
                inDegree.containsKey(dependant)) {
            inDegree.get(dependant).add(dependencyToAdd);
        }
    }

    public void calculate() {
        referenceTable.getCells().forEach(cell -> {
            if (cell.getExpressionTree() != null && cell.getExpressionTree().isReference()) {
                findDependencies(cell.getExpressionTree());
                calculateValue(cell);
            }
        });
    }

    private void calculateValue(Cell cell) {
        ExpressionTree et = cell.getExpressionTree();
        cell.setValue(et.getValue());
    }

    private void findDependencies(ExpressionTree et) {
        if (et.isReference()) {
            final Cell ref = referenceTable.getCell(et.getReference());
            inDegree.put(ref, new HashSet<>());
            et.getNodes().forEach(node -> {
                if (node.isReference()) {
                    inDegree.get(ref).add(referenceTable.getCell(node.getReference()));
                }
            });
        }
    }
}
