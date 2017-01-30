using System;
using System.Collections.Generic;

namespace W3.QuickSort
{
    public static class QuickSort<T>
    {
        public static void Sort(T[] array, Comparer<T> comparer = null)
        {
            var c = comparer ?? Comparer<T>.Default;

            Shuffle(array);

            //var test = "KRATELEPUIMQCXOS".ToCharArray();
            Sort(array, 0, array.Length - 1, c);
        }

        private static void Sort(T[] array, int lo, int hi, Comparer<T> comparer)
        {
            if (hi <= lo)
            {
                return;
            }
            var j = Partition(array, lo, hi, comparer);
            Sort(array, lo, j - 1, comparer);
            Sort(array, j + 1, hi, comparer);
        }

        private static int Partition(T[] array, int lo, int hi, Comparer<T> comparer)
        {
            var i = lo;
            var j = hi + 1;
            while (true)
            {
                while (comparer.Compare(array[lo], array[++i]) > 0)
                {
                    if (i == hi) break;
                }

                while (comparer.Compare(array[lo], array[--j]) < 0)
                {
                    if (j == lo) break;
                }

                if (i >= j) break;
                Swap(array, i, j);
            }

            Swap(array, lo, j);

            return j;
        }

        private static void Shuffle(T[] array)
        {
            var random = new Random();

            for (var i = 0; i < array.Length; i++)
            {
                var r = random.Next(0, array.Length);
                Swap(array, i, r);
            }
        }

        private static void Swap(T[] array, int i, int j)
        {
            var t = array[i];
            array[i] = array[j];
            array[j] = t;
        }
    }
}
