package com.taramt.algo;

import java.util.HashMap;

public class LCS {

	public static void main(String[] args) {
		System.out.println("Longest Common Subsequence ");

		//Two test strings
		String str1 = "ABCBDAB";
		String str2 = "BDCABA";

		LCS obj = new LCS(); 
		obj.lcs(str1, str2);

	}

	static HashMap<String, Integer> ht = new HashMap<>();
	/** function lcs **/
	public void lcs(String str1, String str2)
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
			for(int j = 0; j<l2; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}
		// Print the array created
		for (int i = 0; i<l1; i++) {
			for(int j = 0; j<l2; j++){
				getAllLCS(arr,new StringBuilder(str1).reverse().toString(),new StringBuilder(str2).reverse().toString(),l1,l2,i,j,"");

			}
		}
		System.out.println(ht.toString());

	}
	/*
	 * Generates all lcs by backtracking the Memoised lcs index table
	 */
	public static String getAllLCS(int arr[][],String str1,String str2,int l1,int l2,int i,int j,String sb){

		while (i < l1 && j < l2) 
		{
			System.out.println(str1+" | "+str2);
			System.out.println(str1.charAt(i)+" ~ "+str2.charAt(j));
			//checks if two chars match
			if (str1.charAt(i) == str2.charAt(j)) 
			{
				sb=sb+str1.charAt(i);
				System.out.println("testIF: "+sb+" |"+i+","+j);
				i++;
				j++;
			}
			else if (arr[i + 1][j] >= arr[i][j + 1]) {
				System.out.println("testELSEIF: "+sb+" |"+i+","+j);
				//two possible paths to be traversed
				if(arr[i + 1][j] == arr[i][j + 1]){
					i++;
					String temp = getAllLCS(arr, str1, str2, l1, l2, i, j,sb);
					//
					if(temp.length() == arr[0][0]){
						if(ht.containsKey(temp)){
							ht.put(temp, ht.get(temp)+1);	
						}
						else{
							ht.put(temp, 1);	
						}
					}
					System.out.println("sb "+sb);
					System.out.println("LCS1 :"+sb+temp);
					--i;
					j++;
					temp = getAllLCS(arr, str1, str2, l1, l2, i, j,sb);
					if(temp.length()==arr[0][0]){
						if(ht.containsKey(temp)){
							ht.put(temp, ht.get(temp)+1);	
						}
						else{
							ht.put(temp, 1);	
						}
					}
					System.out.println("sb "+sb);
					System.out.println("LCS2 :"+sb+temp);
					--j;
					break;
				}
				else{
					//down path is choosen
					System.out.println("testElF: "+sb+" |"+i+","+j);
					i++;
				}
			} else {
				//side path is choosen
				System.out.println("testELSE: "+sb+" |"+i+","+j);
				j++;
			}
		}

		return sb.toString();

	}
}
