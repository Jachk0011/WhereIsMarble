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
				 size = 0, case_ = 1;
			 
			 
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
				     
					 for(int i=0, j = search.length; i < search.length; i++, --j)
					 {
						 int index = m.binarySearchRecursive(arr, search[i], 0 , arr.length-1);
						 if(i==0)
						 {							 
							 System.out.println("CASE # " + case_);
							 case_++;
						 }
						
						 if(index >= 0)
							 System.out.println(search[i] + " found at " + (index+1));
						 else
							 System.out.println(search[i] + " not found");
					 }					 
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
		
		m.readFile();
		

	}

}
