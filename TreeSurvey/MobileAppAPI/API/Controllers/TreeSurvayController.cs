using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Application;
using Application.Actions;
using Microsoft.AspNetCore.Mvc;
using TreeSurvay;
using MySql.Data.MySqlClient;


namespace API.Controllers
{
    public class TreeSurvayController : BaseApiController
    {
        
        public bool Connected;
        MySqlConnection connection;
        public TreeSurvayController(ConnectionContext options) {
            connection = options.Connection;
            //connection = new MySqlConnection("Server=localhost;Database=treeplanner;User Id=Admin;Password=kMurA:W3St-EGb3-admin");
            
        }

        [HttpGet]
        public async Task<ActionResult<List<Survey>>> GetSurvays( )
        {
            return await Mediator.Send(new List.Query());
        }

        [HttpGet("{id}")]
        public async Task<ActionResult<Survey>> GetSurvey(int id)
        {
            return await Mediator.Send(new Test.Query{Id = id});
        }
    }
}