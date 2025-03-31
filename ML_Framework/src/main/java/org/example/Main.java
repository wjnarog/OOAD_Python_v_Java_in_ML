package org.example;

import weka.core.Instances;
import weka.core.converters.CSVLoader;
import weka.classifiers.Evaluation;

import java.io.File;
import java.util.Random;

public class Main {
    private static final double TRAIN_TEST_SPLIT_RATIO = 0.7;
    private static final int EVALUATION_RUNS = 100;
    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) {
        try {
            // Load the dataset
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("src/main/resources/Input_Data/Iris.csv"));
            Instances dataset = loader.getDataSet();
            dataset.setClassIndex(dataset.numAttributes() - 1);

            // Shuffle and split data
            dataset.randomize(RANDOM);
            int trainSize = (int) Math.round(dataset.numInstances() * TRAIN_TEST_SPLIT_RATIO);
            int testSize = dataset.numInstances() - trainSize;
            Instances trainingData = new Instances(dataset, 0, trainSize);
            Instances testData = new Instances(dataset, trainSize, testSize);

            // Evaluate each classifier with 100 runs
            System.out.println("\nSVM:");
            evaluateWithRuns(new SVMClassifier(trainingData), testData, EVALUATION_RUNS);

            System.out.println("\nLogistic Regression:");
            evaluateWithRuns(new LogisticRegressionClassifier(trainingData), testData, EVALUATION_RUNS);

            System.out.println("\nDecision Tree:");
            evaluateWithRuns(new DecisionTreeClassifier(trainingData), testData, EVALUATION_RUNS);

            System.out.println("\nk-NN:");
            evaluateWithRuns(new KNNClassifier(trainingData), testData, EVALUATION_RUNS);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void evaluateWithRuns(BaseClassifier classifier, Instances testData, int runs) throws Exception {
        PerformanceStats stats = new PerformanceStats();
        String classifierName = classifier.getClass().getSimpleName();

        // Main runs
        for (int i = 0; i < runs; i++) {
            BaseClassifier freshClassifier = classifier.getClass()
                    .getConstructor(Instances.class)
                    .newInstance(classifier.trainingData);
            long trainStart = System.nanoTime();
            freshClassifier.train();
            long trainTime = System.nanoTime() - trainStart;

            long predictStart = System.nanoTime();
            double[] predictions = freshClassifier.predict(testData);
            long predictTime = System.nanoTime() - predictStart;

            EvaluationResult result = freshClassifier.evaluate(testData, predictions, trainTime, predictTime);
            stats.addRun(result.getAccuracy(), trainTime, predictTime, result.getMemoryUsageKb());
        }

        // Print average metrics after all runs
        System.out.println("\n" + classifierName);
        System.out.printf("Average Training time: %.3f ms\n", stats.getAverageTrainingTimeMillis());
        System.out.printf("Average Memory usage during training: %.2f KB\n", stats.getAverageMemoryUsageKB());
        System.out.printf("Average Prediction time: %.3f ms\n", stats.getAveragePredictionTimeMillis());
        System.out.printf("The average accuracy of the %s is: %.2f%%\n",
                classifierName, stats.getAverageAccuracy());
        System.out.println("(Averaged over " + runs + " runs)");
    }
}