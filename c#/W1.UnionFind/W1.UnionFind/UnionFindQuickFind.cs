namespace W1.UnionFind
{
    public class UnionFindQuickFind
    {
        private readonly int[] array;

        private readonly int size;

        public UnionFindQuickFind(int size)
        {
            this.size = size;
            this.array = new int[this.size];

            for (int i = 0; i < this.size; i++)
            {
                this.array[i] = i;
            }
        }

        public void Union(int p, int q)
        {
            var pid = this.array[p];
            var qid = this.array[q];
            for (int i = 0; i < this.size; i++)
            {
                if (this.array[i] == pid)
                {
                    this.array[i] = qid;
                }
            }
        }

        public bool IsConnected(int p, int q)
        {
            return this.array[p] == this.array[q];
        }
    }
}
