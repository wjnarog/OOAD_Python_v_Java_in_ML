package org.example;

import weka.classifiers.Classifier;
import weka.classifiers.functions.Logistic;
import weka.core.Instances;

/*
 * - Inheritance: Inherits from BaseClassifier.
 * - Polymorphism: Overrides train, predict, and getModel methods.
 * - Composition: Composes a Logistic model instead of inheriting from it.
 */
public class LogisticRegressionClassifier extends BaseClassifier {

    // Composition: Logistic model is composed
    private Classifier model;

    public LogisticRegressionClassifier(Instances trainingData) {
        super(trainingData);
        // Composition: Initialize the Logistic model
        model = new Logistic(); // Use Weka's Logistic Regression
    }

    @Override
    public void train() throws Exception {
        // Polymorphism: Specific implementation for training
        model.buildClassifier(trainingData);
    }

    @Override
    public double[] predict(Instances testData) throws Exception {
        double[] predictions = new double[testData.numInstances()];
        for (int i = 0; i < testData.numInstances(); i++) {
            // Polymorphism: Specific implementation for prediction
            predictions[i] = model.classifyInstance(testData.instance(i));
        }
        return predictions;
    }

    @Override
    protected Classifier getModel() {
        // Composition: Return the composed model
        return model;
    }
}