using System.Collections.Generic;

namespace W3.MergeSort
{
    public class MergeSort<T>
    {
        public static T[] Sort(T[] array)
        {
            var aux = new T[array.Length];

            Merge(array, aux, 0, array.Length / 2, array.Length);

            return array;
        }

        private static void Merge(T[] a, T[] aux, int lo, int mid, int hi, Comparer<T> comparer = null)
        {
            if (hi - lo > 2)
            {
                var mid1 = (lo + mid) / 2;
                Merge(a, aux, lo, mid1, mid);

                var mid2 = (mid + hi) / 2;
                Merge(a, aux, mid, mid2, hi);
            }

            var equalityComparer = comparer ?? Comparer<T>.Default;

            for (var k = lo; k < hi; k++)
            {
                aux[k] = a[k];
            }

            var i = lo;
            var j = mid;
            for (var k = lo; k < hi; k++)
            {
                if (i >= mid)
                {
                    a[k] = aux[j++];
                }
                else if (j >= hi)
                {
                    a[k] = aux[i++];
                }
                else if (equalityComparer.Compare(aux[i], aux[j]) < 0)
                {
                    a[k] = aux[i++];
                }
                else
                {
                    a[k] = aux[j++];
                }
            }
        }
    }
}
