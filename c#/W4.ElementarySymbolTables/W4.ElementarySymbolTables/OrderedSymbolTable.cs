using System;
using System.Collections.Generic;
using System.Linq;

namespace W4.ElementarySymbolTables
{
    public class OrderedSymbolTable<TKey, TValue>
    {
        private readonly int capacity;

        private readonly Comparer<TKey> comparer;

        private TKey[] keys;

        private TValue[] values;

        private int n;

        public OrderedSymbolTable(int capacity)
        {
            this.capacity = capacity;
            this.comparer = Comparer<TKey>.Default;

            this.keys = new TKey[capacity];
            this.values = new TValue[capacity];

            this.n = 0;
        }

        public bool Contains(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key");
            }

            var r = this.Rank(key);

            return Contains(r, key);
        }

        public TValue Get(TKey key)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key");
            }

            var r = this.Rank(key);

            if (this.Contains(r, key))
            {
                return this.values[r];
            }

            throw new KeyNotFoundException("Key cannot be found");
        }

        public void Put(TKey key, TValue value)
        {
            if (key == null)
            {
                throw new ArgumentNullException("key");
            }

            var r = this.Rank(key);

            if (this.Contains(r, key))
            {
                this.values[r] = value;
                return;
            }

            this.n++;

            var newValues = new TValue[this.capacity];
            var newKeys = new TKey[this.capacity];

            for (var i = 0; i < this.n; i++)
            {
                if (i < r)
                {
                    newValues[i] = this.values[i];
                    newKeys[i] = this.keys[i];
                }
                else if (i == r)
                {
                    newValues[i] = value;
                    newKeys[i] = key;
                }
                else
                {
                    newValues[i] = this.values[i - 1];
                    newKeys[i] = this.keys[i - 1];
                }
            }

            this.keys = newKeys;
            this.values = newValues;
        }

        public List<TKey> Keys()
        {
            var result = this.keys.Where(k => k != null).ToList();
            return result;
        }

        private int Rank(TKey key)
        {
            var lo = 0;
            var hi = this.n - 1;

            while (lo <= hi)
            {
                var m = lo + (hi - lo) / 2;

                var c = this.comparer.Compare(key, this.keys[m]);
                if (c > 0)
                {
                    lo = m + 1;
                }
                else if (c < 0)
                {
                    hi = m - 1;
                }
                else
                {
                    return m;
                }
            }

            return lo;
        }

        private bool Contains(int r, TKey key)
        {
            return r < this.n && this.comparer.Compare(this.keys[r], key) == 0; ;
        }
    }
}
