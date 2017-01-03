using System.Collections.Generic;

namespace W2.ElementarySorts
{
    public static class InsertionSort<T>
    {
        public static T[] Sort(T[] array, Comparer<T> comparer = null)
        {
            var equalityComparer = comparer ?? Comparer<T>.Default;

            for (int i = 0; i < array.Length; i++)
            {
                for (int j = i; j > 0; j--)
                {
                    if (equalityComparer.Compare(array[j], array[j - 1]) == -1)
                    {
                        var temp = array[j];
                        array[j] = array[j - 1];
                        array[j - 1] = temp;
                    }
                    else
                    {
                        break;
                    }
                }
            }

            return array;
        }
    }
}
