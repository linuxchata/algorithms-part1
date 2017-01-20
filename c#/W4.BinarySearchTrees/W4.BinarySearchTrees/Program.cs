using System;

namespace W4.BinarySearchTrees
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var bst = new BinarySearchTree<string, int>();
            bst.Put("S", 47);
            bst.Put("X", 14);
            bst.Put("E", 32);
            bst.Put("A", 16);
            bst.Put("R", 61);
            bst.Put("C", 89);
            bst.Put("H", 17);
            bst.Put("M", 67);

            Console.WriteLine(bst.Get("H"));
            Console.WriteLine(bst.Get("X"));
            Console.WriteLine(bst.Get("Z"));

            bst.Floor("G");

            Console.WriteLine(bst.Rank("R"));

            var keys = bst.Keys();

            foreach (var key in keys)
            {
                Console.WriteLine(key);
            }

            bst.DeleteMin();

            Console.ReadKey();
        }
    }
}
