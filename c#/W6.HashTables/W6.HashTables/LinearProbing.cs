namespace W6.HashTables
{
    public class LinearProbing<TKey, TValue>
    {
        private readonly int m;

        private readonly TKey[] keys;

        private readonly TValue[] values;

        public LinearProbing(int capacity)
        {
            this.m = capacity * 2;

            this.keys = new TKey[this.m];
            this.values = new TValue[this.m];
        }

        public TValue Get(TKey key)
        {
            var h = this.GetHashFunction(key.GetHashCode());

            for (var i = h; this.keys[i] != null; i = (i + 1) % this.m)
            {
                if (this.keys[i].Equals(key))
                {
                    return this.values[i];
                }
            }

            return default(TValue);
        }

        public void Add(TKey key, TValue value)
        {
            var h = this.GetHashFunction(key.GetHashCode());

            int i;
            for (i = h; this.keys[i] != null; i = i % this.m)
            {
                if (this.keys[i].Equals(key))
                {
                    break;
                }
            }

            this.keys[i] = key;
            this.values[i] = value;
        }

        private int GetHashFunction(int hashCode)
        {
            return (hashCode & 0x7fffffff) % this.m;
        }
    }
}
