using System;
using UnityEngine;
using UnityEngine.SceneManagement;
using UnityEngine.UI;

public class Health : MonoBehaviour
{
    [SerializeField]
    private int maxHealth = 100; // set max health

    public int CurrentHealth { get; private set;} // current health

    public event Action<float> OnHealthPctChanged = delegate { }; //sets OnHealthPctChanged up

    private void OnEnable()
    {
        CurrentHealth = maxHealth; // current health to max health
    }

    public void ModifyHealth(int amount) // change the current health
    {
        try
        {
            CurrentHealth += amount;

            float currnetHealthPct = (float)CurrentHealth / (float)maxHealth; // workout health Pct
            OnHealthPctChanged(currnetHealthPct);
        }
        catch
        {
        }
    }
    // Update is called once per frame
    void Update()
    {
        if (CurrentHealth <= 0)
        {
            if(this.gameObject.tag == "Piller") //check if the object has a tack of..
            {
                Debug.Log("WIN!!!!!!!!");
                GameObject.Find("SpawnControler").GetComponent<ConditionCheck>().battleDone = true; // find object and change variable in compoment
                GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().Win = true; // find object with tag and change variable in compoment
                SceneManager.LoadScene("LocGame", LoadSceneMode.Single);
            }
            if(this.gameObject.tag == "Player") //check if the object has a tack of..
            {
                Destroy(gameObject);
                GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().BattleDone = true; // find object with tag and change variable in compoment
                GameObject.FindGameObjectWithTag("Controler").GetComponent<DontDestroy>().Win = false; // find object with tag and change variable in compoment
                GameObject.FindGameObjectWithTag("UITextBox").GetComponent<Text>().text = "YOU LOSE!!!!! Returning...";
                SceneManager.LoadScene("LocGame", LoadSceneMode.Single);// cahnges scene
            }
            Destroy(gameObject);
        }
    }
}
