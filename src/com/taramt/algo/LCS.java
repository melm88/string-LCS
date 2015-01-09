package com.taramt.algo;

import java.util.HashMap;
import java.util.Scanner;

public class LCS {
	
	private int MAX_LCM_COUNT = 0;

	public static void main(String[] args) {
		System.out.println("Longest Common Subsequence ");
		
		//Two User entered Strings
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter first string: ");
		String str1 = sc.nextLine().trim();
		System.out.println("Enter second string: ");
		String str2 = sc.nextLine().trim();
		sc.close();
		
		//String str1 = "ABCBDAB";
		//String str2 = "BDCABA";

		LCS obj = new LCS(); 
		obj.lcs(str1, str2);
		
	}
	
	//Function to find the starting points and invoke subsequence identifier
	public String startPoints(int[][] arr, String str1, String str2) {

		String revstr1 = new StringBuffer(str1).reverse().toString();
		String revstr2 = new StringBuffer(str2).reverse().toString();
		//Find the starting points by identifying points where 
		//words match and are with the range of MAX_LCM_COUNT
		for(int i=0; i< revstr1.length()-MAX_LCM_COUNT; i++) {
			for(int j=0; j< revstr2.length()-MAX_LCM_COUNT; j++) {
				if(revstr1.charAt(i) == revstr2.charAt(j)) {
					//System.out.println("("+i+", "+j+")"+revstr1.charAt(i));
					getAllLCS(arr,new StringBuilder(str1).reverse().toString(),
							new StringBuilder(str2).reverse().toString(),
							str1.length(),str2.length(),i,j,"");
				}
					
			}
		}


		return null;
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
		for (int i = l1 - 1; i >= 0; i--) {
			for (int j = l2 - 1; j >= 0; j--) {
				if (str1.charAt(i) == str2.charAt(j))
					arr[i][j] = arr[i + 1][j + 1] + 1;
				else 
					arr[i][j] = Math.max(arr[i + 1][j], arr[i][j + 1]);
			}
		}
		
		MAX_LCM_COUNT = arr[0][0];
		
		// Print the array created
		/*for (int i = 0; i<l1; i++) {
			for(int j = 0; j<l2; j++){
				System.out.print(arr[i][j]+" ");
			}
			System.out.println("");
		}*/
		
		startPoints(arr, str1, str2);
		
		System.out.println(ht.toString());

	}
	/*
	 * Generates all lcs by backtracking the Memoised lcs index table
	 */
	public static String getAllLCS(int arr[][],String str1,String str2,int l1, 
			int l2,int i,int j,String sb){

		while (i < l1 && j < l2) {
			//System.out.println(str1+" | "+str2);
			//System.out.println(str1.charAt(i)+" ~ "+str2.charAt(j));
			//checks if two chars match
			if (str1.charAt(i) == str2.charAt(j)) {
				sb=sb+str1.charAt(i);
				i++;
				j++;
			}
			else if (arr[i + 1][j] >= arr[i][j + 1]) {
				//two possible paths to be traversed
				if(arr[i + 1][j] == arr[i][j + 1]) {
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
					
					--i;
					j++;
					temp = getAllLCS(arr, str1, str2, l1, l2, i, j,sb);
					if(temp.length()==arr[0][0]) {
						if(ht.containsKey(temp)) {
							ht.put(temp, ht.get(temp)+1);	
						}
						else {
							ht.put(temp, 1);	
						}
					}
					
					--j;
					break;
				}
				else {
					//down path is choosen
					i++;
				}
			} else {
				//side path is choosen
				j++;
			}
		}

		return sb.toString();

	}
}
