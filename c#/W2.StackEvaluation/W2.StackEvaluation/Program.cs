using System;

namespace W2.StackEvaluation
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var input = "(1 + ((2 + 3) * (4 * 5)))";

            var eval = new Evaluation();
            Console.WriteLine(eval.Evaluate(input));

            Console.ReadKey();
        }
    }
}
