package com.taramt.algo;

public class LCS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Two test strings
		String str1 = "ABCBDAB";
		String str2 = "BDCABA";
		
		
        LCS obj = new LCS(); 
        String result = obj.lcs(str1, str2);

        System.out.println("\nLongest Common Subsequence : "+ result);

	}
	
	
	/** function lcs **/
	public String lcs(String str1, String str2)
	{
		//Find length of both strings
		int l1 = str1.length();
		int l2 = str2.length();

		//Create an array of size l1,l2
		int[][] arr = new int[l1 + 1][l2 + 1];

		//Loop through each character in both words to find similar
		//Character and if found update its count 
		//Or else
		//(Since differing strings) proceed to either i+1 / j+1
		for (int i = l1 - 1; i >= 0; i--)
		{
			for (int j = l2 - 1; j >= 0; j--)
			{
				if (str1.charAt(i) == str2.charAt(j))
					arr[i][j] = arr[i + 1][j + 1] + 1;
				else 
					arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
			}
		}
		
		// Print the array created
		for (int i = 0; i<l1; i++) {
			for(int j = 0; j<l2; j++)
				System.out.print(arr[i][j]+" ");
			System.out.println("");
		}
			

		int i = 0, j = 0;
		StringBuffer sb = new StringBuffer();

		//Build the string following the highest count values from
		//point (0,0)
		while (i < l1 && j < l2) 
		{
			if (str1.charAt(i) == str2.charAt(j)) 
			{
				sb.append(str1.charAt(i));
				i++;
				j++;
			}
			else if (arr[i + 1][j] >= arr[i][j + 1]) 
				i++;
			else
				j++;
		}

		return sb.toString();

	}

}
