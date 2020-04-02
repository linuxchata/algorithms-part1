using System;
using System.IO;
using System.Linq;

namespace W1.UnionFind
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var input = File.ReadLines("input_quickunion.dat").ToList();
            var size = int.Parse(input[0]);

            var uf = new UnionFindQuickUnion(size);

            for (var i = 1; i < input.Count; i++)
            {
                var pq = input[i].Split(' ');
                var p = int.Parse(pq[0]);
                var q = int.Parse(pq[1]);

                if (!uf.IsConnected(p, q))
                {
                    uf.Union(p, q);
                }
            }

            Console.ReadKey();
        }
    }
}
