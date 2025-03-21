package org.example;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.core.Instances;

/*
 * - Inheritance: Inherits from BaseClassifier.
 * - Polymorphism: Overrides train, predict, and getModel methods.
 * - Composition: Composes an IBk model instead of inheriting from it.
 */

public class KNNClassifier extends BaseClassifier {
    // Composition: IBk model is composed
    private Classifier model;

    public KNNClassifier(Instances trainingData) {
        super(trainingData);
        // Composition: Initialize the IBk model
        model = new IBk(); // Use Weka's IBk (k-NN) classifier
        // Set k=3
        ((IBk) model).setKNN(3);
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
