using System;

namespace W3.QuickSort
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var sort = "QUICKSORTEXAMPLE";
            var array = sort.ToCharArray();

            QuickSort<char>.Sort(array);

            Console.ReadKey();
        }
    }
}
