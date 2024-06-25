using System;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.CompilerServices;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Flowers_project
{
    internal class Bouquet
    {
        private List<BaseFlower> bouquet;
        private List<uint> allID;
        /*public Bouquet(string path)
        {
            this.allID = new List<byte>();
            try
            {
                Read(path);
            }
            catch (InvalidDataException)
            {
                Console.WriteLine("\nInvalid data in file!\n");
            }
        }*/
        public Bouquet(BaseFlower[] bouquet) : this(bouquet.ToList()) { 
        }

        public Bouquet(List<BaseFlower> bouquet)
        {
            this.allID = new List<uint>();
            this.bouquet = bouquet;
        }
        public BaseFlower[] getBouquet() { return bouquet.ToArray(); }
        public List<uint> getAllID() { return allID; }
        public double getCostOfBouquet()
        {
            double cost = 0;
            foreach (BaseFlower flower in bouquet)
            {
                cost += flower.getCost();
            }

            return Math.Round(cost * 100) / 100;
        }

        public void Print()
        {
            Console.WriteLine("\n");
            ConsoleOutput output = new ConsoleOutput();
            output.outputBouquetInLine(this);
            /*foreach (BaseFlower flower in bouquet)
            {
                Console.Write(flower.toStringForConsole() + "\n");
            }*/
        }

        public void Write(string path)
        {
            StreamWriter writer = new StreamWriter(path, false);
            foreach (BaseFlower flower in bouquet)
            {
                writer.WriteLine(flower.toStringForFile());
            }
            writer.Close();
        }

        public static Bouquet Read(string path)
        {
            StreamReader reader = new StreamReader(path);
            //List<BaseFlower> bouquet = new List<BaseFlower>();
            Bouquet bouquet = new Bouquet(new List<BaseFlower>());
            string[] all = reader.ReadToEnd().Split('\n');
            reader.Close();
            all = all.SkipLast(1).ToArray();
            string[] line;
            //while (!reader.EndOfStream)
            foreach(string str in all)
            {
                line = str.Split('~');
                try
                {
                    bouquet.Add(createFlower(line));
                }
                catch (InvalidDataException)
                {
                    throw new InvalidDataException();
                }
            }

            return bouquet;//new Bouquet(bouquet);
        }

        public Bouquet findFlowerByLength(int min, int max)
        {
            List<BaseFlower> result = new List<BaseFlower>();
            foreach (BaseFlower flower in bouquet)
            {
                if ((flower.getLength() >= min) && (flower.getLength() <= max))
                {
                    result.Add(flower);
                }
            }

            if (result.ToArray().Length == 0)
            {
                throw new InvalidDataException();

            }
            return new Bouquet(result);
        }

        public Bouquet sortByFreshness(bool ascending)
        {
            BaseFlower[] array = this.bouquet.ToArray();
            for (int i = 0; i < array.Length; i++)
            {
                for (int j = i; j < array.Length; j++)
                {
                    if ((ascending && (array[i].getFreshness() > array[j].getFreshness())) || (!ascending && (array[i].getFreshness() < array[j].getFreshness())))
                    {
                        BaseFlower buff = array[i];
                        array[i] = array[j];
                        array[j] = buff;
                    }
                }
            }

            return new Bouquet(array.ToList<BaseFlower>());
            //return array;
        }

        private void Add(BaseFlower flower)
        {
            uint curID = 0;
            while (allID.Contains(curID))
            {
                curID = Convert.ToUInt32(RandomNumberGenerator.GetInt32(500));
            }
            allID.Add(curID);
            flower.setID(curID);
            bouquet.Add(flower);
        }

        public void addFlowerFromConsole()
        {
            bouquet.Add(readFlowerFromConsole());
        }
        private static BaseFlower readFlowerFromConsole()
        {
            string[] line = new string[7];
            /*Console.WriteLine("Enter the paramenters in the order type-name-length-freshness-cost-color-specialParameter");
            for (int i = 0; i < 7;  i++)
            {
                line[i] = Console.ReadLine();
            }*/

            do
            {
                line[0] = input("Enter the type: ");
            } while (!BaseFlower.getTypes().Contains(line[0]));
            line[1] = input("Enter the name: ");
            do
            {
                line[2] = input("Enter the length (cm): ");
            } while (!isDigit(line[2]));
            do
            {
                //bool validFreshness = isDigit(line[3]) ? (Convert) : false;
                line[3] = input("Enter the freshness (1-10): ");
            } while (!(isDigit(line[3]) && (Convert.ToInt32(line[3]) > 0) && (Convert.ToInt32(line[3]) < 11)));

            do
            {
                line[4] = input("Enter the cost (BY): ");
            } while (!isDouble(line[4]));

            line[5] = input("Enter the color: ");
            switch (line[0])
            {
                case "rose":
                    do
                    {
                        line[6] = input("Enter the length of pickles (mm): ");
                    } while (!isDigit(line[6]));
                    break;
                case "tulip":
                    line[6] = input("Enter the form of turnip: ");
                    break;
                case "camomile":
                    do
                    {
                        line[6] = input("Enter the count of petal: ");
                    } while (!isDigit(line[6]));
                    break;
            }
            try 
            {
                return createFlower(line);
            }
            catch (InvalidDataException)
            {
                throw new InvalidDataException();
            }
        }

        /*private static void InputParameter(string output, ref string str, bool predicate)
        {
            do
            {
                Console.Write("\n" + output);
                str = Console.ReadLine();
            } while (predicate);
        }*/
        public static BaseFlower createFlower(string[] line)
        {
            switch (line[0])
            {
                case "rose":
                    return new Rose(line);
                case "tulip":
                    return new Tulip(line);
                case "camomile":
                    return new Camomile(line);
                default:
                    throw new InvalidDataException();
            }
        }

        public void Remove(uint ID)
        {
            if (!allID.Contains(ID))
            {
                throw new InvalidDataException(nameof(ID));
            }

            this.allID.Remove(ID);
            foreach(BaseFlower flower in this.bouquet)
            {
                if (flower.getID() == ID)
                {
                    this.bouquet.Remove(flower);
                    break;
                }
            }
        }
        private static string input(string output)
        {
            Console.Write(output);
            return Console.ReadLine();
        }

        private static bool isDigit(string str)
        {
            foreach (char c in str)
            {
                if ((c > '9') || (c < '0'))
                {
                    return false;
                }
            }

            return true;
        }

        private static bool isDouble(string str)
        {
            foreach (char c in str)
            {
                if (((c > '9') || (c < '0')) && (c != ','))
                {
                    return false;
                }
            }

            return true;
        }
        /*
        public void PrintSortedArray(BaseFlower[] array)
        {)
        }
        */
    }
}
