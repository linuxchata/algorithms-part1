namespace W2.Stacks
{
    public class ArrayImplementation
    {
        private string[] array;
        private int index;

        public ArrayImplementation(int capacity)
        {
            this.array = new string[capacity];
            this.index = 0;
        }

        public void Push(string value)
        {
            this.array[index++] = value;
        }

        public string Pop()
        {
            return this.array[--this.index];
        }
    }
}
