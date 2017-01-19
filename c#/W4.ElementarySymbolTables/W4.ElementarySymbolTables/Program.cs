using System;
using System.IO;

namespace W4.ElementarySymbolTables
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var minLength = 0;

            var st = new OrderedSymbolTable<string, int>(30);

            foreach (var item in File.ReadAllLines(@"test.dat"))
            {
                if (item.Length > minLength)
                {
                    if (!st.Contains(item))
                    {
                        st.Put(item, 1);
                    }
                    else
                    {
                        st.Put(item, st.Get(item) + 1);
                    }
                }
            }

            var max = string.Empty;
            st.Put(max, 0);

            foreach (var key in st.Keys())
            {
                if (st.Get(key) > st.Get(max))
                {
                    max = key;
                }
            }

            Console.WriteLine("Max vaue is {0}", st.Get(max));

            Console.ReadKey();
        }
    }
}
