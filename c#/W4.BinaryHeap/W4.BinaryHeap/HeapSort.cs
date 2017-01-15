using System.Collections.Generic;

namespace W4.BinaryHeap
{
    public class HeapSort<T>
    {
        private static Comparer<T> comparer;

        public static void Sort(T[] array, Comparer<T> c = null)
        {
            comparer = c ?? Comparer<T>.Default;

            var n = array.Length - 1;
            for (var i = n / 2; i > 0; i--)
            {
                Sink(array, i, n);
            }

            while (n > 0)
            {
                Exchange(array, 1, n);
                Sink(array, 1, --n);
            }
        }

        private static void Sink(T[] array, int k, int n)
        {
            while (2 * k <= n)
            {
                var child = 2 * k;
                if (child < n && Less(array, 2 * k, 2 * k + 1))
                {
                    child++;
                }
                if (!Less(array, k, child))
                {
                    break;
                }

                Exchange(array, child, k);
                k = child;
            }
        }

        private static bool Less(T[] array, int k, int p)
        {
            var result = comparer.Compare(array[k], array[p]) < 0;
            return result;
        }

        private static void Exchange(T[] array, int k, int p)
        {
            var temp = array[p];
            array[p] = array[k];
            array[k] = temp;
        }
    }
}
