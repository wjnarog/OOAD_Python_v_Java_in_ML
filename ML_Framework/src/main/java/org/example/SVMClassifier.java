package org.example;

import weka.classifiers.functions.SMO;

public class SVMClassifier extends BaseClassifier {
    private final SMO svm;

    public SVMClassifier(String dataPath) throws Exception {
        super(dataPath);
        this.svm = new SMO();
    }

    @Override
    public void train() throws Exception {
        long startTime = System.currentTimeMillis();
        this.svm.buildClassifier(this.trainData);
        this.trainingTime = System.currentTimeMillis() - startTime;
    }

    @Override
    public void predict() throws Exception {
        // Prediction is handled in evaluate()
    }

    @Override
    public weka.classifiers.Classifier getClassifier() {
        return this.svm;
    }
}
