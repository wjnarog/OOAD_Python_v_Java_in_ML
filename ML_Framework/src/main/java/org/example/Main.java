package org.example;

import weka.classifiers.Evaluation;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        try {
            runClassification();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runClassification() throws Exception {
        int numRuns = 100;
        String dataPath = "src/main/resources/Input_Data/iris.csv";

        // Test all classifiers with proper randomization
        testClassifier("SVM", () -> {
            try {
                return new SVMClassifier(dataPath);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create SVM classifier", e);
            }
        }, numRuns);

        testClassifier("Logistic Regression", () -> {
            try {
                return new LogisticRegressionClassifier(dataPath);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create Logistic Regression classifier", e);
            }
        }, numRuns);

        testClassifier("Decision Tree", () -> {
            try {
                return new DecisionTreeClassifier(dataPath);
            } catch (Exception e) {
                throw new RuntimeException("Failed to create Decision Tree classifier", e);
            }
        }, numRuns);
    }

    private static void testClassifier(String classifierName,
                                       Supplier<BaseClassifier> classifierSupplier,
                                       int numRuns) {
        System.out.println("\n" + classifierName);
        PerformanceStats stats = new PerformanceStats();

        for (int i = 0; i < numRuns; i++) {
            try {

                // Create fresh classifier instance (forces new data split)
                BaseClassifier classifier = classifierSupplier.get();

                // Train and evaluate
                classifier.train();
                Evaluation eval = classifier.evaluate();

                // Collect metrics
                stats.addRun(eval.pctCorrect(),
                        classifier.getTrainingTime(),
                        classifier.getPredictionTime());

            } catch (Exception e) {
                System.err.printf("Error in run %d: %s%n", i, e.getMessage());
                e.printStackTrace();
                continue; // Skip this run and continue with the next
            }
        }

        // Print final averages
        System.out.println("\nFinal Statistics:");
        System.out.printf("Average Training time: %.3f ms%n", stats.getAverageTrainTime());
        System.out.printf("Average Prediction time: %.3f ms%n", stats.getAveragePredictTime());
        System.out.printf("Average Accuracy: %.2f%%%n", stats.getAverageAccuracy());
        System.out.printf("(averaged over %d runs.)%n%n", stats.getRuns());
    }
}