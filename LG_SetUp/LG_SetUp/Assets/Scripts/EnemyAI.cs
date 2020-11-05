using UnityEngine;

public class EnemyAI : MonoBehaviour
{
    public bool hitting = false;
    //The target player
    //public Transform player;
    private Transform player;

    void Start()
    {
        try
        {
            player = GameObject.FindWithTag("Player").transform;
        }
        catch
        {
        }
    }
    //At what distance will the enemy walk towards the player?
    public float walkingDistance = 10.0f;
    //In what time will the enemy complete the journey between its position and the players position
    public float smoothTime = 10.0f;
    //Vector3 used to store the velocity of the enemy
    private Vector3 smoothVelocity = Vector3.zero;
    //Call every frame

    void Update()
    {
        try
        {
            //Look at the player
            transform.LookAt(player);
            //Calculate distance between player
            float distance = Vector3.Distance(transform.position, player.position);
            //If the distance is smaller than the walkingDistance
            if (!hitting)
            {
                if (distance < walkingDistance)
                {
                    //Move the enemy towards the player with smoothdamp
                    transform.position = Vector3.SmoothDamp(transform.position, player.position, ref smoothVelocity, smoothTime);
                }
            }
            else
            {
                //transform.position = -Vector3.SmoothDamp(transform.position, player.position, ref smoothVelocity, smoothTime);
            }
        }
        catch
        {
        }
    }
}
