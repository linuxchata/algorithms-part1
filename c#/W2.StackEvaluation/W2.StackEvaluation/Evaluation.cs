using System;
using System.Collections.Generic;

namespace W2.StackEvaluation
{
    public class Evaluation
    {
        private Stack<int> digits = new Stack<int>();
        private Stack<char> operations = new Stack<char>();

        public int Evaluate(string eval)
        {
            var chars = eval.ToCharArray();

            foreach (var item in chars)
            {
                if (Char.IsDigit(item))
                {
                    this.digits.Push((int)Char.GetNumericValue(item));
                }
                else if (item == '+' || item == '*')
                {
                    this.operations.Push(item);
                }
                else if (item == ')')
                {
                    int value = 0;
                    var operation = this.operations.Pop();
                    if (operation == '+')
                    {
                        value = this.digits.Pop() + this.digits.Pop();
                    }
                    else if (operation == '*')
                    {
                        value = this.digits.Pop() * this.digits.Pop();
                    }

                    this.digits.Push(value);
                }
            }

            return this.digits.Pop();
        }
    }
}
