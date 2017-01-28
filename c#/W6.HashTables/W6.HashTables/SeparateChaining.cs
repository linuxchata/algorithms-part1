namespace W6.HashTables
{
    public class SeparateChaining<TKey, TValue>
    {
        private readonly int m;

        private readonly Node[] nodes;

        public SeparateChaining(int capacity)
        {
            this.m = capacity / 5;
            this.nodes = new Node[this.m];
        }

        public TValue Get(TKey key)
        {
            var h = this.GethashFunction(key.GetHashCode());
            for (var x = this.nodes[h]; x != null; x = x.Next)
            {
                if (x.Key.Equals(key))
                {
                    return x.Value;
                }
            }

            return default(TValue);
        }

        public void Add(TKey key, TValue value)
        {
            var h = this.GethashFunction(key.GetHashCode());
            for (var x = this.nodes[h]; x != null; x = x.Next)
            {
                if (x.Key.Equals(key))
                {
                    x.Value = value;
                    return;
                }
            }

            this.nodes[h] = new Node(key, value, this.nodes[h]);
        }

        private int GethashFunction(int hashCode)
        {
            return (hashCode & 0x7fffffff) % this.m;
        }

        private class Node
        {
            public Node(TKey key, TValue value, Node next)
            {
                this.Key = key;
                this.Value = value;
                this.Next = next;
            }

            public TKey Key { get; private set; }

            public TValue Value { get; set; }

            public Node Next { get; set; }

            public override string ToString()
            {
                return string.Format("Key {0}. Value {1}", this.Key, this.Value);
            }
        }
    }
}
