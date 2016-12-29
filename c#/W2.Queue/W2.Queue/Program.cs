using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace W2.Queue
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var queue = new Queue(12);
            queue.Enqueue("1");
            queue.Enqueue("2");
            queue.Enqueue("3");
            queue.Enqueue("4");
            queue.Enqueue("5");
            queue.Enqueue("6");
            queue.Enqueue("7");
            queue.Enqueue("8");
            queue.Enqueue("9");
            queue.Enqueue("10");
            queue.Enqueue("11");
            queue.Enqueue("12");

            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());

            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());

            Console.WriteLine(queue.Dequeue());
            Console.WriteLine(queue.Dequeue());

            // Shufle
            queue.Enqueue("13");
            queue.Enqueue("14");
            queue.Enqueue("15");
            queue.Enqueue("16");
            queue.Enqueue("17");
            queue.Enqueue("18");
            queue.Enqueue("19");
            queue.Enqueue("20");

            // Resize
            queue.Enqueue("21");
            queue.Enqueue("22");

            Console.ReadKey();
        }
    }
}
