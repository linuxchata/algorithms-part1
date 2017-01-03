using System;

namespace W2.ElementarySorts
{
    public static class Shuffling<T>
    {
        private static Random random = new Random();

        public static T[] Shuffle(T[] array)
        {
            for (int i = 0; i < array.Length; i++)
            {
                var r = random.Next(0, i + 1);
                var temp = array[i];
                array[i] = array[r];
                array[r] = temp;
            }

            return array;
        }
    }
}
