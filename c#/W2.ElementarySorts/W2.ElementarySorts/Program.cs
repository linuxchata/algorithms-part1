using System;

namespace W2.ElementarySorts
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var array = new[] { 7.0, 10.0, 5.0, 3.0, 8.0, 4.0, 2.0, 9.0, 6.0, 20.0, 99.0, 33.0, 10.0, 0.0 };

            var selectedSortArray = new double[array.Length];
            array.CopyTo(selectedSortArray, 0);
            SelectionSort<double>.Sort(selectedSortArray);

            for (int i = 0; i < selectedSortArray.Length; i++)
            {
                Console.Write(selectedSortArray[i]);
                Console.Write(" ");
            }

            Console.WriteLine();

            var insertionSortArray = new double[array.Length];
            array.CopyTo(insertionSortArray, 0);
            InsertionSort<double>.Sort(insertionSortArray);

            for (int i = 0; i < insertionSortArray.Length; i++)
            {
                Console.Write(insertionSortArray[i]);
                Console.Write(" ");
            }

            Console.WriteLine();

            var array2 = new[] { 'S', 'H', 'E', 'L', 'L', 'S', 'O', 'R', 'T', 'E', 'X', 'A', 'M', 'P', 'L', 'E' };
            var shellSortArray = new char[array2.Length];
            array2.CopyTo(shellSortArray, 0);
            ShellSort<char>.Sort(shellSortArray);

            for (int i = 0; i < shellSortArray.Length; i++)
            {
                Console.Write(shellSortArray[i]);
                Console.Write(" ");
            }

            Console.WriteLine();

            var shellSortArray2 = new char[shellSortArray.Length];
            shellSortArray.CopyTo(shellSortArray2, 0);
            ShellSort2<char>.Sort(shellSortArray2);

            for (int i = 0; i < shellSortArray2.Length; i++)
            {
                Console.Write(shellSortArray2[i]);
                Console.Write(" ");
            }

            Console.WriteLine();

            var shufflingArray = new double[array.Length];
            selectedSortArray.CopyTo(shufflingArray, 0);
            Shuffling<double>.Shuffle(shufflingArray);

            for (int i = 0; i < shufflingArray.Length; i++)
            {
                Console.Write(shufflingArray[i]);
                Console.Write(" ");
            }

            Console.WriteLine();

            Console.ReadKey();
        }
    }
}
