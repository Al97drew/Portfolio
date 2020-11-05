namespace Mapbox.Examples
{
	using UnityEngine;
	using Mapbox.Utils;
	using Mapbox.Unity.Map;
	using Mapbox.Unity.MeshGeneration.Factories;
	using Mapbox.Unity.Utilities;
	using System.Collections.Generic;

	public class SpawnOnMap : MonoBehaviour
	{
		[SerializeField]
		AbstractMap _map;		

		[SerializeField]
		[Geocode]
		public string[] _locationStrings;
		public Vector2d[] _locations;

        [SerializeField]
        public List<string> IdNames;

        [SerializeField]
        public List<int> LocId;

        [SerializeField]
		float _spawnScale = 100f;

		[SerializeField]
		GameObject _markerPrefab;

		List<GameObject> _spawnedObjects;

		void Start()
		{
			 
			_locations = new Vector2d[_locationStrings.Length]; // uses the poi locations
			_spawnedObjects = new List<GameObject>();
			for (int i = 0; i < _locationStrings.Length; i++) // loops for numebr of POI
			{
				var locationString = _locationStrings[i]; // nth number  = this 
				_locations[i] = Conversions.StringToLatLon(locationString); // converts the string to lat long
				var instance = Instantiate(_markerPrefab); // create the object
                instance.GetComponent<POIName>().PoiName = IdNames[i]; // give object its name from array
                instance.GetComponent<POIName>().PoiID = LocId[i]; // give object its id from array
                instance.transform.localPosition = _map.GeoToWorldPosition(_locations[i], true); // move position to correct possition
				instance.transform.localScale = new Vector3(_spawnScale, _spawnScale, _spawnScale); // change scale of object
				_spawnedObjects.Add(instance); // add to array
			}
		}

		private void Update()
		{
			int count = _spawnedObjects.Count;
			for (int i = 0; i < count; i++) // for each spawned objects
			{
				var spawnedObject = _spawnedObjects[i];
				var location = _locations[i];
				spawnedObject.transform.localPosition = _map.GeoToWorldPosition(location, true); // make sure it is in the correct possition
				spawnedObject.transform.localScale = new Vector3(_spawnScale, _spawnScale, _spawnScale); // make sure it is teh correct scale
			}
		}
	}
}