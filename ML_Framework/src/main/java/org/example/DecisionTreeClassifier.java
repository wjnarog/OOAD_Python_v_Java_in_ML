package org.example;

import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;

/*
 * - Inheritance: Inherits from BaseClassifier.
 * - Polymorphism: Overrides train, predict, and getModel methods.
 * - Composition: Composes a J48 model instead of inheriting from it.
 */
public class DecisionTreeClassifier extends BaseClassifier {

    // Composition: J48 model is composed
    private Classifier model;

    public DecisionTreeClassifier(Instances trainingData) {
        super(trainingData);
        // Composition: Initialize the J48 model
        model = new J48(); // Use Weka's J48 Decision Tree
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