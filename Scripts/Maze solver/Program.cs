using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ReadMazeFile
{
    class Program
    {
        static char[,] maze_board;
        static bool xEmpty;
        static bool yEmpty;
        static bool lEmpty = false;
        static bool rEmpty = false;
        static bool uEmpty = false;
        static bool dEmpty = false;
        static bool lWall = false;
        static bool rWall = false;
        static bool uWall = false;
        static bool dWall = false;
        static bool ExitFound = false;
        static Queue<int> q = new Queue<int>();

        static void Main(string[] args)
        {
            int startX = -1, startY = -1;

            //path to the file. This file you could read from the command line
            //Console.WriteLine("Chose a file");
            //string Fi = Console.ReadLine();
            string path = @"maze.txt";
            string[] lines = System.IO.File.ReadAllLines(path);

            //First line holds row and column information
            string[] line = lines[0].Split(' ');
            int row = Convert.ToInt32(line[0]);
            int col = Convert.ToInt32(line[1]);
            //holding 0s 1s and starting point 's' and exit point 'e'
            maze_board = new char[col, row];

            for (int i = 1; i < lines.Length; i++)
            {
                //String to char array
                char[] arr = lines[i].ToCharArray();

                for (int j = 0; j < arr.Length; j++)
                {
                    maze_board[j, i - 1] = arr[j];

                    if (arr[j] == 's')
                    {
                        // start
                        startX = j;
                        startY = i - 1;
                        Console.WriteLine("Start found at " + j + ", " + (i - 1));
                    }
                    PrintArray();
                }
                SerchStart(startX, startY);

            }
            
        }

        static void PrintArray()
        {
            for (int y = 0; y < maze_board.GetLength(1); y++)
            {
                for (int x = 0; x < maze_board.GetLength(0); x++)
                {
                    Console.Write(maze_board[x, y] + " ");
                }
                Console.WriteLine();
            }
        }

        static void queue()
        {
            int qx = -1;
            int qy = -1;

            if (q.Count() != 0)
            {
                if (qx == -1)
                {
                    qx = q.Dequeue();
                }
                if (qx != -1 && qy == -1)
                {
                    qy = q.Dequeue();
                }
                ExitFind(qx, qy);
                SerchStart(qx, qy);
            }
            return;
        }

        static void SerchStart(int xP, int yP)
        {
            lEmpty = false;
            rEmpty = false;
            uEmpty = false;
            dEmpty = false;
            lWall = false;
            rWall = false;
            uWall = false;
            dWall = false;

            emptyRound(xP, yP);
            ExitFind(xP, yP);
            WallRound(xP, yP);
            PathRound(xP, yP); 
            end();
        }

        static void ExitFind(int xPos, int yPos)
        {
            if (lEmpty == false && lWall == false && maze_board[xPos - 1, yPos] == 'e')
            {
                Console.WriteLine("Exit Left");
                ExitFound = true;
                end();
            }
            if (rEmpty == false && rWall == false && maze_board[xPos + 1, yPos] == 'e')
            {
                Console.WriteLine("Exit Right");
                ExitFound = true;
                end();
            }
            if (uEmpty == false && uWall == false && maze_board[xPos, yPos - 1] == 'e')
            {
                Console.WriteLine("Exit Up");
                ExitFound = true;
                end();
            }
            if (dEmpty == false && dWall == false && maze_board[xPos, yPos + 1] == 'e')
            {
                Console.WriteLine("Exit Down");
                ExitFound = true;
                end();
            }
        }


        static void PathRound(int xPos, int yPos)
        {
            char path = '0';
            char path2 = '.';

            if ((lEmpty == false && lWall == false && maze_board[xPos - 1, yPos] == path) || (lEmpty == false && lWall == false &&  maze_board[xPos - 1, yPos] == path2))
            {
                Console.WriteLine("Path Left");
                maze_board[xPos - 1, yPos] = 'v';
                q.Enqueue((xPos - 1));
                q.Enqueue(yPos);
                PrintArray();
            }
            if ((rEmpty == false && rWall == false && maze_board[xPos + 1, yPos] == path) || (rEmpty == false && rWall == false &&  maze_board[xPos + 1, yPos] == path2))
            {
                Console.WriteLine("Path Right");
                maze_board[xPos + 1, yPos] = 'v';
                q.Enqueue((xPos + 1));
                q.Enqueue(yPos);
                PrintArray();
            }
            if ((uEmpty == false && uWall == false && maze_board[xPos, yPos - 1] == path) || (uEmpty == false && uWall == false && maze_board[xPos, yPos - 1] == path2))
            {
                Console.WriteLine("Path Up");
                maze_board[xPos, yPos - 1] = 'v';
                q.Enqueue(xPos);
                q.Enqueue(yPos - 1);
                PrintArray();
            }
            if ((dEmpty == false && dWall == false && maze_board[xPos, yPos + 1] == path) || (dEmpty == false && dWall == false && maze_board[xPos, yPos + 1] == path2))
            {
                Console.WriteLine("Path Down");
                maze_board[xPos, yPos + 1] = 'v';
                q.Enqueue(xPos);
                q.Enqueue(yPos + 1);
                PrintArray();
            }  
            queue();
        }

        static void WallRound(int xPos, int yPos)
        {
            char path = '0';
            char path2 = '.';

            if (xPos < 0 || yPos < 0 || xPos > maze_board.GetLength(1) || yPos > maze_board.GetLength(0))
            {
                return;
            }

            if (lEmpty == false)
            {
                if (maze_board[xPos - 1, yPos] != path && maze_board[xPos - 1, yPos] != path2)
                {
                    Console.WriteLine("Wall left");
                    lWall = true;
                }
            }
            if (rEmpty == false)
            {
                if (maze_board[xPos + 1, yPos] != path && maze_board[xPos + 1, yPos] != path2)
                {
                    Console.WriteLine("Wall right");
                    rWall = true;
                }
            }
            if (uEmpty == false)
            {
                if (maze_board[xPos, yPos - 1] != path && maze_board[xPos, yPos - 1] != path2)
                {
                    Console.WriteLine("Wall up");
                    uWall = true;
                }
            }
            if (dEmpty == false)
            {
                if (maze_board[xPos, yPos + 1] != path && maze_board[xPos, yPos + 1] != path2)
                {
                    Console.WriteLine("Wall down");
                    dWall = true;
                }
            }
            return;
        }

        static void emptyRound(int xPos, int yPos)
        {
            nullCheck(xPos - 1, yPos);
            if(xEmpty == true)
            {
                xEmpty = false;
                lEmpty = true;
                Console.WriteLine("Empty Left");
            }

            nullCheck(xPos + 1, yPos);
            if (xEmpty == true)
            {
                xEmpty = false;
                rEmpty = true;
                Console.WriteLine("Empty Right");
            }

            nullCheck(xPos, yPos - 1);
            if (yEmpty == true)
            {
                yEmpty = false;
                uEmpty = true;
                Console.WriteLine("Empty Up");
            }

            nullCheck(xPos, yPos + 1);
            if (yEmpty == true)
            {
                yEmpty = false;
                dEmpty = true;
                Console.WriteLine("Empty Down");
            }
            return;
        }

        static void nullCheck(int x, int y)
        {

            try{
                if (maze_board[x, y] == 's' || maze_board[x, y] == 'e' || maze_board[x, y] == '1' || maze_board[x, y] == '0' || maze_board[x,y] == '.' || maze_board[x,y] == '#')
                {
                    yEmpty = false;
                    xEmpty = false;
                    return;

                }
            }
            catch(IndexOutOfRangeException)
            {            
                yEmpty = true;
                xEmpty = true;
                return;
            }
        }

        static void end()
        {
            if (ExitFound == false)
            {
                PrintArray();
                Console.WriteLine("There is no exit within this maze");
                Console.ReadKey();
            }
            else
            {

                PrintArray();
                Console.WriteLine("Exit found");
                Console.ReadKey();
            }
        }   
    }
}