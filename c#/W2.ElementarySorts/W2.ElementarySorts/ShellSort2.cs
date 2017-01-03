using System.Collections.Generic;

namespace W2.ElementarySorts
{
    public static class ShellSort2<T>
    {
        public static T[] Sort(T[] array, Comparer<T> comparer = null)
        {
            var equalityComparer = comparer ?? Comparer<T>.Default;

            int n = array.Length;

            int h = 1;
            while (h < array.Length / 3)
            {
                h = 3 * h + 1;
            }

            while (h >= 1)
            {
                for (int k = 0; k < array.Length; k += h)
                {
                    for (int l = k; l > 0; l -= h)
                    {
                        if (equalityComparer.Compare(array[l], array[l - h]) < 0)
                        {
                            var temp = array[l - h];
                            array[l - h] = array[l];
                            array[l] = temp;
                        }
                    }
                }
                h = h / 3;
            }

            return array;
        }
    }
}