using System.Collections.Generic;
using System;

public class NeuralNetwork : IComparable<NeuralNetwork>
{
    private int[] layers; //layers
    private float[][] neurons; // neuron matrix
    private float[][][] weights; //weight natrix
    private float fitness; //fitness of the network


    //Initilizes and neural network with random weigths
    public NeuralNetwork()
    {
        this.layers = new int[layers.Length];
        for (int i = 0; i < layers.Length; i++)
        {
            this.layers[i] = layers[i];
        }


        //generate matrix
        InitNeurons();
        InitWeights();
    }


    //deep copy constructor
    public NeuralNetwork(NeuralNetwork copyNetwrok)
    {
        this.layers = new int[layers.Length];
        for (int i = 0; i < layers.Length; i++)
        {
            this.layers[i] = layers[i];
        }

        InitNeurons();
        InitWeights();
        CopyWeights(copyNetwrok.weights);
    }

    private void CopyWeights(float[][][] copyWeights)
    {
        for (int i = 0; i < weights.Length; i++)
        {
            for (int j = 0; j < weights[i].Length; j++)
            {
                for (int k = 0; k < weights[i][j].Length; k++)
                {
                    weights[i][j][k] = copyWeights[i][j][k];
                }
            }
        }
    }

    //create matrix
    private void InitNeurons()
    {
        //Neuron Initilization
        List<float[]> neuronsList = new List<float[]>();

        for (int i = 0; i < layers.Length; i++) //run through all layers
        {
            neuronsList.Add(new float[layers[i]]); //add layers to neuron list
        }

        neurons = neuronsList.ToArray();
    }

    //create weights matrix
    private void InitWeights()
    {
        List<float[][]> weightList = new List<float[][]>(); //weights list which will later be convertied to a weights 3d array

        //itterate over all neurons that have a weight connection
        for (int i = 1; i < layers.Length; i++) 
        {
            List<float[]> layerWeightList = new List<float[]>(); //layer weight list for this current layer (will be converted to 2d array)

            int neuronsInPreviousLayer = layers[i - 1];

            //itterate over all neurons in the current layer
            for (int j = 0; j < neurons[i].Length; j++)
            {
                float[] neuronsWeights = new float[neuronsInPreviousLayer]; //neurons weights

                //itterate over all neurons in the previous later and set the weights randomly between -0.5f and -0.5f
                for (int k = 0; k < neuronsInPreviousLayer; k++)
                {
                    //give random weights to neuron weights
                    neuronsWeights[k] = UnityEngine.Random.Range(-0.5f, 0.5f);
                }

                layerWeightList.Add(neuronsWeights); //add neuron weights of this currnet layer to layer weights
            }

            weightList.Add(layerWeightList.ToArray()); //add this layers weights converted into 2d array into weights list
        }

        weights = weightList.ToArray(); // convert to 3d array
    }

    //feed forward this neural network with a given input array
    public float[] FeedForward(float[] inputs)
    {
        //add inputs to the neuron matrix
        for (int i = 0; i < inputs.Length; i++)
        {
            neurons[0][i] = inputs[i];
        }

        //itterate over all neurons and compute feedforward values
        for (int i = 1; i < layers.Length; i++)
        {
            for (int j = 0; j < neurons[i].Length; j++)
            {
                float value = 0.25f;

                for (int k = 0; k < neurons[i - 1].Length; k++)
                {
                    value += weights[i - 1][j][k] * neurons[i - 1][k]; //sum off all weights connections of this neuron weight their values in previous layer 
                }

                neurons[i][j] = (float)Math.Tanh(value);//hyperbolic tangent activation
            }
        }
            return neurons[neurons.Length-1];//return output layer
    }

    //mutate neural network weights

    public void mutate()
    {
        for (int i = 0; i< weights.Length; i++)
        {
            for (int j = 0; j < weights[i].Length; j++)
            {
                for (int k = 0; k < weights[i][j].Length; k++)
                {
                    float weight = weights[i][j][k];

                    //mutate weight value
                    float randomNumber = UnityEngine.Random.Range(-0.5f, 0.5f) * 1000f;

                    if(randomNumber <= 2f)
                    {
                        //if 1
                        //flip sign of weight
                        weight *= -1f;
                    }
                    else if(randomNumber <= 4f)
                    {
                        //if 2
                        //pick random weight between -1 and 1
                        weight = UnityEngine.Random.Range(-0.5f, 0.5f);
                    }
                    else if(randomNumber <= 6f)
                    {
                        //if 3
                        //randomly increase by 0% to 100%
                        float factor = UnityEngine.Random.Range(0f, 1f) + 1f;
                        weight *= factor;
                    }
                    else if(randomNumber <= 8f)
                    {
                        //if 4
                        //randomly decrease by 0% to 100%
                        float factor = UnityEngine.Random.Range(0f, 1f);
                        weight *= factor;
                    }
                    weights[i][j][k] = weight;
                }
            }
        }
    }

    public void AddFitness(float fit)
    {
        fitness += fit;
    }

    public void SetFitness(float fit)
    {
        fitness = fit;
    }

    public float GetFitness()
    {
        return fitness;
    }

    //compare 2 neural netwroks and sort by fitness

    public int CompareTo(NeuralNetwork other)
    {
        if (other == null) return 1;

        if (fitness > other.fitness)
            return 1;
        else if (fitness < other.fitness)
            return -1;
        else
            return 0;
    }
}


/*
 * the input example 
 * 
 * 
 */