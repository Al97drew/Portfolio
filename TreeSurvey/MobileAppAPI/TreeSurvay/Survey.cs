using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TreeSurvay
{
    public class Survey
    {
        public int Id { get; set; } //id
        public string TreeNameCommon { get; set; } //text
        public string TreeNameBotanical { get; set; } //dropbox | text
        public int TreeDiameter { get; set; }// number
        public DateTime PlantingDate { get; set; } //date
        public string TreeAge { get; set; } // dropbox
        public string CrownCondition { get; set; } // dropbox
        public string TrunkCondition { get; set; } // dropbox
        public string RootCondition { get; set; } // dropbox
        public string TypeOfPlantingArea { get; set; } // dropbox
        public string NearestInf { get; set; } // dropbox
        public int SizeOfTreeWhenPlanted { get; set; } // number
        public string ProspectiveTreeSizeCrown { get; set; } // dropbox
        public string ProspectiveTreeSizeHeight { get; set; } // dropbox
        public bool PitPresent { get; set; } //bool radiobutton
        public int SurfaceTreePitSize { get; set; } // number m2
        public int VolumeOfTreePit { get; set; } // number m3
        public string TypeOfTreePit { get; set; } // text?
        public bool TreeStake { get; set; } //bool radiobutton
        public string TreeStakeTypes { get; set; }//dropbox
        //TODO TreeStakeTypes
        //TODO Somthing on mulching
        //TODO Will you be able to record location accuratley
        public string SameWithin50M { get; set; } // dropbox
        public string Comments { get; set; }// comment box
    }
}