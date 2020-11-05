using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class POIName : MonoBehaviour
{
    
    public string PoiName;
    public int PoiID;

    //private AssetBundle myLoadedAssetBundle;

    void Start()
    {
       //myLoadedAssetBundle = AssetBundle.LoadFromFile("Assets/Scenes/GameScene");
    }
    public void OnMouseDown()
    {
        var something = DatabaseSet<PointsOfInterest>.GetById(PoiID); // get the entity with ID
        DatabaseSet<PointsOfInterest>.UpdateFromSource(something); // update this entity from db
        something.TotalBattleNum++; // make any changes
        DatabaseSet<PointsOfInterest>.Update(something); // write changes back to db

        GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().LocId = PoiID; // save id to communicatior
        
        SceneManager.LoadScene("GameScene", LoadSceneMode.Single); //load scene
    }

}
