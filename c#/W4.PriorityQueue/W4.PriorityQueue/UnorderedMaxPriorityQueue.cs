using System.Collections.Generic;

namespace W4.PriorityQueue
{
    public class UnorderedMaxPriorityQueue<T>
    {
        private readonly T[] priorityQueue;
        private readonly Comparer<T> comparer;
        private int numberOfElements;

        public UnorderedMaxPriorityQueue(int capacity)
        {
            this.priorityQueue = new T[capacity];
            this.comparer = Comparer<T>.Default;
        }

        public bool Any()
        {
            return numberOfElements != 0;
        }

        public void Add(T item)
        {
            priorityQueue[numberOfElements++] = item;
        }

        public T DeleteMax()
        {
            var max = 0;
            for (var i = 0; i < this.numberOfElements; i++)
            {
                if (comparer.Compare(priorityQueue[i], priorityQueue[max]) > 0)
                {
                    max = i;
                }
            }

            this.Exchange(max);

            var result = this.priorityQueue[numberOfElements - 1];
            this.priorityQueue[--numberOfElements] = default(T);

            return result;
        }

        private void Exchange(int max)
        {
            T temp = this.priorityQueue[numberOfElements - 1];
            this.priorityQueue[numberOfElements - 1] = this.priorityQueue[max];
            this.priorityQueue[max] = temp;

        }
    }
}
