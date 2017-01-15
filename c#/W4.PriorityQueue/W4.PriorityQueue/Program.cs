using System;

namespace W4.PriorityQueue
{
    public class Program
    {
        public static void Main(string[] args)
        {
            TestPriorityQueue();

            Console.ReadLine();
        }

        private static void TestPriorityQueue()
        {
            var pq = new UnorderedMaxPriorityQueue<int>(10);
            pq.Add(100);
            pq.Add(0);
            pq.Add(15);
            pq.Add(105);
            pq.Add(27);
            pq.Add(27);
            pq.Add(270);
            pq.Add(20);
            pq.Add(-9);

            Console.WriteLine(pq.DeleteMax());
        }
    }
}
