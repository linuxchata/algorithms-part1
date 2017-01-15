using System.Collections;
using System.Collections.Generic;

namespace W4.BinaryHeap
{
    public class BinaryHeap<T> : IEnumerable, IEnumerable<T>
    {
        private readonly T[] internalArray;
        private readonly Comparer<T> comparer;
        private int n;

        public BinaryHeap(int capacity)
        {
            this.internalArray = new T[capacity];
            this.comparer = Comparer<T>.Default;
        }

        public void Add(T item)
        {
            this.internalArray[++n] = item;
            this.Swim(n);
        }

        public T DeleteMax()
        {
            if (this.n == 0)
            {
                return default(T);
            }

            var max = this.internalArray[1];
            this.Exchange(1, n);
            this.internalArray[n--] = default(T);
            this.Sink(1);
            return max;
        }

        public int Count()
        {
            return n;
        }

        private void Sink(int k)
        {
            while (2 * k <= this.n)
            {
                var child = 2 * k;
                if (child < this.n && Less(2 * k, 2 * k + 1))
                {
                    child++;
                }

                if (!this.Less(k, child))
                {
                    break;
                }

                this.Exchange(child, k);
                k = child;
            }
        }

        private void Swim(int k)
        {
            while (k > 1 && !this.Less(k, k / 2))
            {
                this.Exchange(k, k / 2);
                k = k / 2;
            }
        }

        private bool Less(int k, int p)
        {
            var result = this.comparer.Compare(this.internalArray[k], this.internalArray[p]) < 0;
            return result;
        }

        private void Exchange(int k, int p)
        {
            var temp = this.internalArray[p];
            this.internalArray[p] = this.internalArray[k];
            this.internalArray[k] = temp;
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.internalArray.GetEnumerator();
        }

        IEnumerator<T> IEnumerable<T>.GetEnumerator()
        {
            foreach (var item in this.internalArray)
            {
                yield return item;
            }
        }
    }
}
