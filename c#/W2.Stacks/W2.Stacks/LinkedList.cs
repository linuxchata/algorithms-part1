namespace W2.Stacks
{
    public class LinkedList<T>
    {
        private Node firstNode;

        public void AddToHead(T item)
        {
            var node = new Node(item);
            node.Next = this.firstNode;
            this.firstNode = node;
        }

        public T RemoveFromHead()
        {
            var item = this.firstNode;
            this.firstNode = this.firstNode.Next;
            return item.Value;
        }

        private class Node
        {
            public Node(T value)
            {
                this.Value = value;
            }

            public Node Next { get; set; }

            public T Value { get; private set; }

            public override string ToString()
            {
                return this.Value.ToString();
            }
        }
    }
}
