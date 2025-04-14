package org.example;

import weka.classifiers.trees.J48;

public class DecisionTreeClassifier extends BaseClassifier {
    private final J48 decisionTree;

    public DecisionTreeClassifier(String dataPath) throws Exception {
        super(dataPath);
        this.decisionTree = new J48();
    }

    @Override
    public void train() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long startMem = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();
        this.decisionTree.buildClassifier(this.trainData);
        this.trainingTime = System.currentTimeMillis() - startTime;
        long endMem = runtime.totalMemory() - runtime.freeMemory();
        this.trainingMemory = (endMem - startMem) / (1024.0 * 1024.0); // Convert to MB
    }

    @Override
    public void predict() throws Exception {
        // Prediction is handled in evaluate()
    }

    @Override
    public weka.classifiers.Classifier getClassifier() {
        return this.decisionTree;
    }
}
