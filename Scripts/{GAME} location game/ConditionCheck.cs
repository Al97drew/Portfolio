using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class ConditionCheck : MonoBehaviour
{
    public bool battleDone;
    public bool BattleOutCome;
    // Start is called before the first frame update
    void Start()
    {
        battleDone = false;
        BattleOutCome = false;
    }

    // Update is called once per frame
    void Update()
    {
        if(battleDone && GameObject.FindGameObjectsWithTag("Piller").Length == 0) // if true and Piller doesnt exist
        {

            BattleOutCome = true;
            Debug.Log("battleOutCome True");

            GameObject.FindGameObjectWithTag("Controle").GetComponent<DontDestroy>().BattleDone = true; // save status int the communicatior object
            GameObject.FindGameObjectWithTag("Controle").GetComponent<DontDestroy>().Win = true; // save status int the communicatior object
            GameObject.FindGameObjectWithTag("UITextBox").GetComponent<Text>().text = "YOU WIN!!!!! Returning..."; // change text on screen to...
            SceneManager.LoadScene("LocGame", LoadSceneMode.Single); // change scene back to locGame
        }
    }
}
