import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Marble {
	static int[] arr, search ;
	static Marble m = new Marble();
	
	public int binarySearch(int[] sorted, int x)
	{
		int lowerBound = 0, upperBound = sorted.length-1, index = -1;
		
		while(lowerBound < upperBound)
		{
			int middlePoint = (lowerBound+upperBound)/2;
			if(x == sorted[middlePoint])
			{
				index = middlePoint;
				break;
			}
			else
			{
				if(x < sorted[middlePoint])
					upperBound = middlePoint - 1;
				else
					lowerBound = middlePoint - 1;					
			}
		}
		
		if (lowerBound == upperBound && sorted[lowerBound] == x)
			index = lowerBound;
		
		return index;
	}
	
	public int binarySearchRecursive(int[] sorted, int x, int lowerB, int upperB)
	{
		int middlePoint = (lowerB + upperB)/2;
		if(lowerB == upperB)
		{
			if(x == sorted[middlePoint])
				return middlePoint;
			else
				return -1;
		}
		else
		{
			if(x == sorted[middlePoint])
				return middlePoint;
			else
			{
				if(x < sorted[middlePoint])
					return binarySearchRecursive(sorted, x, lowerB, middlePoint);
				else
					return binarySearchRecursive(sorted, x, middlePoint + 1, upperB);
			}						
		}
	}
	
	public void readFile()
	{
		File archivo = null;
	      FileReader fr = null;
	      BufferedReader br = null;

	      try 
	      {
			 archivo = new File ("archivo.txt");
			 fr = new FileReader (archivo);
			 br = new BufferedReader(fr);
			
			
			 String linea;
			 int num = Integer.MIN_VALUE, 
				 ask = Integer.MIN_VALUE, 
				 size = 0, case_ = 0;
			 
			 
			 while((linea=br.readLine())!=null)
			 {
				
				 StringTokenizer st = new StringTokenizer (linea);
				 while (st.hasMoreTokens())    	 
				 {
					 num = Integer.parseInt(st.nextToken());
					 ask = Integer.parseInt(st.nextToken());
				 }
				 
				 arr = new int[num];
				 size = num + ask;
				 //System.out.println("\nsize: " + size);
				 
				 if(num == 0 && ask == 0)
					 System.exit(0);
				 else
				 {
					 for(int i=0;  i < num; i++)
					   	 arr[i] = Integer.parseInt(br.readLine());
					
					 m.countingSort(arr);
					 
					 search = new int[ask];					 
					 for(int i=0; i < ask; i++)
						 search[i] = Integer.parseInt(br.readLine());
				     
					 for(int i=0; i < search.length; i++)
					 {
						 int index = m.binarySearchRecursive(arr, search[i], 0 , arr.length-1);
						 //System.out.println("CASE # " + i);
						 if(index >= 0)
							 System.out.println(search[i] + " found at " + (index+1));
						 else
							 System.out.println(search[i] + " not found");
					 }
					 
				     
					 
					 /*//PRINTING THE ARRAY WITH THE VALUES OF MARBLES
				     for(int i=0; i<arr.length; i++)
				    	 System.out.print(arr[i] + " ");*/
				 }		         
			 }
	      }
	      catch(Exception e)
	      {
	    	  e.printStackTrace();
	      }
	      finally
	      {	         
			 try{                    
			    if( fr != null ){   
			       fr.close();     
			    }                  
			 }catch (Exception e2){ 
			    e2.printStackTrace();
			 }
	      }
	}
	
	public void countingSort(int[] unsorted)
	{
		int max = Integer.MIN_VALUE, index = 0;
		int[] aux;
		for(int i=0; i<unsorted.length; i++)
			if(max < unsorted[i])
				max = unsorted[i];
		aux = new int[max+1];// because we need one position more for zero
		
		for(int i=0; i<unsorted.length; i++)
			aux[unsorted[i]]++;
		
		for(int i=0; i < aux.length; i++)
			for(int j=0; j < aux[i]; j++)
			{
				unsorted[index] = i;
				index++;
			}		
	}

	public static void main(String[] args) throws IOException {
		/*int marble = 0, ask=0;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Marble m = new Marble();
		marble = 4; 
		ask = 1;
		int search = 3;
		int[] hand = {2,3,5,1};
		m.countingSort(hand);
		int r = m.binarySearchRecursive(hand, search, 0 , hand.length-1);
		if(r >= 0)
			System.out.println(search + " found at " + r);
		else
			System.out.println(search + " not found");*/
		
		
		m.readFile();
		

	}

}
