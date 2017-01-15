using System;
using System.Linq;

namespace W4.BinaryHeap
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var bh = new BinaryHeap<char>(15);
            bh.Add('T');

            bh.Add('P');
            bh.Add('R');

            bh.Add('N');
            bh.Add('H');
            bh.Add('O');
            bh.Add('A');

            bh.Add('E');
            bh.Add('I');
            bh.Add('G');

            bh.Add('S');

            bh.DeleteMax();
            bh.DeleteMax();

            bh.Add('S');

            foreach (var array in bh)
            {
                if (array != default(char))
                {
                    Console.WriteLine(array);
                }
            }

            Console.WriteLine("Sorting example with binary heap - Start");

            var bh2 = new BinaryHeap<char>(15);
            bh2.Add('S');
            bh2.Add('O');
            bh2.Add('R');
            bh2.Add('T');
            bh2.Add('E');
            bh2.Add('X');
            bh2.Add('A');
            bh2.Add('M');
            bh2.Add('P');
            bh2.Add('L');
            bh2.Add('E');

            foreach (var array in bh2)
            {
                if (array != default(char))
                {
                    Console.WriteLine(array);
                }
            }

            Console.WriteLine("Sorting example with binary heap - Result");

            while (bh2.Count() > 0)
            {
                Console.WriteLine(bh2.DeleteMax());
            }

            Console.WriteLine("Heap sort - Start");

            var @string = "SORTEXAMPLE";
            var arrayToSort = @string.ToCharArray();
            var array2 = new char[arrayToSort.Length + 1];

            for (var i = 1; i < array2.Length; i++)
            {
                array2[i] = arrayToSort[i - 1];
            }

            HeapSort<char>.Sort(array2);

            foreach (var item in array2)
            {
                if (item != default(char))
                {
                    Console.WriteLine(item);
                }
            }

            Console.WriteLine("Heap sort - Result");

            Console.ReadKey();
        }
    }
}
