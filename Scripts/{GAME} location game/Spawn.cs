using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Spawn : MonoBehaviour
{
    public GameObject RockPrefab;
    public GameObject EnemyPrefab;
    public GameObject EnemyPillerPrefab;

    public Vector3 center;
    public Vector3 size;
    public float timer = 30;

    // Start is called before the first frame update
    void Start()
    {
        for(int i=0; i<=50; i++) // do 50 times
        {
            SpawnRocks();
            SpawnEnemys();
        }
        SpawnPiller();
    }

    // Update is called once per frame
    void Update()
    {
        timer -= Time.deltaTime;
        if(timer < 0 ) // once time has run out..
        {
            for(int i=0; i<=50; i++) // do 50 times
            {
                SpawnEnemys();
            }
            timer = 30;
        }
    }

    public void SpawnRocks()
    {
        //create random position for object within spawn area
        Vector3 pos = center + new Vector3(Random.Range(-size.x / 2, size.x / 2), 1, Random.Range(-size.z / 2, size.z / 2));

        Instantiate(RockPrefab, pos, Quaternion.identity); // spawn object
    }

    public void SpawnEnemys()
    {
        //create random position for object within spawn area
        Vector3 pos = center + new Vector3(Random.Range(-size.x / 2, size.x / 2), (float)0.5, Random.Range(-size.z / 2, size.z / 2));

        Instantiate(EnemyPrefab, pos, Quaternion.identity); // spawn object
    }

    public void SpawnPiller()
    {
        //create random position for object within spawn area
        Vector3 pos = center + new Vector3(Random.Range(-size.x / 2, size.x / 2), 1, Random.Range(-size.z / 2, size.z / 2));
        Quaternion basePillerRotation = EnemyPillerPrefab.transform.rotation; // use prefab rotaion
        Instantiate(EnemyPillerPrefab, pos, basePillerRotation); // spawn object
    }

    void OnDrawGizmosSelected() // create spawn area
    {
        Gizmos.color = new Color(1, 0, 0, 0.5f);
        Gizmos.DrawCube(center, size); // create cube at center
    }
}
