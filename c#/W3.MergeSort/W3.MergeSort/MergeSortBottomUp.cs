using System;
using System.Collections.Generic;

namespace W3.MergeSort
{
    public class MergeSortBottomUp<T>
    {
        public static T[] Sort(T[] a)
        {
            var n = a.Length;

            var aux = new T[n];

            for (var sz = 1; sz < n; sz = sz + sz)
            {
                for (var lo = 0; lo < n - sz; lo += sz + sz)
                {
                    Merge(a, aux, lo, lo + sz - 1, Math.Min(lo + sz + sz - 1, n - 1));
                }
            }

            return a;
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
