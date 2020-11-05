using System.Collections;
using System.Collections.Generic;
using Mapbox.Unity.Location;
using UnityEngine;

public class LoadNearPlayer : MonoBehaviour
{
    // Start is called before the first frame update

    DeviceLocationProviderAndroidNative _DeviceLocationProviderAndroidNative;

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

    void Start()
    {
        InvokeRepeating("CurrentPOI", 0,  5.0f);
    } 
    public void CurrentPOI()
    {

    }

    public void GetPOI()
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

                LatLong.Add(stemplat + "," + stemplong); // add lat long to the list in the correct format


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
    }

}
