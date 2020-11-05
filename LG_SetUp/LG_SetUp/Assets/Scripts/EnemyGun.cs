using UnityEngine;

public class EnemyGun : MonoBehaviour
{
    [SerializeField]
    Transform fireingPoint; // fireing point

    [SerializeField]
    GameObject projectilePrefab; // bullet prefab

    [SerializeField]
    float fireingSpeed; // fire rate

    private float lastShot = 0; 

    void Update()
    {
        Shoot(); // shoot
    }
    public void Shoot()
    {
        if (lastShot + fireingSpeed <= Time.time) // checks when last shot was if > than fire rate then
        {
            lastShot = Time.time; // reset last shot
            Instantiate(projectilePrefab, fireingPoint.position, fireingPoint.rotation); // create bullet
        }
    }
}
