using UnityEngine;

public class DontDestroy : MonoBehaviour
{
    public bool Win = false;
    public bool BattleDone = false;
    public int LocId;

    public void Awake()
    {
        GameObject[] Target = GameObject.FindGameObjectsWithTag("Controler");//find all objects in scene that have controler tag
        
        if (Target.Length > 1) // if more than 1
        {
            LocId = Target[1].GetComponent<DontDestroy>().LocId; // get id
            Destroy(this.gameObject); // destroy this
        }
        DontDestroyOnLoad(this.gameObject); // dont destroy this when loading new scene
    }
}
