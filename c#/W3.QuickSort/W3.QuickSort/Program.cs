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

            var selectArray = sort.ToCharArray();
            
            Console.Write(Selection<char>.Select(selectArray, 15));

            Console.ReadKey();
        }
    }
}
