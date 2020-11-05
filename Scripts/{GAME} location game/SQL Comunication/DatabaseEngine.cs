using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using UnityEngine;
using MySql.Data;
using MySql.Data.MySqlClient;


/** 
SQL type = C# type
nvarchar = string
int = int
smallint = short
decimal = decimal
bit = bool
*/

public static class DatabaseConnection {

    public static MySqlConnection Connection { get; }

    //used to set up the connection string used to connect to the database
    static DatabaseConnection() {
        SqlConnectionStringBuilder builder = new SqlConnectionStringBuilder();
        builder.DataSource = "********.ddns.net";   // server or ip address 
        builder.UserID = "*****";              // login
        builder.Password = "******";      // password
        builder.InitialCatalog = "*******";  //database 

        Connection = new MySqlConnection(builder.ConnectionString);
        try {
            Debug.Log("testing connection 1");
            Connection.Open(); //open connection to server
            Debug.Log("open");
        } catch (Exception ex) {
            Debug.Log(ex.ToString());
        }
    }
}
//from this point the code creats an sql command for the required command

public class DatabaseSet<TEntity> where TEntity : DatabaseEntity, new() {

    public static List<TEntity> AllEntities { get; } = GetAll();

    //sets up commands for getting all entities in the database
    //this sets up the MySQL command and sends it to the conencted server
    private static List<TEntity> GetAll() {
        var props = typeof(TEntity).GetProperties();

        var sql = "SELECT " + string.Join(", ", props.Select(GetColumnName)) + " FROM " + typeof(TEntity).Name;
        var entities = new List<TEntity>();
        using (var command = new MySqlCommand(sql, DatabaseConnection.Connection)) {
            Debug.Log(sql);
                
            using (var reader = command.ExecuteReader()) {
                while (reader.Read()) {
                    var entity = new TEntity();
                    var i = 0;
                    foreach (var prop in props)
                        prop.SetValue(entity, GetValue(reader, prop.PropertyType, i++));
                    entities.Add(entity);
                }
            }
        }

        return entities;
    }
    //sets up the command to update 
    //this sets up the MySQL command and sends it to the conencted server
    public static void UpdateFromSource(TEntity entity) {
        var props = typeof(TEntity).GetProperties();

        var sql = "SELECT " + string.Join(", ", props.Select(GetColumnName)) + " FROM " + typeof(TEntity).Name + " WHERE Id = @id";
        var entities = new List<TEntity>();
        using (var command = new MySqlCommand(sql, DatabaseConnection.Connection)) {
            command.Parameters.AddWithValue("@id", entity.Id);
            using (var reader = command.ExecuteReader()) {
                while (reader.Read()) {
                    var i = 0;
                    foreach (var prop in props)
                        prop.SetValue(entity, GetValue(reader, prop.PropertyType, i++));
                }
            }
        }
    }
    //gets the Id of item in teh database by the id
    public static TEntity GetById(int id) => AllEntities.FirstOrDefault(x => x.Id == id);

    //inserts new element to the database
    //this sets up the MySQL command and sends it to the conencted server
    public static bool Insert(TEntity entity) {
        var props = typeof(TEntity).GetProperties().Where(x => x.Name != "Id");
        var sql = "INSERT INTO " + typeof(TEntity).Name + "(" + string.Join(", ", props.Select(GetColumnName)) + ") output INSERTED.ID VALUES (" + string.Join(", ", props.Select((_, i) => "@value" + i)) + ")";
        using (var command = new MySqlCommand(sql, DatabaseConnection.Connection)) {
            foreach (var prop in props.Select((p, i) => new { p, i }))
                command.Parameters.AddWithValue("@value" + prop.i, prop.p.GetValue(entity));
            entity.Id = (int)command.ExecuteScalar();
            AllEntities.Add(entity);
            return true;
        }
    }
    //updates element in the database
    //this sets up the MySQL command and sends it to the conencted server
    public static bool Update(TEntity entity) {
        var props = typeof(TEntity).GetProperties().Where(x => x.Name != "Id");
        var sql = "UPDATE " + typeof(TEntity).Name + " SET " + string.Join(", ", props.Select((prop, i) => "`" + prop.Name + "` = @value" + i)) + " WHERE Id = @id";
        Debug.Log(sql);
        using (var command = new MySqlCommand(sql, DatabaseConnection.Connection)) {
            foreach (var prop in props.Select((p, i) => new { p, i }))
                command.Parameters.AddWithValue("@value" + prop.i, prop.p.GetValue(entity));
            command.Parameters.AddWithValue("@id", entity.Id);
            return command.ExecuteNonQuery() > 0;
        }
    }
    //deletes element from table
    //this sets up the MySQL command and sends it to the conencted server
    public static bool Delete(TEntity entity) {
        var sql = "DELETE FROM " + typeof(TEntity).Name + " WHERE Id = @id";
        using (var command = new MySqlCommand(sql, DatabaseConnection.Connection)) {
            command.Parameters.AddWithValue("@id", entity.Id);
            AllEntities.Remove(entity);
            return command.ExecuteNonQuery() > 0;
        }
    }
    //gets the values for the required type
    private static object GetValue(MySqlDataReader reader, Type type, int columnIndex) {
        if (type == typeof(int))                              //if the variable is this type then
            return reader.GetInt32(columnIndex);              //retrun converting value
        else if (type == typeof(string))                      //if the variable is this type then
            return reader.GetString(columnIndex);             //retrun converting value
        else if (type == typeof(decimal))                     //if the variable is this type then
            return reader.GetDecimal(columnIndex);            //retrun converting value
        else if (type == typeof(short))                       //if the variable is this type then
            return reader.GetInt16(columnIndex);              //retrun converting value
        else if (type == typeof(bool))                        //if the variable is this type then
            return reader.GetBoolean(columnIndex);            //retrun converting value
        return null;
    }
    //get the column name
    private static string GetColumnName(System.Reflection.PropertyInfo pi) {
        return "`" + pi.Name + "`";
    }
}

public class DatabaseEntity {
    public int Id { get; set; }
}