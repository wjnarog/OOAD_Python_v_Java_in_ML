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
        long startTime = System.currentTimeMillis();
        this.logistic.buildClassifier(this.trainData);
        this.trainingTime = System.currentTimeMillis() - startTime;
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