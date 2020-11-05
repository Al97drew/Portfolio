using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CameraController : MonoBehaviour
{

    [SerializeField]
    private Transform target; //target for follow
    [SerializeField]
    private Vector3 targetOffset; //off set (how far for the target to move before following
    [SerializeField]
    private float movementSpeed; // sepeed of movemnt


    void Start()
    {
        
    }



    void Update()
    {
        MoveCamera();
    }

    void MoveCamera()
    {
        try
        {
            //move position to target
            transform.position = Vector3.Lerp(transform.position, target.position + targetOffset, movementSpeed * Time.deltaTime);
        }
        catch
        {

        }
    }
}
