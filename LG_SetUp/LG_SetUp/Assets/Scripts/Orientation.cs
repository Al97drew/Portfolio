using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Orientation : MonoBehaviour
{
    // Start is called before the first frame update
    void Start()
    {
        Scene scene = SceneManager.GetActiveScene(); // gets currnet scene
        if (scene.name == "LocGame") // if LocGame
        {
            Screen.autorotateToLandscapeRight = false; // change to landcape view

            Screen.autorotateToPortrait = true; // change to landcape view
        }
        else if (scene.name == "GameScene") // if GameScene
        {
            Screen.autorotateToPortrait = false; // change to portrate view
            Screen.autorotateToLandscapeRight = true; // change to portrate view
        }
    }

}
