namespace W2.Queue
{
    public class Queue
    {
        private string[] array;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int capacity)
        {
            this.capacity = capacity;
            this.array = new string[this.capacity];
        }

        public void Enqueue(string value)
        {
            if (tail == capacity)
            {
                if (tail - head < capacity / 2)
                {
                    this.Shuffle();
                }
                else
                {
                    this.Resize();
                }
            }

            this.array[tail++] = value;
        }

        public string Dequeue()
        {
            var value = this.array[head];
            this.array[head++] = null;

            if ((tail - head) <= capacity / 4)
            {

            }

            return value;
        }

        private void Shuffle()
        {
            var temp = this.array;

            int j = 0;
            for(var i= 0; i < this.capacity; i++)
            {
                if (temp[i] != null)
                {
                    this.array[j++] = temp[i];
                    this.array[i] = null;
                }
            }

            this.head = 0;
            this.tail = j;
        }

        private void Resize()
        {
            var oldCapacity = this.capacity;
            var temp = this.array;

            this.capacity *= 2;
            this.array = new string[this.capacity];
            for (int i = 0; i < oldCapacity; i++)
            {
                this.array[i] = temp[i];
            }

            this.head = 0;
            this.tail = oldCapacity;
        }
    }
}
