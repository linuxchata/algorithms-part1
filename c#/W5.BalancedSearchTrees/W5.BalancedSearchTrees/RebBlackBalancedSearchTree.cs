using System.Collections.Generic;

namespace W5.BalancedSearchTrees
{
    public class RebBlackBalancedSearchTree<TKey, TValue>
    {
        private readonly Comparer<TKey> comparer;

        private Node root;

        public RebBlackBalancedSearchTree()
        {
            this.comparer = Comparer<TKey>.Default;
        }

        public TValue Get(TKey key)
        {
            var x = this.root;
            while (x != null)
            {
                var c = this.comparer.Compare(key, x.Key);
                if (c > 0)
                {
                    x = x.Left;
                }
                else if (c < 0)
                {
                    x = x.Right;
                }
                else
                {
                    return x.Value;
                }
            }

            return default(TValue);
        }

        public void Put(TKey key, TValue value)
        {
            this.root = this.Put(this.root, key, value);
        }

        private Node Put(Node h, TKey key, TValue value)
        {
            if (h == null)
            {
                return new Node(key, value, Node.Red);
            }

            var c = this.comparer.Compare(key, h.Key);
            if (c > 0)
            {
                h.Right = this.Put(h.Right, key, value);
            }
            else if (c < 0)
            {
                h.Left = this.Put(h.Left, key, value);
            }
            else
            {
                h.Value = value;
            }

            if (h.Right.IsRed() && !h.Left.IsRed())
            {
                this.RotateLeft(h);
            }
            if (h.Left.IsRed() && h.Left.Left.IsRed())
            {
                this.RotateRight(h);
            }
            if (h.Left.IsRed() && h.Right.IsRed())
            {
                this.FlipColors(h);
            }

            return h;
        }

        private Node RotateLeft(Node h)
        {
            var x = h.Right;
            h.Right = x.Left;
            x.Left = h;
            x.Left.Color = h.Color;
            h.Color = Node.Red;
            return x;
        }

        private Node RotateRight(Node h)
        {
            var x = h.Left;
            h.Left = x.Right;
            x.Right = h;
            x.Right.Color = h.Color;
            h.Color = Node.Red;
            return x;
        }

        private void FlipColors(Node h)
        {
            h.Color = Node.Red;
            h.Left.Color = Node.Black;
            h.Right.Color = Node.Black;
        }

        private class Node
        {
            public static bool Red = true;

            public static bool Black = false;

            public Node(TKey key, TValue value, bool color)
            {
                this.Key = key;
                this.Value = value;
                this.Color = color;
            }

            public TKey Key { get; set; }

            public TValue Value { get; set; }

            public bool Color { get; set; }

            public Node Left { get; set; }

            public Node Right { get; set; }

            public bool IsRed()
            {
                return this.Color == Node.Red;
            }
        }
    }
}
