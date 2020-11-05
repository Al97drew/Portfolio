using System.Collections;
using System.Collections.Generic;
using UnityEngine;


public class NewBehaviourScript : MonoBehaviour {
    [SerializeField]
    private string ID;

    [SerializeField]
    private double LAT;

    [SerializeField]
    private double LON;

    [SerializeField]
    private int conthr;

    [SerializeField]
    private bool condition;




    // Start is called before the first frame update
    void Start() {
        Debug.Log("Start");
        Main();
    }

    // Update is called once per frame
    void Update() {

    }

    public static void Main() {
        /*
        Debug.Log("gogogo");

        
       // Read all teh things
       var x = DatabaseSet<PointsOfInterest>.AllEntities;
       foreach (var p in x) {
           Debug.Log("Record " + p.Name + ", " + p.Latitude);
       }



       // update a ting
       var something = DatabaseSet<PointsOfInterest>.GetById(1); // get the entity with ID 0
       DatabaseSet<PointsOfInterest>.UpdateFromSource(something); // update this entity from db
       something.TotalBattleNum++; // make any changes
       DatabaseSet<PointsOfInterest>.Update(something); // write changes back to db

       //insert a ting
       var newPOI = new POI();
       newPOI.Name = "new name";
       newPOI.Latitude = 25.54646M;
       DatabaseSet<POI>.Insert(newPOI);
       */
        // delete a ting
        //DatabaseSet<POI>.Delete(DatabaseSet<POI>.GetById(1));
    }
}
