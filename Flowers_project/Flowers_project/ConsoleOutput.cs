using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

namespace Flowers_project
{
    internal class ConsoleOutput
    {
        public void outputBouquetInLine(Bouquet bouquet)
        {
            int countOfLines = BaseFlower.getCountOfParameters();
            string[] outputLines = new string[countOfLines];
            string[,] allFlowers = new string[countOfLines, bouquet.getBouquet().Length];
            int j = 0;
            foreach (BaseFlower flower in bouquet.getBouquet())     //собираем двумерный массив выводимых строк, каждый внутренний массив соответствует отдельному цветку букета
            {
                string[] buff = flower.toStringForConsole().Split("\n").SkipLast(1).ToArray();
                for (int i = 0; i < countOfLines; i++)
                {
                    allFlowers[i, j] = buff[i];
                }
                j++;
            }

            string line = "";                       //склеиваем строки соответствующих параметров всех цветов букета
            for (int i = 0; i < countOfLines; i++)
            {
                line = "";
                for (int k = 0; k < bouquet.getBouquet().Length; k++)
                {
                    if (i == 7)
                    {
                        int b = 0;
                    }
                    line += allFlowers[i, k];
                    int countOfSpaces = maxLengthInStrings(allFlowers, k) - allFlowers[i, k].Length;    //высчитываем максимальный отступ
                    for (int p = 0; p < countOfSpaces; p++)
                    {
                        line += " ";
                    }

                    line += " | ";
                }
                outputLines[i] = line.Replace('\r', ' ');
            }

            print(outputLines);
        }

        private static int maxLengthInStrings(string[,] array, int j)
        {
            int maxLength = array[0, j].Length;
            for (int i = 0; i < BaseFlower.getCountOfParameters(); i++)
            {
                if (array[i,j].Length > maxLength) maxLength = array[i, j].Length;
            }

            return maxLength;
        }

        private static void print(string[] array)
        {
            for (int i = 0; i < array.Length; i++)
            {
                Console.WriteLine(array[i]);
            }
        }
    }
}
