using System;

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

            var hasTable2 = new LinearProbing<string, int>(10);
            hasTable2.Add("S", 1);
            hasTable2.Add("K", 0);
            hasTable2.Add("R", 1);
            hasTable2.Add("S", 5);
            hasTable2.Add("A", 7);
            hasTable2.Add("C", 9);
            hasTable2.Add("Q", 3);

            hasTable2.Get("A");

            var vector = new SparseVector[5];

            vector[0] = new SparseVector();
            vector[0].Put(1, 0.9);

            vector[1] = new SparseVector();
            vector[1].Put(2, 0.36);
            vector[1].Put(3, 0.36);
            vector[1].Put(4, 0.18);

            vector[2] = new SparseVector();
            vector[2].Put(3, 0.9);

            vector[3] = new SparseVector();
            vector[3].Put(0, 0.9);

            vector[4] = new SparseVector();
            vector[4].Put(0, 0.47);
            vector[4].Put(2, 0.47);

            var a = new[] { 0.05, 0.04, 0.36, 0.37, 0.19 };
            var b = new double[a.Length];

            for (var i = 0; i < a.Length; i++)
            {
                b[i] = vector[i].Dot(a);
            }

            Console.ReadLine();
        }
    }
}
