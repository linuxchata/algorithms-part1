using System.Collections.Generic;

namespace W6.HashTables
{
    public class SparseVector
    {
        private readonly Dictionary<int, double> set;

        public SparseVector()
        {
            this.set = new Dictionary<int, double>();
        }

        public double Get(int i)
        {
            if (this.set.ContainsKey(i))
            {
                return this.set[i];
            }

            return 0;
        }

        public void Put(int i, double d)
        {
            if (!this.set.ContainsKey(i))
            {
                this.set.Add(i, d);
            }
        }

        public double Dot(double[] that)
        {
            var sum = 0.0;

            foreach (var key in this.set.Keys)
            {
                sum += this.Get(key) * that[key];
            }

            return sum;
        }
    }
}
