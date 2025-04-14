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
        Runtime runtime = Runtime.getRuntime();
        long startMem = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();
        this.svm.buildClassifier(this.trainData);
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
        return this.svm;
    }
}
