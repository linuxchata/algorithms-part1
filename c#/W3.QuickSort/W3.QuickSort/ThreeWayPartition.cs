using System.Collections.Generic;

namespace W3.QuickSort
{
    public static class ThreeWayPartition<T>
    {
        public static void Sort(T[] array, Comparer<T> comparer = null)
        {
            var c = comparer ?? Comparer<T>.Default;

            var lo = 0;
            var hi = array.Length - 1;
            Partition(array, lo, hi, c);
        }

        private static void Partition(T[] array, int lo, int hi, Comparer<T> comparer)
        {
            if (lo > hi)
            {
                return;
            }

            var v = array[lo];
            var i = lo;
            var lt = lo;
            var gt = hi;

            while (i <= gt)
            {
                var c = comparer.Compare(array[i], v);
                if (c < 0)
                {
                    Exchange(array, lt++, i++);
                }
                else if (c > 0)
                {
                    Exchange(array, gt--, i);
                }
                else
                {
                    i++;
                }
            }

            Partition(array, lo, lt - 1, comparer);
            Partition(array, gt + 1, hi, comparer);
        }

        private static void Exchange(T[] array, int i, int j)
        {
            var temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }
}
