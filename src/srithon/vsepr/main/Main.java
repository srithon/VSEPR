package srithon.vsepr.main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
	//SN, BONDED ATOMS, LONE PAIRS, BOND ANGLE
	private static final float[][] vsepr = 
	{
			{2F, 2F, 0F, 180F}, //0

			{3F, 3F, 0F, 120F}, //1
			{3F, 2F, 1F, 120F}, //2

			{4F, 4F, 0F, 109.5F}, //3
			{4F, 3F, 1F, 109.5F}, //4
			{4F, 2F, 2F, 109.5F}, //5

			{5F, 5F, 0F, 120F}, //6	//90F
			{5F, 4F, 1F, 120F}, //7	//90F
			{5F, 3F, 2F, 90F}, //8
			{5F, 2F, 3F, 180F}, //9

			{6F, 6F, 0F, 90F}, //10
			{6F, 5F, 1F, 90F}, //11
			{6F, 4F, 2F, 90F}, //12
			{6F, 3F, 3F, 90F}, //13
			{6F, 2F, 4F, 180F} //14
	};
	
	private static final String[] vseprGeometry =
	{
			"Linear",
			"Trigonal Planar", "Bent",
			"Tetrahedral", "Trigonal Pyramidal", "Bent",
			"Trigonal Bypyramidal", "See-Saw", "T-Shaped", "Linear",
			"Octahedral", "Square Pyramidal", "Square Planar", "T-Shaped", "Linear"
	};
	
	public static void main(String[] args)
	{
		String line = "";
		
		Scanner j = new Scanner(System.in);
		
		int index = 0;
		int hide = 0;
		
		while (!line.equalsIgnoreCase("exit"))
		{
			hide = -1;
			
			while (line.equals(""))
			{
				printInfo(index, hide, false);

				System.out.print("Shape: ");

				String shape = j.nextLine();

				if (vseprGeometry[index].equalsIgnoreCase(shape))
				{
					System.out.println("Correct!");
				}
				else
				{
					System.out.println("Sorry, it is actually " + vseprGeometry[index]);
					index--;
				}

				if (index < vsepr.length - 1)
				{
					index++;
				}
				else
				{
					index = 0;

					/*if (hide < 3)
						hide++;
					else
						hide = 0;*/
				}

				line = j.nextLine();
			}
			
			line = "";
			index = 0;
			hide = -1;
			
			while (line.equals(""))
			{
				printInfo(index, hide, true);

				System.out.print("Bond Angle: ");

				double guessedAngle = -1;
				boolean good = true;
				
				do
				{
					try
					{
						guessedAngle = j.nextDouble();
						good = true;
					}
					catch (InputMismatchException e)
					{
						good = false;
					}
				} while (!good);

				if (guessedAngle == vsepr[index][3])
				{
					System.out.println("Correct!");
				}
				else
				{
					System.out.println("Sorry, it is actually " + vsepr[index][3]);
					index--;
				}

				if (index < vsepr.length - 1)
				{
					index++;
				}
				else
				{
					index = 0;

					/*if (hide < 3)
						hide++;
					else
						hide = 0;*/
				}

				System.out.println();
				line = j.nextLine();
			}
		}
		
		j.close();
	}
	
	public static final int randomStericNumber()
	{
		return (int)(Math.random() * 5) + 2;
	}
	
	public static final void printInfo(int index, int hide, boolean solveForBA)
	{
		if (hide != 0)
			System.out.println("SN: " + vsepr[index][0]);
		if (hide != 1)
			System.out.println("Bonded Atoms: " + vsepr[index][1]);
		if (hide != 2)
			System.out.println("Lone Pairs: " + vsepr[index][2]);
		if (hide != 3)
		{
			if (!solveForBA)
				System.out.println("Bond Angle: " + vsepr[index][3]);
			else
				System.out.println("Shape: " + vseprGeometry[index]);
		}
	}
}
