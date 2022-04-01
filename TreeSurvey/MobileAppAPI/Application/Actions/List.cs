using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Microsoft.EntityFrameworkCore;
using MySql.Data.MySqlClient;
using Persistence;
using TreeSurvay;

namespace Application.Actions
{
    public class List
    {
        public class Query : IRequest<List<Survey>> {
            
        }
        
        public class Handler : IRequestHandler<Query, List<Survey>>
        {
            private readonly DataContext _context;
            public Handler(DataContext context)
            {
                _context = context;
            }

            public async Task<List<Survey>> Handle(Query request, CancellationToken cancellationToken)
            {
                return await _context.TreeSurveys.ToListAsync();
            }
        }
    }
}