using UnityEngine;

public class DealDamage : MonoBehaviour
{
    public void OnTriggerEnter(Collider col) // when triggered
    {
        try // try
        {
            if (col.gameObject.tag == "Enemy" || col.gameObject.tag == "Piller") // if other object is enemy or piller then
            {
                col.GetComponent<Health>().ModifyHealth(-10); // -10 health
                Destroy(gameObject); //destroy bullet
            }
        }
        catch 
        {
        }
    }
}
