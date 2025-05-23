package org.example;

import weka.core.Instances;
import weka.classifiers.Evaluation;
import java.util.Random;
import weka.core.converters.CSVLoader;
import java.io.File;

public abstract class BaseClassifier {
    protected Instances data;
    protected Instances trainData;
    protected Instances testData;
    protected long trainingTime;
    protected long predictionTime;
    protected double trainingMemory;
    protected double predictionMemory;

    public BaseClassifier(String dataPath) throws Exception {
        // Load CSV file directly
        CSVLoader loader = new CSVLoader();
        loader.setSource(new File(dataPath));
        this.data = loader.getDataSet();

        // Set the class index (assuming last column is the class/target)
        if (this.data.classIndex() == -1) {
            this.data.setClassIndex(this.data.numAttributes() - 1);
        }
        splitData(0.7); // 70% train, 30% test
    }

    protected void splitData(double trainRatio) throws Exception {
        this.data.randomize(new Random(System.currentTimeMillis()));  // Dynamic seed
        int trainSize = (int) Math.round(this.data.numInstances() * trainRatio);
        this.trainData = new Instances(this.data, 0, trainSize);
        this.testData = new Instances(this.data, trainSize, this.data.numInstances() - trainSize);
    }

    public abstract void train() throws Exception;
    public abstract void predict() throws Exception;

    public double getTrainingMemory() {
        return trainingMemory;
    }

    public double getPredictionMemory() {
        return predictionMemory;
    }

    public Evaluation evaluate() throws Exception {
        Runtime runtime = Runtime.getRuntime();
        long startMem = runtime.totalMemory() - runtime.freeMemory();
        long startTime = System.currentTimeMillis();
        Evaluation eval = new Evaluation(this.trainData);
        eval.evaluateModel(getClassifier(), this.testData);
        this.predictionTime = System.currentTimeMillis() - startTime;
        long endMem = runtime.totalMemory() - runtime.freeMemory();
        this.predictionMemory = (endMem - startMem) / (1024.0 * 1024.0); // Convert to MB
        return eval;
    }

    public abstract weka.classifiers.Classifier getClassifier();

    public long getTrainingTime() {
        return trainingTime;
    }

    public long getPredictionTime() {
        return predictionTime;
    }
}
