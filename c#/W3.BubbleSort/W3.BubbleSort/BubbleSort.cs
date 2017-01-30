using System.Collections.Generic;

namespace W3.BubbleSort
{
    public static class BubbleSort<T>
    {
        public static void Sort(T[] array, Comparer<T> c = null)
        {
            var comparer = c ?? Comparer<T>.Default;
            for (var j = 1; j < array.Length; j++)
            {
                for (var i = 0; i < array.Length - j; i++)
                {
                    var r = comparer.Compare(array[i], array[i + 1]);
                    if (r > 0)
                    {
                        Swap(array, i, i + 1);
                    }
                }
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
