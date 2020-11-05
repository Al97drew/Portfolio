using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MAttack : MonoBehaviour
{
    private float Timer = 2;
    private float hit;

    public void OnTriggerEnter(Collider col) // on trigger
    {
        if(col.gameObject.tag == "Player") // if hites player
        {
            if (hit + Timer < Time.time)
            {
                hit = Time.time;
                Debug.Log("hit");
                col.GetComponent<Health>().ModifyHealth(-10); // -10 health 
            }  
        }
    }

    public void OnTriggerExit(Collider col) // on exit
    {
        if(col.gameObject.tag == "Player")
        {
            Debug.Log("nooo");
        }
    }
}
