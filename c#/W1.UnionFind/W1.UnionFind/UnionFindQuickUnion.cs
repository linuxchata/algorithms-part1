namespace W1.UnionFind
{
    public class UnionFindQuickUnion
    {
        private int[] array;

        private readonly int size;

        public UnionFindQuickUnion(int size)
        {
            this.size = size;
            this.array = new int[this.size];

            for (var i = 0; i < this.size; i++)
            {
                this.array[i] = i;
            }
        }

        public void Union(int p, int q)
        {
            var pRoot = this.GetRoot(p);
            var qRoot = this.GetRoot(q);
            this.array[pRoot] = qRoot;
        }

        private int GetRoot(int n)
        {
            var root = this.array[n];
            while (this.array[root] != root)
            {
                root = this.array[root];
            }

            return root;
        }

        public bool IsConnected(int p, int q)
        {
            return this.GetRoot(p) == this.GetRoot(q);
        }
    }
}
