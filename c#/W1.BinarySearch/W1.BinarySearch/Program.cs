using System;

namespace W1.BinarySearch
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var array = new int[15];
            array[0] = 97;
            array[1] = 96;
            array[2] = 95;
            array[3] = 93;
            array[4] = 84;
            array[5] = 72;
            array[6] = 64;
            array[7] = 53;
            array[8] = 51;
            array[9] = 43;
            array[10] = 33;
            array[11] = 25;
            array[12] = 14;
            array[13] = 13;
            array[14] = 6;

            Array.Sort(array);

            int searchItem = 33;

            Console.WriteLine(BinarySearch(array, searchItem));
            Console.ReadKey();
        }

        private static int BinarySearch(int[] array, int key)
        {
            int lo = 0;
            int hi = array.Length - 1;
            while (lo <= hi)
            {
                int mid = lo + (hi - lo) / 2;
                if (array[mid] < key)
                {
                    lo = mid + 1;
                }
                else if (array[mid] > key)
                {
                    hi = mid - 1;
                }
                else
                {
                    return mid;
                }
            }

            return -1;
        }
    }
}
