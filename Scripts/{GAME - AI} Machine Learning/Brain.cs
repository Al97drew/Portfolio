using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Brain : MonoBehaviour
{

    private Transform hex;

    private NeuralNetwork net;
    private Material[] mats;

    // Start is called before the first frame update
    void Start()
    {
        mats = new Material[transform.childCount];
        for (int i = 0; i < mats.Length; i++)
            mats[i] = transform.GetChild(i).GetComponent<Renderer>().material;
    }

    // Update is called once per frame
    void Update()
    {
        
    }
}
