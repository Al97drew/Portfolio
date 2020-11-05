using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Manager : MonoBehaviour
{
    private bool isTraning = false;
    private int populationSize = 50;
    private int generationNumber = 0;
    private int[] layers = new int[] { 1, 10, 10, 1 }; //1 input and 1 output
    private List<NeuralNetwork> nets;
    private bool leftMouseDown = false;

    void Timer()
    {
        isTraning = false;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
