using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerGun : MonoBehaviour
{
    [SerializeField]
    Transform fireingPoint;

    [SerializeField]
    GameObject projectilePrefab;

    [SerializeField]
    float fireingSpeed;

    public static PlayerGun Instance;

    private float lastShot = 0;

    void Awake()
    {
        Instance = GetComponent<PlayerGun>(); // get the gun
    }

    public void Shoot()
    {
        if (lastShot + fireingSpeed <= Time.time) // workout last shot
        {
            lastShot = Time.time; // save new last shot time
            Instantiate(projectilePrefab, fireingPoint.position, fireingPoint.rotation); // fire
        }
    }
}
