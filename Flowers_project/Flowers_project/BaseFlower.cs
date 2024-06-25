using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace Flowers_project
{
    internal abstract class BaseFlower
    {
        private uint id;
        private readonly string name;
        private readonly int length;
        private readonly int freshness;
        private readonly double cost;
        private readonly string color;
        //protected string strForFile;
        protected string type = "baseType";
        /*private const string rose = "rose";
        private const string tulip = "tulip";
        private const string camomile = "camomile";*/
        private static string[] types = { "rose", "tulip", "camomile" };
        private static int countOfParameters = 8;

        public BaseFlower(string name, int length, int freshness, double cost, string color) 
        {
            this.name = name;
            this.length = length;
            this.freshness = freshness;
            this.cost = cost;
            this.color = color;
            //strForFile = name + "~" + length.ToString() + "~" + freshness.ToString() + "~" + cost.ToString() + "~" + color;
        }

        public BaseFlower(string[] arrayOfParameters)
        {
            if (arrayOfParameters.Length < 7)
            {
                throw new InvalidDataException(nameof(arrayOfParameters));
            }
            //this.type = str[0];
            this.name = arrayOfParameters[1];
            this.length = Convert.ToInt32(arrayOfParameters[2]);
            this.freshness = Convert.ToInt32(arrayOfParameters[3]);
            this.cost = Convert.ToDouble(arrayOfParameters[4]);
            this.color = arrayOfParameters[5];
        }

        public uint getID() { return id; }
        public void setID(uint id_) { this.id = id_; }
        public string getName() { return name; }
        public int getLength() { return length; }
        public int getFreshness() { return freshness; }
        public double getCost() { return cost; }
        public string getColor() { return color; }
        public static string[] getTypes() { return types; }
        public static int getCountOfParameters() { return countOfParameters; }
        //public string getStrForFile() { return strForFile; }
        public virtual string toStringForConsole()
        {
            return "\nID: " + id.ToString() + "\nName: " + name + "\nLength (cm): " + length.ToString() + "\nFreshness: " + freshness + "/10\nCost(BY): " + cost.ToString() + "\nColor: " + color;
        }
        public virtual string toStringForFile()
        {
            return name + "~" + length.ToString() + "~" + freshness.ToString() + "~" + cost.ToString() + "~" + color;
        }
        public abstract void Write(string path);
//        public abstract Bouquet addToBouquet(Bouquet bouquet);
    }
}
