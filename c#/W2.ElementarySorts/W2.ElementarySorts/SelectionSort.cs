using System;
using System.Collections.Generic;

namespace W2.ElementarySorts
{
    public static class SelectionSort<T>
    {
        public static T[] Sort(T[] array, Comparer<T> comparer = null)
        {
            var equalityComparer = comparer ?? Comparer<T>.Default;
            for (int i = 0; i < array.Length; i++)
            {
                int min = i;
                for (int j = i + 1; j < array.Length; j++)
                {
                    if (equalityComparer.Compare(array[j], array[min]) == -1)
                    {
                        min = j;
                    }
                }

                var temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }

            return array;
        }
    }
}
