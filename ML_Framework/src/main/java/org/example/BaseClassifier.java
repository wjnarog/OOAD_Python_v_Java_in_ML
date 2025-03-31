package org.example;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
    public EvaluationResult evaluate(Instances testData, double[] predictions,
                                    long trainingTimeNanos, long predictionTimeNanos) throws Exception {
        // Force GC and wait to reduce noise
        System.gc();
        Thread.sleep(100);

        // Measure memory BEFORE training
        long memoryBefore = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();

        // Train and predict (if not already done)
        train();
        predict(testData);

        // Measure memory AFTER
        long memoryAfter = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed();
        long memoryUsed = memoryAfter - memoryBefore;

        // Evaluation
        Evaluation eval = new Evaluation(testData);
        eval.evaluateModel(getModel(), testData);

        return new EvaluationResult(
                eval.pctCorrect(),
                trainingTimeNanos / 1_000_000.0, // Convert to ms
                predictionTimeNanos / 1_000_000.0, // Convert to ms
                memoryUsed / 1024.0 // Convert to KB
        );
    }

    // Abstraction: Subclasses must provide the model
    protected abstract Classifier getModel();


}