package org.example;

import weka.core.Instances;
import weka.core.converters.CSVLoader;

import java.io.File;

/*
 * - Polymorphism: Treats all classifiers as BaseClassifier objects.
 * - Maintainability: Easy to add or remove classifiers.
 * - DRY/KISS: Reuses common functionality from BaseClassifier.
 */

public class Main {
    public static void main(String[] args) {
        try {
            // Load the dataset
            CSVLoader loader = new CSVLoader();
            loader.setSource(new File("src/main/resources/Input_Data/Iris.csv"));
            Instances dataset = loader.getDataSet();
            dataset.setClassIndex(dataset.numAttributes() - 1);

            // Split the dataset into training and testing sets (70% training, 30% testing)
            int trainSize = (int) Math.round(dataset.numInstances() * 0.7);
            int testSize = dataset.numInstances() - trainSize;
            Instances trainingData = new Instances(dataset, 0, trainSize);
            Instances testData = new Instances(dataset, trainSize, testSize);

            // Create and train SVM
            System.out.println("\nSVM:");
            SVMClassifier svmClassifier = new SVMClassifier(trainingData);
            svmClassifier.train();
            double[] predictions = svmClassifier.predict(testData);
            svmClassifier.evaluate(testData, predictions);
            svmClassifier.crossValidate(10);

            // Create and train Logistic Regression
            System.out.println("\nLogistic Regression:");
            LogisticRegressionClassifier logisticRegression = new LogisticRegressionClassifier(trainingData);
            logisticRegression.train();
            predictions = logisticRegression.predict(testData);
            logisticRegression.evaluate(testData, predictions);
            logisticRegression.crossValidate(10);

            // Create and train Decision Tree
            System.out.println("\nDecision Tree:");
            DecisionTreeClassifier decisionTree = new DecisionTreeClassifier(trainingData);
            decisionTree.train();
            predictions = decisionTree.predict(testData);
            decisionTree.evaluate(testData, predictions);
            decisionTree.crossValidate(10);



            // Create and train k-NN
            System.out.println("\nk-NN:");
            KNNClassifier knnClassifier = new KNNClassifier(trainingData);
            knnClassifier.train();
            predictions = knnClassifier.predict(testData);
            knnClassifier.evaluate(testData, predictions);
            knnClassifier.crossValidate(10);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}