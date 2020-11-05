using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class POISpawnerScript : MonoBehaviour
{
    //string[] latlong;
    List<string> LatLong = new List<string>();
    List<string> locName = new List<string>();
    List<int> locId = new List<int>();
    decimal templat;
    decimal templong;
    string stemplat;
    string stemplong;
    bool LastBattle;
    bool battleOver;
    int lastPOI;
    
    int dbnum;
    //f10
    // Start is called before the first frame update
    void Start()
    {
        try // try too....
        {
            var x = DatabaseSet<PointsOfInterest>.AllEntities; // open databse connection
            
            foreach (var p in x)
            {
                //Debug.Log("Record " + p.Latitude + ", " + p.Longitude);
                locName.Add(p.Name);
                stemplat = p.Latitude.ToString("F8");  //get value from server converted to string to 8 decimal points

                stemplong = p.Longitude.ToString("F8"); //get value from server converted to string to 8 decimal points

                locId.Add(p.Id); // get id

                LatLong.Add(stemplat +","+ stemplong); // add lat long to the list in the correct format

                
            }
            LatLong.ForEach(i => Debug.Log(i));
            string[] latlong = LatLong.ToArray(); // convert list to array

            gameObject.GetComponent<Mapbox.Examples.SpawnOnMap>().IdNames = locName; // send needed data to spawn on map for spawning POI
            gameObject.GetComponent<Mapbox.Examples.SpawnOnMap>().LocId = locId; // send needed data to spawn on map for spawning POI
            gameObject.GetComponent<Mapbox.Examples.SpawnOnMap>()._locationStrings = latlong; // send needed data to spawn on map for spawning POI

            Debug.Log("SPAWN ON MAP DONE");
        }
        catch
        {
            Debug.Log("FAILURE");
        }

        try
        {
            battleOver = GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().BattleDone; // get data from communicatior
            LastBattle = GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().Win; // get data from communicatior
            lastPOI = GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().LocId; // get data from communicatior

            if (battleOver)
            {
                if (LastBattle)
                {
                    //update text feild with message
                    GameObject.FindGameObjectWithTag("UITextBox").GetComponent<Text>().text = "Last Battle Notice: WIN!! Keep at it and we can take back the world!!";
                    var something = DatabaseSet<PointsOfInterest>.GetById(lastPOI); // get the entity with ID 0
                    DatabaseSet<PointsOfInterest>.UpdateFromSource(something); // update this entity from db
                    something.TotalBattleWin++; // make any changes
                    DatabaseSet<PointsOfInterest>.Update(something); // write changes back to db
                    battleOver = false; // reset variables for next game
                    LastBattle = false; // reset variables for next game
                }
                else
                {
                    //update text feild with message
                    GameObject.FindGameObjectWithTag("UITextBox").GetComponent<Text>().text = "Last Battle Notice: LOSS!! Dont Give up now we need you!!!";
                    var something = DatabaseSet<PointsOfInterest>.GetById(lastPOI); // get the entity with ID 0
                    DatabaseSet<PointsOfInterest>.UpdateFromSource(something); // update this entity from db
                    something.TotalBattleLoss++; // make any changes
                    DatabaseSet<PointsOfInterest>.Update(something); // write changes back to db
                    battleOver = false; // reset variables for next game
                    LastBattle = false; // reset variables for next game
                }
            }
        }
        catch
        {
            Debug.Log("ERROR");
        }
    }
}
