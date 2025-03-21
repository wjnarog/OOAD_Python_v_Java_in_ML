package org.example;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.util.Random;

/*
 * - Encapsulation: Encapsulates common functionality like evaluation and cross-validation.
 * - Abstraction: Defines abstract methods (train, predict, getModel) to hide implementation details.
 * - Inheritance: Subclasses inherit from this class and implement the abstract methods.
 */

public abstract class BaseClassifier {

    // Encapsulation: Data is protected
    protected Instances trainingData;

    public BaseClassifier(Instances trainingData) {
        this.trainingData = trainingData;
    }

    // Abstraction: Subclasses must implement these methods
    public abstract void train() throws Exception;

    public abstract double[] predict(Instances testData) throws Exception;

    /*
     * - DRY: Reusable method for evaluating accuracy.
     * - Encapsulation: Evaluation logic is encapsulated in this method.
     */
    public void evaluate(Instances testData, double[] predictions) throws Exception {
        Evaluation eval = new Evaluation(testData);
        for (int i = 0; i < testData.numInstances(); i++) {
            eval.evaluateModelOnce(predictions[i], testData.instance(i));
        }
        // System.out.println("Accuracy: " + eval.pctCorrect());
    }

    /*
     * - DRY: Reusable method for cross-validation.
     * - Encapsulation: Cross-validation logic is encapsulated in this method.
     */

    public void crossValidate(int folds) throws Exception {
        Evaluation eval = new Evaluation(trainingData);
        eval.crossValidateModel(getModel(), trainingData, folds, new Random(42));
//        System.out.println("Cross-Validation Accuracy: " + eval.pctCorrect() + "%");
        System.out.println("Accuracy: " + eval.pctCorrect() + "%");
    }

    // Abstraction: Subclasses must provide the model
    protected abstract Classifier getModel();
}