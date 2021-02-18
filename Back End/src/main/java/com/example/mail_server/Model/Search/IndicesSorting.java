package com.example.mail_server.Model.Search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndicesSorting {
    public Object[] sortMailContent(String text) {

        ArrayList<indexedWord> temp = new ArrayList<>();
            String regex = "\\w+";
            System.out.println(text);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);
            while (matcher.find())
                temp.add(new indexedWord(matcher.group(),matcher.start(),matcher.end()-1));
            divide(temp,0,temp.size()-1);
            return temp.toArray();
//            for (Object word : arr) {
//                indexedWord castedObject = (indexedWord) word;
//                bw.write("\n" +  castedObject.word + " " + castedObject.start+ " "+ castedObject.end);
//            }
    }

    void merge(ArrayList<indexedWord> arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /*Copy data to temp arrays*/
        ArrayList<indexedWord> L = new ArrayList<>(arr.subList(l,m+1));
        ArrayList<indexedWord> R = new ArrayList<>(arr.subList(m+1,r+1));

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if(L.get(i).word.compareToIgnoreCase(R.get(j).word)<0){
                arr.set(k,L.get(i));
                i++;
            } else {
                arr.set(k,R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr.set(k,L.get(i));
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr.set(k,R.get(j));
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    void divide(ArrayList<indexedWord> arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            divide(arr, l, m);
            divide(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public static void main(String[] args) {
        IndicesSorting h = new IndicesSorting();
        Object[] haha = h.sortMailContent("It is a long established fact");
        BinarySearch hoho = new BinarySearch();
        System.out.println(Arrays.toString(hoho.search(haha, 0, haha.length - 1, "fact")));
    }
}
