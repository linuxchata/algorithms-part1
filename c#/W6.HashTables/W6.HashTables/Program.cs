﻿using System;

namespace W6.HashTables
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var hasTable = new SeparateChaining<string, int>(10);
            hasTable.Add("S", 1);
            hasTable.Add("K", 0);
            hasTable.Add("R", 1);
            hasTable.Add("S", 5);
            hasTable.Add("A", 7);
            hasTable.Add("C", 9);
            hasTable.Add("Q", 3);

            hasTable.Get("A");

            Console.ReadLine();
        }
    }
}
