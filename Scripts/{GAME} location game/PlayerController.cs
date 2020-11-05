using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerController : MonoBehaviour
{
    [SerializeField]
    private float movementSpeed;

    public FixedJoystick FixedJoystick; //get the joysticks
    public FixedJoystick FixedJoystick2;//get the joysticks
    public Rigidbody rb;
    private float yRot;

    void Start()
    {
        
    }

    /*
    void Update()
    {
        HandleMovementInput();
        HandleRotationInput();
        handleShootInput();
    }
    */
    public void FixedUpdate()
    {
        joystickMove(); 
        joystickRotate();
    }


    public void joystickMove()
    {
        Vector3 direction = Vector3.forward * FixedJoystick.Vertical + Vector3.right * FixedJoystick.Horizontal;  //find the correct direction to move
        rb.AddForce(direction * movementSpeed * Time.fixedDeltaTime, ForceMode.VelocityChange); // uses above line to change player possition based on joystick
    }

    public void joystickRotate()
    {
        yRot = Mathf.Atan2(FixedJoystick2.Vertical, FixedJoystick2.Horizontal) * Mathf.Rad2Deg; //find the correct direction to face
        transform.rotation = Quaternion.Euler(new Vector3(0, -yRot + 90, 0)); // uses above line to change player rortaion based on joystick + 90
        PlayerGun.Instance.Shoot();
        //transform.Rotate(FixedJoystick2.Direction);
    }

    /*
    void HandleMovementInput()
    {
        
        float _horizontal = Input.GetAxis("Horizontal");
        float _vertical = Input.GetAxis("Vertical");

        Vector3 _movement = new Vector3(_horizontal, 0, _vertical);
        transform.Translate(_movement * movementSpeed * Time.deltaTime, Space.World);
    }

    void HandleRotationInput()
    {
        RaycastHit _hit;
        Ray _ray = Camera.main.ScreenPointToRay(Input.mousePosition);

        if (Physics.Raycast(_ray, out _hit))
        {
            transform.LookAt(new Vector3(_hit.point.x, transform.position.y, _hit.point.z));
        }
    }
    
    void handleShootInput()
    {
        if (Input.GetButton("Fire1"))
        {
            PlayerGun.Instance.Shoot();
        }
    }
    */
}
