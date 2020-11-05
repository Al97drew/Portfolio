using System.Collections;
using UnityEngine;
using UnityEngine.UI;

public class HealthBar : MonoBehaviour
{

    [SerializeField]
    private Image foregroundImage; // sets forground image
    [SerializeField]
    private float updateSpeedSeconds = 0.5f; 
    [SerializeField]
    private float positionOffset;

    private void Awake()
    {
        try
        {
            GetComponentInParent<Health>().OnHealthPctChanged += HandleHealthChanged; // get changed heatlh from health script
        }
        catch
        {
        }
    }


    private void HandleHealthChanged(float pct) // handle the change in health
    {
        try
        {
            StartCoroutine(ChangeToPct(pct)); // runs ChangeoPct but alows for pause execution and automaticlly resume at next frame
        }
        catch
        {
        }
    }

    private IEnumerator ChangeToPct(float pct)
    {
        
        float preChangedPct = foregroundImage.fillAmount; // how much to change
        float elapsed = 0f;

        while(elapsed < updateSpeedSeconds) // how oftern updates
        {
            elapsed += Time.deltaTime;
            foregroundImage.fillAmount = Mathf.Lerp(preChangedPct, pct, elapsed / updateSpeedSeconds); // smooth health decrease
            yield return null; // pause coroutine
        }

        foregroundImage.fillAmount = pct; // change fill amount
    }

    private void LateUpdate()
    {
        try
        {
            //look at camera
            transform.LookAt(Camera.main.transform);
            transform.Rotate(0, 180, 0);
        }
        catch
        {

        }
    }
    //ModifyHealth(-?);
}
