using System;
using System.Collections.Generic;

namespace W3.QuickSort
{
    public static class Selection<T>
    {
        public static T Select(T[] array, int k, Comparer<T> comparer = null)
        {
            var c = comparer ?? Comparer<T>.Default;

            Shuffle(array);

            return SelectInternal(array, k, c);
        }

        private static T SelectInternal(T[] array, int k, Comparer<T> comparer)
        {
            var lo = 0;
            var hi = array.Length - 1;
            while (lo < hi)
            {
                var j = Partition(array, lo, hi, comparer);
                if (k < j)
                {
                    hi = j - 1;
                }
                else if (k > j)
                {
                    lo = j + 1;
                }
                else
                {
                    return array[k];
                }
            }
            return array[k];
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
