using UnityEngine;

public class EnemyDealDamage : MonoBehaviour
{

    public void OnTriggerEnter(Collider col) // on trigger
    {
        if (col.gameObject.tag == "Player") // if it is the player
        {
            col.GetComponent<Health>().ModifyHealth(-10); // -10 health
            Destroy(gameObject); // destroy bullet
        }
    }
}
