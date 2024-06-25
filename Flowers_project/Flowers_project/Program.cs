// See https://aka.ms/new-console-template for more information
//Console.WriteLine("Hello, World!");

using Flowers_project;

int choose = -1;
/*Rose rose = new Rose("rose", 20, 8, 5.0, "red", 4);
Tulip tulip = new Tulip("tulip", 12, 5, 7.0, "pink", "oval");
Camomile camomile = new Camomile("beautiful camomile", 5, 10, 0.05, "white", 10);
Bouquet bouquet = new Bouquet(new BaseFlower[] {rose, tulip, camomile});*/
//Bouquet bouquet = new Bouquet(new List<BaseFlower>());
const string space = "\n---------------------------------\n";
const string menu = "Choose the operation:\n1. Get cost of bouquet\n2. Print bouquet\n3. Find flower by length\n4. Sort by freshness\n5. Add new Flower\n6. Remove flower\n0. Exit";
const string path = "..\\flowers.txt";
//bouquet.Write(path);
Bouquet bouquet = Bouquet.Read(path);
/*List<BaseFlower> flowers = new List<BaseFlower>( new BaseFlower[] { Bouquet.createFlower(new string[] {"rose", "rose1","40","5","2", "red","3" }),
                                                   Bouquet.createFlower(new string[] {"tulip","tulip1","35","2","1","pink","oval" }),
                                                   Bouquet.createFlower(new string[] { "camomile", "beautiful camomile", "3", "7", "0,04", "white", "8" }),
                                                   Bouquet.createFlower(new string[] { "rose", "test", "50", "10", "10,1", "black", "4" }),
                                                   Bouquet.createFlower(new string[] { "camomile", "Cam", "7", "9", "0,1", "pink", "10" })
                                                   } );
Bouquet bouquet = new Bouquet(flowers);
bouquet.Write(path);*/
while (choose != 0)
{
    Console.WriteLine(space + menu + space);
    try
    {
        choose = Convert.ToInt32(Console.ReadLine());
    }
    catch (FormatException)
    {
        choose = -1;
        Console.WriteLine("\nUncorrect choose\n");
        continue;
    }
    Console.WriteLine("\n");
    switch (choose)
    {
        case 1:
            Console.WriteLine("The cost of current bouquet is " + bouquet.getCostOfBouquet() + "\n");
            break;
        case 2:
            bouquet.Print();
            break;
        case 3:
            int min, max;
            Console.WriteLine("Input min value ");
            min = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Input max value ");
            max = Convert.ToInt32(Console.ReadLine());
            try
            {
                bouquet.findFlowerByLength(min, max).Print();
            }
            catch (InvalidDataException)
            {
                Console.WriteLine("In this bouquet aren't flowers of this range");
            }
            break;
        case 4:
            bouquet.sortByFreshness(true).Print();
            break;
        case 5:
            bouquet.addFlowerFromConsole();
            break;
        case 6:
            uint ID = new uint();
            Console.WriteLine("\nEnter the ID of flower, what you want remove ");
            try
            {
                ID = Convert.ToUInt32(Console.ReadLine());
                while(!bouquet.getAllID().Contains(ID))
                {
                    Console.WriteLine("\nUncorrect ID!!!\nEnter the ID of flower, what you want remove ");
                    ID = Convert.ToUInt32(Console.ReadLine());
                }
            } 
            catch (FormatException OverflowException) 
            {
                Console.WriteLine("\nInvalid input!!!\n");
            }
            bouquet.Remove(ID);
            break;
        case 0:
            bouquet.Write(path);
            break;
/*        case 5:
            try
            {
                bouquet.Read(path);
            }
            catch (InvalidDataException)
            {
                Console.WriteLine("Invalid data in the readed file");
            }
            break;*/
    }
}