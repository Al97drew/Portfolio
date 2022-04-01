using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using MySql.Data.MySqlClient;
using TreeSurvay;

namespace API
{
    public class ConnectionContext
    {

        public MySqlConnection Connection { get; set; }
        Boolean x = true;

        public ConnectionContext(MySqlConnection Connection)
        {
            this.Connection = GetConnection();
        }

        private MySqlConnection GetConnection()
        {
            //Console.WriteLine("Default Connection");
            MySqlConnection conn = new MySqlConnection("Server=localhost;Database=treeplanner;User Id=Admin;Password=kMurA:W3St-EGb3-admin");
            if(x){
                x = false;
                conn.Open();
            }
            return conn;
        }


    }
}