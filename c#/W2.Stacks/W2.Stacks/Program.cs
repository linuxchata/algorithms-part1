using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;

namespace W2.Stacks
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var stack = new Stack<string>();

            var input = File.ReadAllLines("data.dat").ToList<string>();

            foreach (var item in input)
            {
                if (string.Equals(item, "-", StringComparison.OrdinalIgnoreCase))
                {
                    Console.Write(stack.Pop() + " ");
                }
                else
                {
                    stack.Push(item);
                }
            }

            Console.WriteLine();

            var linkedList = new LinkedList<string>();
            linkedList.AddToHead("Node 1");
            linkedList.AddToHead("Node 2");
            linkedList.AddToHead("Node 3");
            Console.WriteLine(linkedList.RemoveFromHead());
            Console.WriteLine(linkedList.RemoveFromHead());
            Console.WriteLine(linkedList.RemoveFromHead());

            var stack2 = new LinkedList<string>();
            foreach (var item in input)
            {
                if (string.Equals(item, "-", StringComparison.OrdinalIgnoreCase))
                {
                    Console.Write(stack2.RemoveFromHead() + " ");
                }
                else
                {
                    stack2.AddToHead(item);
                }
            }

            Console.WriteLine();

            var stack3 = new ArrayImplementation(10);
            foreach (var item in input)
            {
                if (string.Equals(item, "-", StringComparison.OrdinalIgnoreCase))
                {
                    Console.Write(stack3.Pop() + " ");
                }
                else
                {
                    stack3.Push(item);
                }
            }

            Console.WriteLine();

            Console.ReadKey();
        }
    }
}
