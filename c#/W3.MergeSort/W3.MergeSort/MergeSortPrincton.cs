using System.Collections.Generic;

namespace W3.MergeSort
{
    public class MergeSortPrincton<T>
    {
        public static T[] Sort(T[] a)
        {
            var aux = new T[a.Length];
            Sort(a, aux, 0, a.Length - 1);
            return a;
        }

        private static void Sort(T[] a, T[] aux, int lo, int hi)
        {
            if (hi <= lo)
            {
                return;
            }

            var mid = lo + (hi - lo) / 2;
            Sort(a, aux, lo, mid);
            Sort(a, aux, mid + 1, hi);
            Merge(a, aux, lo, mid, hi);
        }

        private static void Merge(T[] a, T[] aux, int lo, int mid, int hi, Comparer<T> comparer = null)
        {
            var equalityComparer = comparer ?? Comparer<T>.Default;

            for (var k = lo; k <= hi; k++)
            {
                aux[k] = a[k];
            }

            var i = lo;
            var j = mid + 1;
            for (var k = lo; k <= hi; k++)
            {
                if (i > mid)
                {
                    a[k] = aux[j++];
                }
                else if (j > hi)
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
