using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class SpawnGardians : MonoBehaviour
{

    public GameObject EnemyGardianPrefab;

    public Vector3 size;
    public float localposx;
    public float localposz;
    public float timer = 30;

    void Start()
    {
        for (int i = 0; i <= 5; i++) // loop 5 times
        {
            SpawnGardian();
        }
    }

    public void SpawnGardian()
    {
        localposx = transform.localPosition.x; // get object local position
        localposz = transform.localPosition.z; // get object local position
        Vector3 pos = new Vector3(Random.Range(localposx - 10, localposx + 10), (float)1.77, Random.Range(localposz  - 10, localposz + 10)); // spawn randomly within area

        Instantiate(EnemyGardianPrefab, pos, Quaternion.identity); // Spawn
    }

    void OnDrawGizmosSelected() //create spawn area
    {
        Gizmos.color = new Color(2, 0, 2, 0.5f);
        Gizmos.DrawCube(transform.localPosition, size); // Create spawn area around object
    }
}
