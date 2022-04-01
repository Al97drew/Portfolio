using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using System.Threading.Tasks;
using MediatR;
using Microsoft.EntityFrameworkCore;
using Persistence;
using TreeSurvay;

namespace Application.Actions
{
    public class Test
    {
        public class Query : IRequest<Survey> {
            public int Id {get; set;}
        }

        public class Handler : IRequestHandler<Query, Survey>
        {
            private readonly DataContext _context;
            public Handler(DataContext context)
            {
                _context = context;
            }

            public async Task<Survey> Handle(Query request, CancellationToken cancellationToken)
            {
                return await _context.TreeSurveys.FindAsync(request.Id);
            }
        }
    }
}