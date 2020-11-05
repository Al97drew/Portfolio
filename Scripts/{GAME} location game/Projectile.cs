using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Projectile : MonoBehaviour
{
    private Vector3 fireingPoint; // fireing point

    [SerializeField]
    private float projectileSpeed; // bullet speed

    [SerializeField]
    private float maxProjectileDistance; // distance it can travel
    void Start()
    {
        fireingPoint = transform.position; // get firing point
    }

    void Update()
    {
        Moveprojectile(); // move nullet
    }
    
    void Moveprojectile()
    {
        if (Vector3.Distance(fireingPoint, transform.position) > maxProjectileDistance) // if further than max distance
        {
            Destroy(this.gameObject); // destroy self
        }
        else
        {
            transform.Translate(Vector3.forward * projectileSpeed * Time.deltaTime); // move forwards
        }
    }



}
