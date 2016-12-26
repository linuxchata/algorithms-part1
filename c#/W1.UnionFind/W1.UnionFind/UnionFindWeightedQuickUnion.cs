namespace W1.UnionFind
{
    public class UnionFindWeightedQuickUnion
    {
        private int[] array;
        private int[] sizes;

        private readonly int size;

        public UnionFindWeightedQuickUnion(int size)
        {
            this.size = size;
            this.array = new int[this.size];
            this.sizes = new int[this.size];

            for (var i = 0; i < this.size; i++)
            {
                this.array[i] = i;
                this.sizes[i] = 1;
            }
        }

        public void Union(int p, int q)
        {
            var pRoot = this.GetRoot(p);
            var qRoot = this.GetRoot(q);
            if (pRoot == qRoot)
            {
                return;
            }
            if (this.sizes[pRoot] >= this.sizes[qRoot])
            {
                this.array[qRoot] = pRoot;
                this.sizes[pRoot] += this.sizes[qRoot];
            }
            else
            {
                this.array[pRoot] = qRoot;
                this.sizes[qRoot] += this.sizes[pRoot];
            }
        }

        public bool IsConnected(int p, int q)
        {
            return this.GetRoot(p) == this.GetRoot(q);
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
    }
}
