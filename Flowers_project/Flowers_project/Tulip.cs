using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Flowers_project
{
    internal class Tulip : BaseFlower
    {
        private readonly string formOfTurnip;
        public Tulip(string name, int length, int freshness, double cost, string color, string formOfTurnip) : base(name, length, freshness, cost, color)
        {
            this.formOfTurnip = formOfTurnip;
            type = "tulip";
            //_ = strForFile.Insert(0, type);
            //this.strForFile += "~" + formOfTurnip;
        }

        public Tulip(string[] str) : base(str)
        {
            type = str[0];
            formOfTurnip = str[6];
        }
        public string getFormOfTurnip() { return formOfTurnip; }

        public override string toStringForConsole()
        {
            return "Type: " + type + base.toStringForConsole() + "\nForm: " + formOfTurnip + "\n";
        }

        public override string toStringForFile()
        {
            return type + "~" + base.toStringForFile() + "~" + formOfTurnip;
        }
        public override void Write(string path)
        {
            StreamWriter writer = new StreamWriter(path);
            writer.WriteLine(this.toStringForFile());
            writer.Close();
        }
    }
}
