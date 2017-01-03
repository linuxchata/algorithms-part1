using System.Collections.Generic;

namespace W2.ElementarySorts
{
    public static class ShellSort<T>
    {
        public static T[] Sort(T[] array, Comparer<T> comparer = null)
        {
            var equalityComparer = comparer ?? Comparer<T>.Default;

            int[] increments = new int[3];

            int increment = 0;
            int j = 0;
            while (increment < array.Length)
            {
                increment = 3 * increment + 1;
                if (increment < array.Length)
                {
                    increments[j++] = increment;
                }
            }

            for (int i = increments.Length - 1; i >= 0; i--)
            {
                int x = increments[i];
                for (int k = 0; k < array.Length; k += x)
                {
                    for (int l = k; l > 0; l -= x)
                    {
                        if (equalityComparer.Compare(array[l], array[l - x]) < 0)
                        {
                            var temp = array[l - x];
                            array[l - x] = array[l];
                            array[l] = temp;
                        }
                    }
                }
            }

            return array;
        }
    }
}