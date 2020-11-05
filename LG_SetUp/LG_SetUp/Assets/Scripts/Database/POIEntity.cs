public class PointsOfInterest : DatabaseEntity
{
    public string Name { get; set; } // data in the databse
    public decimal Latitude { get; set; } // data in the databse
    public decimal Longitude { get; set; } // data in the databse
    public int TotalBattleNum { get; set; } // data in the databse
    public int TotalBattleWin { get; set; } // data in the databse
    public int TotalBattleLoss { get; set; } // data in the databse
    public short ControlThreshold { get; set; } // data in the databse
    public bool POICondition { get; set; } // data in the databse
}