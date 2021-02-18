package com.example.mail_server.Model.Search;

import java.util.LinkedList;

class BinarySearch {


    public Interval[] search(Object[] arr, int l, int r, String x)
    {
        if (r >= l) {
            int mid = l + (r - l) / 2;

            // If the element is present at the
            // middle itself
            indexedWord castedElement = (indexedWord) arr[mid];
            if (castedElement.getWord().equalsIgnoreCase(x))
                return findAllOccurrences(arr,mid,x);//return its original index in the UNSORTED array

            // If element is smaller than mid, then
            // it can only be present in left subarray
            if (castedElement.getWord().compareToIgnoreCase(x) > 0)
                return search(arr, l, mid - 1, x);

            // Else the element can only be present
            // in right subarray
            return search(arr, mid + 1, r, x);
        }

        // We reach here when element is not present
        // in array
        return new Interval[0];
    }

    public Interval[] findAllOccurrences  (Object[] arr,int mid,String x){
        LinkedList<Interval> occurrencesIndices = new LinkedList<>();
        for (int i = mid; i < arr.length ; i++) {
            indexedWord castedElement = (indexedWord) arr[i];
            if (castedElement.getWord().equalsIgnoreCase(x))
                occurrencesIndices.add(new Interval(castedElement.getStart(),castedElement.getEnd()));
            else break;
        }
        for (int i = mid-1; i > 0 ; i--) {
            indexedWord castedElement = (indexedWord) arr[i];
            if (castedElement.getWord().equalsIgnoreCase(x))
                occurrencesIndices.add(new Interval(castedElement.getStart(),castedElement.getEnd()));
            else break;
        }
        return Interval.toArray(occurrencesIndices);
    }


}
