package org.example;

import weka.classifiers.functions.Logistic;

public class LogisticRegressionClassifier extends BaseClassifier {
    private final Logistic logistic;

    public LogisticRegressionClassifier(String dataPath) throws Exception {
        super(dataPath);
        this.logistic = new Logistic();
        this.logistic.setMaxIts(1000);  // Increase from default 100
    }

    @Override
    public void train() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long startMem = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();
        this.logistic.buildClassifier(this.trainData);
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
        return this.logistic;
    }
}