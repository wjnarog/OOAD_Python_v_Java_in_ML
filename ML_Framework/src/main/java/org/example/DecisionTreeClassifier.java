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
        long startTime = System.currentTimeMillis();
        this.decisionTree.buildClassifier(this.trainData);
        this.trainingTime = System.currentTimeMillis() - startTime;
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
