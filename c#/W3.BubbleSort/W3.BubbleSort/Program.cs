using System;

namespace W3.BubbleSort
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var t = new[] { 5, 1, 4, 2, 8 };
            BubbleSort<int>.Sort(t);

            Console.ReadLine();
        }
    }
}
