using System;
using System.Collections.Generic;

namespace W4.BinarySearchTrees
{
    public class BinarySearchTree<TKey, TValue>
    {
        private Node rootNode;

        private readonly Comparer<TKey> comparer;

        public BinarySearchTree()
        {
            this.comparer = Comparer<TKey>.Default;
        }

        public TValue Get(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            if (this.rootNode == null)
            {
                throw new KeyNotFoundException("Key cannot be found");
            }

            var current = this.rootNode;
            while (current != null)
            {
                var c = this.comparer.Compare(key, current.Key);
                if (c < 0)
                {
                    current = current.LeftNode;
                }
                else if (c > 0)
                {
                    current = current.RightNode;
                }
                else
                {
                    return current.Value;
                }
            }

            return default(TValue);
        }

        public void Put(TKey key, TValue value)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            if (value == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            this.rootNode = this.Put(this.rootNode, key, value);
        }

        public void Put2(TKey key, TValue value)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            if (value == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            if (this.rootNode == null)
            {
                this.rootNode = new Node(key, value, 1);
                return;
            }

            var current = this.rootNode;
            while (current != null)
            {
                var c = this.comparer.Compare(key, current.Key);
                if (c < 0)
                {
                    if (current.LeftNode == null)
                    {
                        current.LeftNode = new Node(key, value, 1);
                        break;
                    }
                    current = current.LeftNode;
                }
                else if (c > 0)
                {
                    if (current.RightNode == null)
                    {
                        current.RightNode = new Node(key, value, 1);
                        break;
                    }
                    current = current.RightNode;
                }
                else
                {
                    current.Value = value;
                    return;
                }
            }
        }

        public TKey Floor(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key", "Key cannot be null");
            }

            var node = Floor(this.rootNode, key);
            return node.Key;
        }

        public int Rank(TKey key)
        {
            return Rank(this.rootNode, key);
        }

        public IEnumerable<TKey> Keys()
        {
            var queue = new Queue<TKey>();

            this.InOrder(this.rootNode, queue);

            return queue;
        }

        public int Count()
        {
            return this.Count(this.rootNode);
        }

        public void DeleteMin()
        {
            this.DeleteMin(this.rootNode);
        }

        private Node DeleteMin(Node node)
        {
            if (node.LeftNode == null)
            {
                return node.RightNode;
            }

            node.LeftNode = this.DeleteMin(node.LeftNode);

            node.Count = 1 + this.Count(node.LeftNode) + this.Count(node.RightNode);

            return node;
        }

        private Node Put(Node x, TKey key, TValue value)
        {
            if (x == null)
            {
                return new Node(key, value, 1);
            }

            var c = this.comparer.Compare(key, x.Key);
            if (c < 0)
            {
                x.LeftNode = this.Put(x.LeftNode, key, value);
            }
            else if (c > 0)
            {
                x.RightNode = this.Put(x.RightNode, key, value);
            }
            else
            {
                x.Value = value;
            }

            x.Count = 1 + this.Count(x.LeftNode) + this.Count(x.RightNode);

            return x;
        }

        private int Rank(Node node, TKey key)
        {
            if (node == null)
            {
                return 0;
            }

            var c = this.comparer.Compare(key, node.Key);
            if (c > 0)
            {
                return 1 + this.Count(node.LeftNode) + this.Rank(node.RightNode, key);
            }
            else if (c < 0)
            {
                return this.Rank(node.LeftNode, key);
            }
            else
            {
                return this.Count(node.LeftNode);
            }
        }

        private int Count(Node x)
        {
            if (x == null)
            {
                return 0;
            }

            return x.Count;
        }

        private Node Floor(Node x, TKey key)
        {
            if (x == null)
            {
                return null;
            }

            var c = this.comparer.Compare(key, x.Key);

            if (c == 0)
            {
                return x;
            }

            if (c < 0)
            {
                return this.Floor(x.LeftNode, key);
            }

            var t = this.Floor(x.RightNode, key);
            if (t != null)
            {
                return t;
            }
            else
            {
                return x;
            }
        }

        private void InOrder(Node node, Queue<TKey> queue)
        {
            if (node == null)
            {
                return;
            }
            this.InOrder(node.LeftNode, queue);
            queue.Enqueue(node.Key);
            this.InOrder(node.RightNode, queue);
        }

        private class Node
        {
            public Node(TKey key, TValue value, int count)
            {
                this.Key = key;
                this.Value = value;
                this.Count = count;
            }

            public TKey Key { get; set; }

            public TValue Value { get; set; }

            public Node LeftNode { get; set; }

            public Node RightNode { get; set; }

            public int Count { get; set; }

            public override string ToString()
            {
                return string.Format("Key: {0}. Value: {1}. Count: {2}", this.Key, this.Value, this.Count);
            }
        }
    }
}
