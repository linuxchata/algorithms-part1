using System;
using System.Collections.Generic;

namespace W4.ElementarySymbolTables
{
    public class SymbolTableLinkedList<TKey, TValue>
    {
        private Node firstNode;

        public bool Contains(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key");
            }

            if (this.firstNode == null)
            {
                return false;
            }

            var node = this.firstNode;

            var c = EqualityComparer<TKey>.Default;

            while (node != null)
            {
                if (c.Equals(node.Key, key))
                {
                    return true;
                }
                node = node.NextNode;
            }

            return false;
        }

        public List<TKey> Keys()
        {
            if (this.firstNode == null)
            {
                return new List<TKey>();
            }

            var node = this.firstNode;

            var result = new List<TKey>();
            while (node != null)
            {
                result.Add(node.Key);
                node = node.NextNode;
            }

            return result;
        }

        public TValue Get(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key");
            }

            if (this.firstNode == null)
            {
                return default(TValue);
            }

            var node = this.firstNode;

            var c = EqualityComparer<TKey>.Default;

            while (node != null)
            {
                if (c.Equals(node.Key, key))
                {
                    return node.Value;
                }
                node = node.NextNode;
            }

            return default(TValue);
        }

        public void Put(TKey key, TValue value)
        {
            if (this.firstNode == null)
            {
                this.firstNode = new Node(key, value, null);
            }
            else
            {
                var node = new Node(key, value, this.firstNode);
                this.firstNode = node;
            }
        }

        private class Node
        {
            public Node(TKey key, TValue value, Node nextNode)
            {
                this.Key = key;
                this.Value = value;
                this.NextNode = nextNode;
            }

            public Node NextNode { get; set; }

            public TKey Key { get; private set; }

            public TValue Value { get; set; }
        }
    }
}
