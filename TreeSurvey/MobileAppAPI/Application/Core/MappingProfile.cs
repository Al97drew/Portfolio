using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using TreeSurvay;

namespace Application.Core
{
    public class MappingProfile: Profile
    {
        public MappingProfile(){
            CreateMap<Survey, Survey>();
        }
        
    }
}