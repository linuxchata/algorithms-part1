using System;

namespace W3.MergeSort
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var array = new char[] { 'M', 'E', 'R', 'G', 'E', 'S', 'O', 'R', 'T' };

            var array2 = new char[array.Length];
            array.CopyTo(array2, 0);
            
            MergeSort<char>.Sort(array2);
            foreach (var item in array2)
            {
                Console.Write(item);
                Console.Write(' ');
            }

            Console.WriteLine();

            var array3 = new char[array.Length];
            array.CopyTo(array3, 0);

            MergeSortPrincton<char>.Sort(array3);
            foreach (var item in array3)
            {
                Console.Write(item);
                Console.Write(' ');
            }

            Console.WriteLine();

            var array4 = new char[array.Length];
            array.CopyTo(array4, 0);

            MergeSortBottomUp<char>.Sort(array4);
            foreach (var item in array4)
            {
                Console.Write(item);
                Console.Write(' ');
            }

            Console.WriteLine();

            Console.ReadKey();
        }
    }
}
